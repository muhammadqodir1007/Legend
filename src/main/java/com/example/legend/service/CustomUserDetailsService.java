package com.example.legend.service;

import com.example.legend.entity.Admin;
import com.example.legend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Inject your AdminRepository to fetch admin details
    private final AdminRepository adminRepository;

    @Autowired
    public CustomUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch admin details from the repository using username
        Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Create a UserDetails object using fetched admin details
        return User.withUsername(admin.getUsername()).password(admin.getPassword()).roles("ADMIN") // Assign roles as needed
                .build();
    }
}
