package dev.gabrielequa.jwttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwttemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwttemplateApplication.class, args);
		System.out.println("\n=== JWT Authentication Application Started ===");
        System.out.println("Server running on: http://localhost:8080");
        System.out.println("H2 Console: http://localhost:8080/h2-console");
        System.out.println("Test endpoints:");
        System.out.println("- POST /api/auth/register");
        System.out.println("- POST /api/auth/login");
        System.out.println("- POST /api/auth/refresh");
        System.out.println("- POST /api/auth/logout");
        System.out.println("- GET /api/test/public");
        System.out.println("- GET /api/test/protected");
        System.out.println("- GET /api/test/user");
        System.out.println("\nTest User Credentials:");
        System.out.println("Username: testuser");
        System.out.println("Password: password123");
        System.out.println("\nAdmin User Credentials:");
        System.out.println("Username: admin");
        System.out.println("Password: password123");
        System.out.println("===============================================\n");
	}

}
