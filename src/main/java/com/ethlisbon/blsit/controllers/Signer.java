package com.ethlisbon.blsit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Signer {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "hola mundo";
    }
}