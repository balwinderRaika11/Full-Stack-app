package com.balwinderjava.springboot;

import com.balwinderjava.springboot.model.Employee;
import com.balwinderjava.springboot.repository.EmployeeRepository;
import com.balwinderjava.springboot.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
    }

    @Test
    void shouldReturnAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
        assertEquals(1, employeeService.findAll().size());
    }

    @Test
    void shouldReturnEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeeService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    void shouldSaveEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee result = employeeService.save(employee);
        assertEquals("john.doe@example.com", result.getEmailId());
    }

    @Test
    void shouldDeleteEmployee() {
        doNothing().when(employeeRepository).delete(employee);
        assertDoesNotThrow(() -> employeeService.delete(employee));
        verify(employeeRepository, times(1)).delete(employee);
    }
}