package me.oktop.clientservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "fallback")
    public String getUsernameUseFeignClient(String userId) throws InterruptedException {
        Thread.sleep(2000);
        return feignClient.getUsername(userId + "feign : ");
    }

    public String fallback(String userId, Throwable t) {
        System.out.println("t = " + t);
        return "fallback method execute";
    }
}
