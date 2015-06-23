package com.bob.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bob.demo.model.Department;
import com.bob.demo.service.DepartmentService;

@Controller
public class DepartController {

    @Autowired
    private DepartmentService deptService;

    @RequestMapping(value = { "/vmDemo", "/vmWelcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        List<Department> list = deptService.listDepartment();
        model.addAttribute("departments", list);
        return "index";
    }
}
