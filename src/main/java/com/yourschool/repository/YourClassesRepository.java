package com.yourschool.repository;

import com.yourschool.model.YourClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "yourclasses")
public interface YourClassesRepository extends JpaRepository<YourClass, Integer> {
}
