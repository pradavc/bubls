
package com.ethlisbon.blsit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_SignatureResponse extends SignatureResponse {

  private final String signature;

  private AutoValue_SignatureResponse(
      String signature) {
    if (signature == null) {
      throw new NullPointerException("Null signature");
    }
    this.signature = signature;
  }

  @JsonProperty(value = "signature")
  @Override
  public String getSignature() {
    return signature;
  }

  @Override
  public String toString() {
    return "SignatureResponse{"
        + "signature=" + signature
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SignatureResponse) {
      SignatureResponse that = (SignatureResponse) o;
      return (this.signature.equals(that.getSignature()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.signature.hashCode();
    return h;
  }

  static final class Builder extends SignatureResponse.Builder {
    private String signature;
    Builder() {
    }
    Builder(SignatureResponse source) {
      this.signature = source.getSignature();
    }
    @Override
    public SignatureResponse.Builder setSignature(String signature) {
      this.signature = signature;
      return this;
    }
    @Override
    public SignatureResponse build() {
      String missing = "";
      if (signature == null) {
        missing += " signature";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_SignatureResponse(
          this.signature);
    }
  }

}
