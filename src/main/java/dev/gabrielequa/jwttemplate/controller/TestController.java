package dev.gabrielequa.jwttemplate.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielequa.jwttemplate.model.User;
import dev.gabrielequa.jwttemplate.repository.UserRepository;



@RestController
@RequestMapping("/api/test")
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoint pubblico, accessibile a tutti";
    }
    
    @GetMapping("/protected")
    public String protectedEndpoint(Authentication authentication) {
        return "Endpoint protetto, benvenuto " + authentication.getName();
    }
    
    @GetMapping("/user")
    public String userInfo(Authentication authentication) {
        System.out.println("Ciao, " + authentication.getName() + ", ecco le tue info:");
        return "User info: " + authentication.getName() + " - Authorities: " + authentication.getAuthorities();
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers(Authentication authentication) {
        System.out.println("Ciao, " + authentication.getName() + "! Ecco tutti gli utenti registrati:");
        return userRepository.findAll();
    }
    
}
