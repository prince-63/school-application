package com.yourschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourschool.model.Roles;
import com.yourschool.repository.RolesRepository;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public Roles getByRoleName(String role) {
        return rolesRepository.getByRoleName(role);
    }
}
