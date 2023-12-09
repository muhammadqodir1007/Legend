package com.example.legend.controller;

import com.example.legend.entity.Admin;
import com.example.legend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admins")
@AllArgsConstructor
public class AdminController {


    AdminService adminService;


    @GetMapping
    public List<Admin> getAll() {
        return adminService.getAll();


    }


}
