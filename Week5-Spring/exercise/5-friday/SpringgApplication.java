package com.rev.rest.springg;

import com.rev.rest.springg.model.AppUser;
import com.rev.rest.springg.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringgApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringgApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedUsers(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userRepo.save(admin);
            }

            if (userRepo.findByUsername("student").isEmpty()) {
                AppUser student = new AppUser();
                student.setUsername("student");
                student.setPassword(passwordEncoder.encode("student123"));
                student.setRole("ROLE_STUDENT");
                userRepo.save(student);
            }
        };
    }
}
