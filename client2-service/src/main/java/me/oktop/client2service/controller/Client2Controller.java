package me.oktop.client2service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client2Controller {

    @GetMapping("/user/{userId}")
    public String getUsername(@PathVariable String userId) {
        return userId + "name";
    }

}
