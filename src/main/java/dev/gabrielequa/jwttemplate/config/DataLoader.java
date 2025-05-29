package dev.gabrielequa.jwttemplate.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.gabrielequa.jwttemplate.models.User;
import dev.gabrielequa.jwttemplate.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create a test user if it doesn't exist
        if (!userRepository.existsByUsername("testuser")) {
            User testUser = new User(
                "testuser",
                passwordEncoder.encode("password123"),
                "test@example.com"
            );
            userRepository.save(testUser);
            System.out.println("Test user created - Username: testuser, Password: password123");
        }

        // Create an admin user if it doesn't exist
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = new User(
                "admin",
                passwordEncoder.encode("password123"),
                "admin@example.com"
                );
            userRepository.save(adminUser);
            
        }
    }
}
