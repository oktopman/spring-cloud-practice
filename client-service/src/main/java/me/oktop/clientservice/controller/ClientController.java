package me.oktop.clientservice.controller;

import me.oktop.clientservice.service.ClientService;
import me.oktop.clientservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/feign/user/{userId}")
    public String getUserUseFeign(@PathVariable String userId) throws InterruptedException {
        System.out.println("ClientController Correlation id: " +  UserContextHolder.getContext().getCorrelationId());
        return clientService.getUsernameUseFeignClient(userId);
    }

    @GetMapping("/resttemplate/user/{userId}")
    public String getUserUseRestTemplate(@PathVariable String userId) throws InterruptedException {
        System.out.println("ClientController Correlation id: " +  UserContextHolder.getContext().getCorrelationId());
        return clientService.getUsername(userId);
    }
}
