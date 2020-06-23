package me.oktop.clientservice.config;

import me.oktop.clientservice.utils.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
public class RibbonRestTemplateConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List interceptors = restTemplate.getInterceptors();
        if (interceptors != null) {
            interceptors.add(new UserContextInterceptor());
            restTemplate.setInterceptors(interceptors);
            return restTemplate;
        }

        restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        return restTemplate;
    }
}
