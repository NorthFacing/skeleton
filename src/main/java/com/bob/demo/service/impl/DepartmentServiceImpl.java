package com.bob.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bob.demo.model.Department;
import com.bob.demo.service.DepartmentService;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<Department> listDepartment() {
        List<Department> list = new ArrayList<Department>();

        list.add(new Department(1, "Operations", "Chicago"));
        list.add(new Department(2, "HR", "Hanoi"));
        return list;
    }
}
