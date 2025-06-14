package com.balwinderjava.springboot.service.impl;

import com.balwinderjava.springboot.model.Employee;
import com.balwinderjava.springboot.repository.EmployeeRepository;
import com.balwinderjava.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
