package com.yourschool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yourschool.model.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {
    
}
