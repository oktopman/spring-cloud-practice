package me.oktop.client2service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Client2ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Client2ServiceApplication.class, args);
    }

}
