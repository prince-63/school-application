package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.model.Roles;
import com.learn.repository.RolesRepository;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public Roles getByRoleName(String role) {
        return rolesRepository.getByRoleName(role);
    }
}
