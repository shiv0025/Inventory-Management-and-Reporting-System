package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // 👈 ADD THIS

@SpringBootApplication
@ComponentScan(basePackages = {"com.inventory", "com.database"})
@EntityScan(basePackages = {
        "com.inventory.database_system.entity",
        "com.inventory.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.inventory.database_system.repository",
        "com.inventory.repository"
})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

        // 👇 ADD THIS BLOCK (inside main)
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("ENCODED PASSWORD: " + encoder.encode("admin"));
    }
}