package com.learn.controller;

import com.learn.model.Holiday;
import com.learn.service.HolidayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidaysController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/holidays")
    public String displayHolidays(Model model) {
        Iterable<Holiday> holidays = holidayService.findAllHolidays();

        List<Holiday> holidayList = StreamSupport
                .stream(holidays.spliterator(), false)
                .collect(Collectors.toList());

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type))
                            .collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}