package dev.gabrielequa.jwttemplate.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint - no authentication required!";
    }
    
    @GetMapping("/protected")
    public String protectedEndpoint(Authentication authentication) {
        return "This is a protected endpoint. Welcome, " + authentication.getName() + "!";
    }
    
    @GetMapping("/user")
    public String userInfo(Authentication authentication) {
        return "User info: " + authentication.getName() + " - Authorities: " + authentication.getAuthorities();
    }
}
