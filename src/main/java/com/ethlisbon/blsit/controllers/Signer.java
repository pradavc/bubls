package com.ethlisbon.blsit.controllers;

import com.ethlisbon.blsit.model.SignatureRequest;
import com.ethlisbon.blsit.model.SignatureResponse;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.pegasys.teku.bls.*;
import org.apache.tuweni.io.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping(path = "/api/v1")
public class Signer {

    @Value("${keys.public}")
    private String publicKey;

    @Value("${keys.private}")
    private String privateKey;

    @PostMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SignatureResponse test(@RequestBody SignatureRequest request) {
        SignatureResponse signatureResponse = SignatureResponse.builder()
                .setMessage("signed" + request.getMessage())
                .setKey("publicKey").build();
        return signatureResponse;
    }

    @PostMapping(path = "/sign", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SignatureResponse sign(@RequestBody SignatureRequest request) {
        Bytes message = Base64.decode(request.getMessage());
        BLSSecretKey secretKey = BLSSecretKey.fromBytes(Bytes32.fromHexString(privateKey));
        BLSSignature signature = BLS.sign(secretKey, message);
        SignatureResponse signatureResponse = SignatureResponse.builder()
                .setMessage(Base64.encode(signature.toSSZBytes()))
                .setKey(Base64.encode(secretKey.toPublicKey().toSSZBytes())).build();
        return signatureResponse;
    }
}