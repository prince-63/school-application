package com.learn.repository;

import com.learn.model.YourClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YourClassesRepository extends JpaRepository<YourClass, Integer> {
}
