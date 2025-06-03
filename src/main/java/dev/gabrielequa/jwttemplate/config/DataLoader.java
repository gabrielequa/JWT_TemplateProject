package dev.gabrielequa.jwttemplate.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.gabrielequa.jwttemplate.model.User;
import dev.gabrielequa.jwttemplate.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Crea un utente di test se non esiste già
        if (!userRepository.existsByUsername("testuser")) {
            User testUser = new User(
                "testuser",
                passwordEncoder.encode("password123"),
                "test@ciao.com"
            );
            userRepository.save(testUser);
            System.out.println("Utente Test creato - Username: testuser, Password: password123");
        }

        // Crea un utente admin se non esiste già
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = new User(
                "admin",
                passwordEncoder.encode("password123"),
                "admin@example.com"
                );
            userRepository.save(adminUser);
            System.out.println("Utente Admin creato - Username: admin, Password: password123");
            
        }
    }
}
