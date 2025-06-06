package dev.gabrielequa.jwttemplate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import dev.gabrielequa.jwttemplate.dto.JwtRequest;
import dev.gabrielequa.jwttemplate.dto.JwtResponse;
import dev.gabrielequa.jwttemplate.dto.RefreshTokenRequest;
import dev.gabrielequa.jwttemplate.dto.RegisterRequest;
import dev.gabrielequa.jwttemplate.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest authRequest) {
        try {
            JwtResponse response = authService.authenticate(authRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(registerRequest);
            return ResponseEntity.ok("Utente registrato correttamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            JwtResponse response = authService.refreshToken(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }
    
    @PostMapping("/logout")
    @SecurityRequirement(name = "bearerAuth") // <-- Abilita l'autenticazione per Swagger
    public ResponseEntity<?> logout(Authentication authentication) {
        try {
            authService.logout(authentication.getName());
            return ResponseEntity.ok("Logout effettuato con successo");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }
}
