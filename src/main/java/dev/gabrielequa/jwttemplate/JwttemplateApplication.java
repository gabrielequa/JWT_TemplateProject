package dev.gabrielequa.jwttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwttemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwttemplateApplication.class, args);
		System.out.println("\n=== JWT Template Project ===");
        System.out.println("Server: http://localhost:8080");
        System.out.println("Console H2: http://localhost:8080/h2-console");
        System.out.println("Test endpoints:");
        System.out.println("- POST /api/auth/register");
        System.out.println("- POST /api/auth/login");
        System.out.println("- POST /api/auth/refresh");
        System.out.println("- POST /api/auth/logout");
        System.out.println("- GET /api/test/public");
        System.out.println("- GET /api/test/protected");
        System.out.println("- GET /api/test/user");
        System.out.println("\nTest Credenziali Utenti:");
        System.out.println("Username: testuser");
        System.out.println("Password: password123");
        System.out.println("\nAdmin Credenziali Utenti:");
        System.out.println("Username: admin");
        System.out.println("Password: password123");
        System.out.println("===============================================\n");
	}

}
