package com.yourschool.controller;

import com.yourschool.model.Contact;
import com.yourschool.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {

        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessage(contact);
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model, @PathVariable(value = "pageNum") int pageNum, @RequestParam(value = "sortField") String sortField, @RequestParam(value = "sortDir") String sortDir) {
        Page<Contact> pageListMessages = contactService.findMsgWithOpenStatus(pageNum, sortField, sortDir);
        ModelAndView modelAndView = new ModelAndView("message.html");
        List<Contact> contactMsgs = pageListMessages.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", pageListMessages.getTotalPages());
        model.addAttribute("totalMsgs", pageListMessages.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMessages(@RequestParam int id) {
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }
}