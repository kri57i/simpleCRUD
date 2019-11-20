package com.kristi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kristi.model.Department;
import com.kristi.repository.DepartmentRepository;
import com.kristi.service.DepartmentService;

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

@RestController
@RequestMapping("/restAPI")
public class DepartmentController {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private DepartmentService departmentService;
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a GET request, mapping to /departments
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, a list of all the departments will be shown
	 */
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> findAll() {
		return ResponseEntity.ok(departmentService.findAll());
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a GET request, mapping to /departments/{departmentId}
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, a department for the given id will be shown
	 */
	@GetMapping("/departments/{departmentId}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "departmentId")
	Long departmentId) {
		return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a POST request, mapping to /departments
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, the service will invoke the save method with the department
	 * body as an argument, and will save the new department
	 */
	@PostMapping("/departments")
	public ResponseEntity createDepartment(@Valid @RequestBody Department department) {
		return ResponseEntity.ok(departmentService.save(department));
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a PUT request, mapping to /departments/{departmentId}
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, as indicated in the database schema, the given department
	 * will take a to_date the current date, which means that from this moment, the department is terminated
	 * The service will also synchronize managers and employess, by updating their to_date fields too
	 */
	@PutMapping("/departments/{departmentId}")
	public ResponseEntity<Department> updateDepartment(@PathVariable(value = "departmentId")
	Long departmentId, @Valid @RequestBody Department department) {
		return ResponseEntity.ok(departmentService.terminateDepartment(departmentId, department));
	}
	
}
