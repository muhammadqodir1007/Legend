package com.example.legend;

import com.example.legend.entity.Admin;
import com.example.legend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements ApplicationRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Check if there are no admins in the database
        if (adminRepository.count() == 0) {
            // Create predefined admins
            Admin mainAdmin = new Admin();
            mainAdmin.setUsername("mainAdmin");
            mainAdmin.setPassword(passwordEncoder.encode("password")); // Encode the password
            // Set other admin properties if needed

            Admin otherAdmin = new Admin();
            otherAdmin.setUsername("otherAdmin");
            otherAdmin.setPassword(passwordEncoder.encode("password")); // Encode the password
            // Set other admin properties if needed

            // Save admins to the database
            adminRepository.save(mainAdmin);
            adminRepository.save(otherAdmin);

            // Assign roles (for example, assigning roles to the admins)
            // You can have a separate Role entity and manage roles accordingly

            // For simplicity, let's assume a role field in the Admin entity
            mainAdmin.setRole("ADMIN"); // Assign roles as needed
            otherAdmin.setRole("ADMIN"); // Assign roles as needed

            adminRepository.save(mainAdmin);
            adminRepository.save(otherAdmin);
        }
    }
}
