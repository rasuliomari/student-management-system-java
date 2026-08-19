package com.example.studentmanagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeUsers(UserRepository userRepository) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                BCryptPasswordEncoder encoder =
                        new BCryptPasswordEncoder();

                User admin = new User(
                        "admin",
                        encoder.encode("Admin@123"),
                        "ADMIN"
                );

                userRepository.save(admin);

                System.out.println(
                        "Default admin account created."
                );
            }
        };
    }
}