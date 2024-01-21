package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.model.Person;

@Repository
public interface AuthenticationRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);

}
