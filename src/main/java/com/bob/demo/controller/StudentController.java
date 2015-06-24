package com.bob.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bob.demo.model.Student;
import com.bob.demo.model.StudentLogin;
import com.bob.demo.service.StudentService;

@Controller
@SessionAttributes("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "jspDemo/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "demo/signup.jsp";
    }

    @RequestMapping(value = "jspDemo/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("student") Student student, Model model) {
        if (studentService.getStudentByUserName(student.getUserName())) {
            model.addAttribute("message", "User Name exists. Try another user name");
            return "demo/signup.jsp";
        } else {
            studentService.insertStudent(student);
            model.addAttribute("message", "Saved student details");
            return "redirect:login.html";
        }
    }

    @RequestMapping(value = "jspDemo/login", method = RequestMethod.GET)
    public String login(Model model) {
        StudentLogin studentLogin = new StudentLogin();
        model.addAttribute("studentLogin", studentLogin);
        return "demo/login.jsp";
    }

    @RequestMapping(value = "jspDemo/login", method = RequestMethod.POST)
    public String login(@ModelAttribute StudentLogin studentLogin) {
        boolean found = studentService.getStudentByLogin(studentLogin.getUserName(), studentLogin.getPassword());
        if (found) {
            return "demo/success.jsp";
        } else {
            return "demo/failure.jsp";
        }
    }
}
