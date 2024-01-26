package com.learn.controller;

import com.learn.model.Contact;
import com.learn.model.Courses;
import com.learn.model.Person;
import com.learn.model.YourClass;
import com.learn.repository.CourseRepository;
import com.learn.repository.PersonRepository;
import com.learn.repository.YourClassesRepository;
import com.learn.service.ContactService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private YourClassesRepository yourClassesRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/displayMessages")
    public ModelAndView displayMessages(Model model) {
        List<Contact> contactList = contactService.findMsgWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("message.html");
        modelAndView.addObject("contactMsg", contactList);
        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMessages(@RequestParam int id) {
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }

    @GetMapping("/displayClasses")
    public ModelAndView displayClasses() {
        List<YourClass> yourClasses = yourClassesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("yourClasses", yourClasses);
        modelAndView.addObject("yourClass", new YourClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(@Valid @ModelAttribute("yourClass") YourClass yourClass) {
        yourClassesRepository.save(yourClass);
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @GetMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int id) {
        Optional<YourClass> yourClass = yourClassesRepository.findById(id);
        for (Person person : yourClass.get().getPersons()) {
            person.setYourClass(null);
            personRepository.save(person);
        }
        yourClassesRepository.deleteById(id);
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(@RequestParam(value = "classId", required = true) Integer classId, @RequestParam(value = "error", required = false) String error, HttpSession session) {
        String errorMessage = null;
        Optional<YourClass> presentClass = yourClassesRepository.findById(classId);
        ModelAndView modelAndView = new ModelAndView("students.html");
        modelAndView.addObject("person", new Person());
        modelAndView.addObject("yourClass", presentClass.get());
        session.setAttribute("yourClass", presentClass.get());
        if (error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(@ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person personEntity = personRepository.readByEmail(person.getEmail());
        YourClass yourClass = (YourClass) session.getAttribute("yourClass");

        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + yourClass.getClassId()
                    + "&error=true");
            return modelAndView;
        }

        personEntity.setYourClass(yourClass);
        personRepository.save(personEntity);
        yourClass.getPersons().add(personEntity);
        yourClassesRepository.save(yourClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + yourClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam(value = "personId", required = true) Integer personId, HttpSession session) {
        YourClass yourClass = (YourClass) session.getAttribute("yourClass");
        Optional<Person> personEntity = personRepository.findById(personId);
        personEntity.get().setYourClass(null);
        yourClass.getPersons().remove(personEntity.get());
        personRepository.save(personEntity.get());
        YourClass savedClass = yourClassesRepository.save(yourClass);
        session.setAttribute("yourClass", savedClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + yourClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses() {
        List<Courses> courses = courseRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("course", new Courses());
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(@ModelAttribute("course") Courses courses) {
        ModelAndView modelAndView = new ModelAndView();
        courseRepository.save(courses);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(@RequestParam(value = "courseId", required = true) Integer courseId, @RequestParam(value = "error", required = false) String error, HttpSession session) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> course = courseRepository.findById(courseId);
        modelAndView.addObject("courses", course.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("courses", course.get());
        if (error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(@ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Person personEntity = personRepository.readByEmail(person.getEmail());
        Courses course = (Courses) session.getAttribute("courses");

        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/viewStudents?courseId=" + course.getCourseId() + "&error=true");
            return modelAndView;
        }

        personEntity.getCourses().add(course);
        course.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses", course);
        modelAndView.setViewName("redirect:/admin/viewStudents?courseId=" + course.getCourseId());
        return modelAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(@RequestParam(value = "personId") Integer personId, HttpSession session) {
        Optional<Person> personEntity = personRepository.findById(personId);
        Courses course = (Courses) session.getAttribute("courses");
        course.getPersons().remove(personEntity.get());
        personEntity.get().getCourses().remove(course);
        personRepository.save(personEntity.get());
        session.setAttribute("courses", course);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?courseId=" + course.getCourseId());
        return modelAndView;
    }
}
