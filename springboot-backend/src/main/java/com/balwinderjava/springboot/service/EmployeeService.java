package com.balwinderjava.springboot.service;

import com.balwinderjava.springboot.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    void delete(Employee employee);
}
