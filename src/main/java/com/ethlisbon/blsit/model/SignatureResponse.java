package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_SignatureResponse.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class SignatureResponse {
    @JsonProperty("message")
    public abstract String getMessage();
    @JsonProperty("public_key")
    public abstract String getPublicKey();

    public static SignatureResponse.Builder builder() {
        return new AutoValue_SignatureResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("message")
        public abstract SignatureResponse.Builder setMessage(String message);
        @JsonProperty("public_key")
        public abstract SignatureResponse.Builder setPublicKey(String publicKey);

        public abstract SignatureResponse build();
    }
}
