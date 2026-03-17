package com.ythwork.resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {

    @GetMapping("/public")
    public String publicApi() {
        return "public ok";
    }

    @GetMapping("/api/read")
    public String readApi(Authentication authentication) {
        return "read ok " + authentication.getName();
    }

    @GetMapping("/api/resource")
    public Map<String, Object> resourceApi(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        return Map.of(
                "name", authentication.getName(),
                "authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList(),
                "claims", jwt.getClaims());
    }
}
