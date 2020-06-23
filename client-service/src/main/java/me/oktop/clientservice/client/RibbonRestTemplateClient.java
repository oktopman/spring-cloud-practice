package me.oktop.clientservice.client;

import me.oktop.clientservice.utils.UserContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RibbonRestTemplateClient {

    private static final String URL = "http://CLIENT2-SERVICE/user/{userId}";

    private final RestTemplate restTemplate;


    public RibbonRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUsername(String userId) {
        System.out.println("ClientService Correlation id: " +  UserContextHolder.getContext().getCorrelationId());
        ResponseEntity<String> username = restTemplate.exchange(
                URL, HttpMethod.GET, null, String.class, userId);

        return username.getBody();
    }
}
