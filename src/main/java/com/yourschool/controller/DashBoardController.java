package com.yourschool.controller;

import com.yourschool.config.YourSchoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yourschool.model.Person;
import com.yourschool.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashBoardController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private YourSchoolProperties yourSchoolProperties;

    @RequestMapping("/dashboard")
    public String displayDashBoard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson", person);
        if (null != person.getYourClass() && null != person.getYourClass().getName()) {
            model.addAttribute("enrolledClass", person.getYourClass().getName());
        }
        return "dashboard.html";
    }
}
