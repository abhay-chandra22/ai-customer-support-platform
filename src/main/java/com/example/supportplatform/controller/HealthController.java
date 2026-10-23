package com.example.supportplatform.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public Map<String , String> checkHealth(){
        return Map.of(
                "status" , "UP",
                "message" , "Support Platform is running"
        );
    }
}
