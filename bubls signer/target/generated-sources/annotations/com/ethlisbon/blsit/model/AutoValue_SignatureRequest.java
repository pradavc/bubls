
package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SignatureRequest extends SignatureRequest {

  private final String message;

  private AutoValue_SignatureRequest(
      String message) {
    if (message == null) {
      throw new NullPointerException("Null message");
    }
    this.message = message;
  }

  @JsonProperty(value = "message")
  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "SignatureRequest{"
        + "message=" + message
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SignatureRequest) {
      SignatureRequest that = (SignatureRequest) o;
      return (this.message.equals(that.getMessage()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.message.hashCode();
    return h;
  }

  static final class Builder extends SignatureRequest.Builder {
    private String message;
    Builder() {
    }
    Builder(SignatureRequest source) {
      this.message = source.getMessage();
    }
    @Override
    public SignatureRequest.Builder setMessage(String message) {
      this.message = message;
      return this;
    }
    @Override
    public SignatureRequest build() {
      String missing = "";
      if (message == null) {
        missing += " message";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SignatureRequest(
          this.message);
    }
  }

}
