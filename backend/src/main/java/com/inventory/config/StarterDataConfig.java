package com.inventory.config;

import com.inventory.database_system.entity.Category;
import com.inventory.database_system.entity.Supplier;
import com.inventory.database_system.repository.CategoryRepository;
import com.inventory.database_system.repository.SupplierRepository;
import com.inventory.entity.Role;
import com.inventory.entity.User;
import com.inventory.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class StarterDataConfig {

    @Bean
    CommandLineRunner seedStarterData(
            CategoryRepository categoryRepository,
            SupplierRepository supplierRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // Seed Categories (only if empty)
            if (categoryRepository.count() == 0) {
                categoryRepository.saveAll(List.of(
                        new Category(null, "Electronics", "Electronic items"),
                        new Category(null, "Stationery", "Office and school supplies"),
                        new Category(null, "Furniture", "Office and home furniture"),
                        new Category(null, "Food", "Food and grocery products")
                ));
            }

            // Seed Suppliers (only if empty)
            if (supplierRepository.count() == 0) {
                supplierRepository.saveAll(List.of(
                        new Supplier(null, "Default Supplier", "default@supplier.com", "9876543210"),
                        new Supplier(null, "Global Traders", "global@supplier.com", "9999999999")
                ));
            }

            // Seed Default Users for every role (ADMIN, SUPPLIER, CUSTOMER)
            if (!userRepository.existsByUsername("admin")) {
                userRepository.save(User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .email("admin@inventory.com")
                        .role(Role.ADMIN)
                        .active(true)
                        .build());
            }

            if (!userRepository.existsByUsername("supplier")) {
                userRepository.save(User.builder()
                        .username("supplier")
                        .password(passwordEncoder.encode("supplier123"))
                        .email("supplier@inventory.com")
                        .role(Role.SUPPLIER)
                        .active(true)
                        .build());
            }

            if (!userRepository.existsByUsername("customer")) {
                userRepository.save(User.builder()
                        .username("customer")
                        .password(passwordEncoder.encode("customer123"))
                        .email("customer@inventory.com")
                        .role(Role.CUSTOMER)
                        .active(true)
                        .build());
            }

            // Optional startup logs
            System.out.println("Categories count: " + categoryRepository.count());
            System.out.println("Suppliers count: " + supplierRepository.count());
            System.out.println("Users count: " + userRepository.count());
        };
    }
}