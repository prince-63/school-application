package com.yourschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.yourschool.model.Roles;

@Repository
@RepositoryRestResource(path = "roles")
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles getByRoleName(String role);
}
