
package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SignatureResponse extends SignatureResponse {

  private final String message;
  private final String publicKey;

  private AutoValue_SignatureResponse(
      String message,
      String publicKey) {
    if (message == null) {
      throw new NullPointerException("Null message");
    }
    this.message = message;
    if (publicKey == null) {
      throw new NullPointerException("Null publicKey");
    }
    this.publicKey = publicKey;
  }

  @JsonProperty(value = "message")
  @Override
  public String getMessage() {
    return message;
  }

  @JsonProperty(value = "public_key")
  @Override
  public String getPublicKey() {
    return publicKey;
  }

  @Override
  public String toString() {
    return "SignatureResponse{"
        + "message=" + message + ", "
        + "publicKey=" + publicKey
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SignatureResponse) {
      SignatureResponse that = (SignatureResponse) o;
      return (this.message.equals(that.getMessage()))
           && (this.publicKey.equals(that.getPublicKey()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.message.hashCode();
    h *= 1000003;
    h ^= this.publicKey.hashCode();
    return h;
  }

  static final class Builder extends SignatureResponse.Builder {
    private String message;
    private String publicKey;
    Builder() {
    }
    Builder(SignatureResponse source) {
      this.message = source.getMessage();
      this.publicKey = source.getPublicKey();
    }
    @Override
    public SignatureResponse.Builder setMessage(String message) {
      this.message = message;
      return this;
    }
    @Override
    public SignatureResponse.Builder setPublicKey(String publicKey) {
      this.publicKey = publicKey;
      return this;
    }
    @Override
    public SignatureResponse build() {
      String missing = "";
      if (message == null) {
        missing += " message";
      }
      if (publicKey == null) {
        missing += " publicKey";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SignatureResponse(
          this.message,
          this.publicKey);
    }
  }

}
