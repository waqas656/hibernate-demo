package com.waqas.hibernatedemo.dao;

import com.waqas.hibernatedemo.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    Student findByEmail(String email);

    Student findByFirstName(String firstName);

    Student findByFirstAndLastName(String firstName, String lastName);

    Student findByLastName(String lastName);

    Student updateLastNameById(Integer id, String lastName);
    int updateAllLastNames(String lastName);

    void deleteStudentUsingId(int id);

    int deleteStudentUsingLastName(String lastName);
}
