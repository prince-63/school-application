package com.yourschool.repository;

import com.yourschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "courses")
public interface CourseRepository extends JpaRepository<Courses, Integer> {
}
