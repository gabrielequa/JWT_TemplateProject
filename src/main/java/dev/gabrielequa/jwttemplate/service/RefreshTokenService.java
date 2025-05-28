package dev.gabrielequa.jwttemplate.service;

import dev.gabrielequa.jwttemplate.models.User;
import dev.gabrielequa.jwttemplate.repository.UserRepository;
import dev.gabrielequa.jwttemplate.models.RefreshToken;
import dev.gabrielequa.jwttemplate.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    
    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenDuration;
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Delete existing refresh token for user
        refreshTokenRepository.findByUser(user)
                .ifPresent(refreshTokenRepository::delete);
        
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDuration / 1000));
        refreshToken.setToken(UUID.randomUUID().toString());
        
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.isExpired()) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
    
    @Transactional
    public void deleteByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        refreshTokenRepository.deleteByUser(user);
    }
}
