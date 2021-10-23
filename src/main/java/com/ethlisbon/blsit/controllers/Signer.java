package com.ethlisbon.blsit.controllers;

import com.ethlisbon.blsit.model.SignatureRequest;
import com.ethlisbon.blsit.model.SignatureResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class Signer {

    @PostMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SignatureResponse test(@RequestBody SignatureRequest request) {
        SignatureResponse signatureResponse = SignatureResponse.builder().setSignature("signed " + request.getMessage()).build();
        return signatureResponse;
    }
}