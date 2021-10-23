
package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SignatureResponse extends SignatureResponse {

  private final String message;

  private AutoValue_SignatureResponse(
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
    return "SignatureResponse{"
        + "message=" + message
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SignatureResponse) {
      SignatureResponse that = (SignatureResponse) o;
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

  static final class Builder extends SignatureResponse.Builder {
    private String message;
    Builder() {
    }
    Builder(SignatureResponse source) {
      this.message = source.getMessage();
    }
    @Override
    public SignatureResponse.Builder setMessage(String message) {
      this.message = message;
      return this;
    }
    @Override
    public SignatureResponse build() {
      String missing = "";
      if (message == null) {
        missing += " message";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SignatureResponse(
          this.message);
    }
  }

}
