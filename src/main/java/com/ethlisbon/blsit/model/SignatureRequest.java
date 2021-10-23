package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_SignatureRequest.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class SignatureRequest {
    @JsonProperty("message")
    public abstract String getMessage();

    public static SignatureRequest.Builder builder() {
        return new AutoValue_SignatureRequest.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("message")
        public abstract SignatureRequest.Builder setMessage(String message);

        public abstract SignatureRequest build();
    }
}
