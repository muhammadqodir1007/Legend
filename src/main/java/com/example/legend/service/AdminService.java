package com.example.legend.service;

import com.example.legend.entity.Admin;
import com.example.legend.entity.ApiResponse;
import com.example.legend.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {


    AdminRepository adminRepository;


    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin getByUsername(String username) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isEmpty()) {
            throw new UsernameNotFoundException("admin not found");
        }
        return admin.get();
    }

    public ApiResponse insert(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setRole(admin.getRole());
        admin1.setDescription(admin.getDescription());
        admin1.setUsername(admin.getUsername());
        admin1.setPassword(admin.getPassword());
        Admin saved = adminRepository.save(admin1);
        return new ApiResponse(true, "success", saved);
    }

    public ApiResponse delete(int id) {
        adminRepository.deleteById(id);
        return new ApiResponse(true, "success");
    }



}
