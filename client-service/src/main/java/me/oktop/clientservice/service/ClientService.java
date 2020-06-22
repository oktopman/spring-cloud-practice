package me.oktop.clientservice.service;

import me.oktop.clientservice.client.Client2FeignClient;
import me.oktop.clientservice.client.RibbonRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final RibbonRestTemplateClient ribbonRestTemplateClient;
    private final Client2FeignClient feignClient;

    public ClientService(RibbonRestTemplateClient ribbonRestTemplateClient, Client2FeignClient feignClient) {
        this.ribbonRestTemplateClient = ribbonRestTemplateClient;
        this.feignClient = feignClient;
    }


    public String getUsername(String userId) {
        String username = ribbonRestTemplateClient.getUsername(userId);
        return username;
    }

    public String getUsernameUseFeignClient(String userId) {
        return feignClient.getUsername(userId + "feign : ");
    }
}
