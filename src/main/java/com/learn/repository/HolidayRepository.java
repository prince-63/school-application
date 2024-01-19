package com.learn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.model.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {
    
}
