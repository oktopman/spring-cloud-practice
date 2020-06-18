package me.oktop.clientservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {

    @Value("${example.property}")
    private String exampleProperty;

    @GetMapping("/")
    public String test() {
        return exampleProperty;
    }
}
