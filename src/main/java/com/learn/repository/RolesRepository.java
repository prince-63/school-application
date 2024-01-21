package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles getByRoleName(String role);
}
