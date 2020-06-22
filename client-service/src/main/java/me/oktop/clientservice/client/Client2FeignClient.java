package me.oktop.clientservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLIENT2-SERVICE")
public interface Client2FeignClient {

    @GetMapping("/user/{userId}")
    String getUsername(@PathVariable String userId);
}
