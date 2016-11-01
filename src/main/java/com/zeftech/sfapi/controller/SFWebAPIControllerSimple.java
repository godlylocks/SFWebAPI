package com.zeftech.sfapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cody_OPI on 11/1/16.
 */
@RestController
@RequestMapping(value = "/rest/v1/SimpleController")
public class SFWebAPIControllerSimple {

    private static int numCalls;

    @RequestMapping(value = "/secretPhrase", method = RequestMethod.GET, consumes = "application/json")
    public String secretPhrase() {
        numCalls++;
        return "My Secret Phrase";
    }

    @RequestMapping(value = "/numberOfCalls", method = RequestMethod.GET, consumes = "application/json")
    public int numberOfCalls() {
        numCalls++;
        return numCalls;
    }
}
