package com.balwinderjava.springboot.controller;
import com.balwinderjava.springboot.exception.ShouldNotHappenException;
import com.balwinderjava.springboot.repository.EmployeeRepository;
import com.balwinderjava.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balwinderjava.springboot.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * REST controller for managing employee records.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	/**
	 * Retrieve all employee records.
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeService.findAll();
	}

	/**
	 * Create new employee.
	 */
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}

	/**
	 * Get employee details by Id
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ShouldNotHappenException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

	/**
	 * Update employee record by Ie.
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ShouldNotHappenException("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeService.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * Delete employee record by ID.
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ShouldNotHappenException("Employee not exist with id :" + id));
		
		employeeService.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
