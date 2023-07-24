package com.example.restclient;

import com.example.restclient.serivce.MqttService;
import com.example.restclient.serivce.MqttService2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

    private final MqttService service;
    private final MqttService2 service2;

    public RestClientApplication(MqttService service, MqttService2 service2) {
        this.service = service;
        this.service2 = service2;
    };
    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.start();
        service2.start();
    }
}
