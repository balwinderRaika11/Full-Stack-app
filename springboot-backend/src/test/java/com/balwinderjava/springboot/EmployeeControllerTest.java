package com.balwinderjava.springboot;

import com.balwinderjava.springboot.controller.EmployeeController;
import com.balwinderjava.springboot.model.Employee;
import com.balwinderjava.springboot.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
    }

    @Test
    void shouldGetAllEmployees() throws Exception {
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void shouldGetEmployeeById() throws Exception {
        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailId").value("john.doe@example.com"));
    }

    @Test
    void shouldUpdateEmployee() throws Exception {
        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeService).delete(employee);

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true));
    }
}