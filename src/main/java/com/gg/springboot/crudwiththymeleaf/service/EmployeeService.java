package com.gg.springboot.crudwiththymeleaf.service;

import com.gg.springboot.crudwiththymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
