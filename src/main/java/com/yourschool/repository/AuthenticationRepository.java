package com.yourschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourschool.model.Person;

@Repository
public interface AuthenticationRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);

}
