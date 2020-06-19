package me.oktop.clientservice.service;

import me.oktop.clientservice.client.RibbonRestTemplateClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final RibbonRestTemplateClient ribbonRestTemplateClient;

    public ClientService(RibbonRestTemplateClient ribbonRestTemplateClient) {
        this.ribbonRestTemplateClient = ribbonRestTemplateClient;
    }


    public String getUsername(String userId) {
        String username = ribbonRestTemplateClient.getUsername(userId);
        return username;
    }
}
