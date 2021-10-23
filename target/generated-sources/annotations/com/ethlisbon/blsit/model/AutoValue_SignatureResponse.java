
package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SignatureResponse extends SignatureResponse {

  private final String message;
  private final String key;

  private AutoValue_SignatureResponse(
      String message,
      String key) {
    if (message == null) {
      throw new NullPointerException("Null message");
    }
    this.message = message;
    if (key == null) {
      throw new NullPointerException("Null key");
    }
    this.key = key;
  }

  @JsonProperty(value = "message")
  @Override
  public String getMessage() {
    return message;
  }

  @JsonProperty(value = "key")
  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String toString() {
    return "SignatureResponse{"
        + "message=" + message + ", "
        + "key=" + key
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
           && (this.key.equals(that.getKey()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.message.hashCode();
    h *= 1000003;
    h ^= this.key.hashCode();
    return h;
  }

  static final class Builder extends SignatureResponse.Builder {
    private String message;
    private String key;
    Builder() {
    }
    Builder(SignatureResponse source) {
      this.message = source.getMessage();
      this.key = source.getKey();
    }
    @Override
    public SignatureResponse.Builder setMessage(String message) {
      this.message = message;
      return this;
    }
    @Override
    public SignatureResponse.Builder setKey(String key) {
      this.key = key;
      return this;
    }
    @Override
    public SignatureResponse build() {
      String missing = "";
      if (message == null) {
        missing += " message";
      }
      if (key == null) {
        missing += " key";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SignatureResponse(
          this.message,
          this.key);
    }
  }

}
