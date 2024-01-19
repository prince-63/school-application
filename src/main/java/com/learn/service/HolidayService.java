package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.model.Holiday;
import com.learn.repository.HolidayRepository;

@Service
public class HolidayService {
    @Autowired
    private HolidayRepository holidayRepository;

    public Iterable<Holiday> findAllHolidays() {
        return holidayRepository.findAll();
    }

}
