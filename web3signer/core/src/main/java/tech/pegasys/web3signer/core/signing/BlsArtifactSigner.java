/*
 * Copyright 2020 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.web3signer.core.signing;

import static tech.pegasys.web3signer.core.util.IdentifierUtils.normaliseIdentifier;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tuweni.io.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import tech.pegasys.teku.bls.BLS;
import tech.pegasys.teku.bls.BLSKeyPair;

import org.apache.tuweni.bytes.Bytes;
import tech.pegasys.teku.bls.BLSPublicKey;
import tech.pegasys.teku.bls.BLSSignature;
import tech.pegasys.web3signer.core.models.SignatureResponse;
import tech.pegasys.web3signer.core.multikey.metadata.SigningMetadataException;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BlsArtifactSigner implements ArtifactSigner {

  private final BLSKeyPair keyPair;

  // TODO: This is a quick fix. Initialize using dependency injection
  private static final Logger logger = LogManager.getLogger();
  private final ObjectMapper objectMapper = new ObjectMapper();

  public BlsArtifactSigner(final BLSKeyPair keyPair) {
    this.keyPair = keyPair;
  }

  @Override
  public String getIdentifier() {
    return normaliseIdentifier(keyPair.getPublicKey().toString());
  }

  @Override
  public BlsArtifactSignature sign(final Bytes data) {
    String encodedData = Base64.encode(data);
    List<BLSPublicKey> blsPublicKeys = Collections.emptyList();
    List<BLSSignature> blsSignatures = Collections.emptyList();

    for (int nodeNumber = 1; nodeNumber <= 3; nodeNumber++) {
      SignatureResponse signatureResponse;
      // TODO: Move hardcoded values to application.yaml file
      String url = "http://node" + nodeNumber + ":8080/api/v1/sign";
      String encodedSignatureResponse = requestSignature(encodedData, url);
      try {
        signatureResponse = this.objectMapper.readValue(encodedSignatureResponse, SignatureResponse.class);
        blsSignatures.add(BLSSignature.fromSSZBytes(Base64.decode(signatureResponse.getSignature())));
        blsPublicKeys.add(BLSPublicKey.fromSSZBytes(Base64.decode(signatureResponse.getPublicKey())));
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

    BLSPublicKey aggregatedBlsPublicKey = BLSPublicKey.aggregate(blsPublicKeys);
    BLSSignature aggregatedBlsSignature = BLS.aggregate(blsSignatures);

    logger.info("Aggregated BLS signature: " + aggregatedBlsSignature);
    logger.info("Aggregated BLS public key: " + aggregatedBlsPublicKey);
    return new BlsArtifactSignature(aggregatedBlsSignature);
  }

  private ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
      logger.info("Request: ", clientRequest.method(), clientRequest.url());
      clientRequest.headers()
              .forEach((name, values) -> values.forEach(value -> logger.info("Header: ", name, value)));
      return Mono.just(clientRequest);
    });
  }

  private String requestSignature(String encodedData, String serverUrl) {
    ObjectNode newSignatureRequest = this.objectMapper.createObjectNode()
            .put("message", encodedData);

    WebClient webClient = WebClient
            .builder()
            .filters(exchangeFilterFunctions -> {
              exchangeFilterFunctions.add(logRequest());
            })
            .baseUrl(serverUrl)
            .build();
    try {
      return webClient.post()
              .uri(serverUrl)
              .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
              .body(Mono.just(newSignatureRequest.toString()), String.class)
              .retrieve()
              .bodyToMono(String.class)
              .timeout(Duration.ofSeconds(10))
              .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                      .jitter(0d)
                      .doBeforeRetry(retrySignal -> {
                        logger.warn("Retrying: "
                                + retrySignal.totalRetries() + "; "
                                + retrySignal.totalRetriesInARow() + "; "
                                + retrySignal.failure());
                      })
                      .doAfterRetry(retrySignal -> {
                        logger.info("Retried " + retrySignal.totalRetries());
                      }))
              .toFuture()
              .get();
    } catch (ExecutionException | InterruptedException e) {
      throw new SigningMetadataException("Failed to sign using remote BLS-IT signer. ", e);
    }
  }
}
