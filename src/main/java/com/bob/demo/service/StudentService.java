package com.bob.demo.service;

import com.bob.demo.model.Student;

public interface StudentService {

    void insertStudent(Student student);

    boolean getStudentByLogin(String userName, String password);

    boolean getStudentByUserName(String userName);
}
