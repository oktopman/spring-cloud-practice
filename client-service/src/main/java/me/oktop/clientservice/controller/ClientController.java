package me.oktop.clientservice.controller;

import me.oktop.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ClientController {

    @Value("${example.property}")
    private String exampleProperty;

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String test() {
        return exampleProperty;
    }

    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable String userId) {
        return clientService.getUsername(userId);
    }
}