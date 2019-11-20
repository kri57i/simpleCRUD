package com.kristi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kristi.model.Department;
import com.kristi.model.Employee;
import com.kristi.repository.DepartmentRepository;
import com.kristi.repository.EmployeeRepository;
import com.kristi.service.EmployeeService;

@RestController
@RequestMapping("/restAPI")
public class EmployeeController {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private EmployeeService employeeService;
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a GET request, mapping to /employees
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, a list of all the employees will be shown
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(employeeService.findAll());
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a GET request, mapping to /employees/{employeeId}
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, an employee with the given id(if existing) will be shown
	 */
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "employeeId") 
	Long employeeId) {
		return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a POST request, mapping to /employees
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, the service will take the employee body as an argument,
	 * and will save the employee
	 */
	@PostMapping("/employees")
	public ResponseEntity createEmployee(@Valid @RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.save(employee));
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a PUT request, mapping to /employees
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, the service will take the employee id and body as an argument,
	 * and will update the given employee
	 */
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity updateEmployee(@PathVariable(value = "employeeId")
	Long employeeId, @Valid @RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.update(employeeId, employee));
	}
	
}