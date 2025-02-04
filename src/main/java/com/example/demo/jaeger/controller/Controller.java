package com.example.demo.jaeger.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/hello")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/path1")
    public ResponseEntity<String>  path1() {

        logger.info("Incoming request at {} for request /path1 ", applicationName);
        String response = restTemplate.getForObject("http://localhost:8080/hello/path2", String.class);
        return ResponseEntity.ok("response from /path1 + " + response);
    }

    @GetMapping("/path2")
    public ResponseEntity<String>  path2() {
        logger.info("Incoming request at {} at /path2", applicationName);
        return ResponseEntity.ok("response from /path2 ");
    }
}
