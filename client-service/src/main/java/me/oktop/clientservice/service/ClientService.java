package me.oktop.clientservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.oktop.clientservice.client.Client2FeignClient;
import me.oktop.clientservice.client.RibbonRestTemplateClient;
import me.oktop.clientservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @HystrixCommand(commandKey = "clientKey",
            fallbackMethod = "fallback",
            threadPoolKey = "clientByClient2ThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"), // 스레드 풀의 스레드 개수
                    @HystrixProperty(name = "maxQueueSize", value = "10") // 스레드 풀 앞에 배치할 큐와 큐에 넣을 요청의 수
            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")})
    public String getUsernameUseFeignClient(String userId) throws InterruptedException {
        System.out.println("ClientService Correlation id: " +  UserContextHolder.getContext().getCorrelationId());
        return "success";
//        return feignClient.getUsername(userId + "feign : ");
    }

    public String fallback(String userId, Throwable t) {
        System.out.println("t = " + t);
        return "fallback method execute";
    }
}
