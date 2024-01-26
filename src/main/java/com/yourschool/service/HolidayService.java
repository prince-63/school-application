package com.yourschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourschool.model.Holiday;
import com.yourschool.repository.HolidayRepository;

@Service
public class HolidayService {
    @Autowired
    private HolidayRepository holidayRepository;

    public Iterable<Holiday> findAllHolidays() {
        return holidayRepository.findAll();
    }

}
