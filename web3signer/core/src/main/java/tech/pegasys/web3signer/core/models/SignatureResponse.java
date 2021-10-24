package tech.pegasys.web3signer.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignatureResponse {
    private final String signature;
    private final String publicKey;

    @JsonCreator
    public SignatureResponse(
            @JsonProperty(value = "signature", required = true) final String signature,
            @JsonProperty(value = "public_key", required = true) final String publicKey) {
        this.signature = signature;
        this.publicKey = publicKey;
    }

    @JsonProperty(value = "signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty(value = "public_key")
    public String getPublicKey() {
        return publicKey;
    }
}
