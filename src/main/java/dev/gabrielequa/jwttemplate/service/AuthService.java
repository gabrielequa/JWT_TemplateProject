package dev.gabrielequa.jwttemplate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gabrielequa.jwttemplate.dto.JwtRequest;
import dev.gabrielequa.jwttemplate.dto.JwtResponse;
import dev.gabrielequa.jwttemplate.dto.RefreshTokenRequest;
import dev.gabrielequa.jwttemplate.dto.RegisterRequest;
import dev.gabrielequa.jwttemplate.models.RefreshToken;
import dev.gabrielequa.jwttemplate.models.User;
import dev.gabrielequa.jwttemplate.repository.UserRepository;
import dev.gabrielequa.jwttemplate.utils.JwtUtil;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public JwtResponse authenticate(JwtRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Credenziali invalide", e);
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String accessToken = jwtUtil.generateAccessToken(userDetails);
        
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        
        return new JwtResponse(accessToken, refreshToken.getToken());
    }
    
    public JwtResponse refreshToken(RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtUtil.generateAccessToken(user);

                    // Creo un nuovo refresh token
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getId());
                    
                    return new JwtResponse(accessToken, newRefreshToken.getToken());
                })
                .orElseThrow(() -> new RuntimeException("Il refresh token non è nel database!"));
    }
    
    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username già in uso!");
        }
        
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email già in uso!");
        }
        
        User user = new User(
            registerRequest.getUsername(),
            passwordEncoder.encode(registerRequest.getPassword()),
            registerRequest.getEmail()
        );
        
        userRepository.save(user);
    }
    
    public void logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        refreshTokenService.deleteByUserId(user.getId());
    }
}
