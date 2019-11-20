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
import com.kristi.model.DepartmentManager;
import com.kristi.model.Employee;
import com.kristi.model.Title;
import com.kristi.repository.DepartmentManagerRepository;
import com.kristi.repository.DepartmentRepository;
import com.kristi.repository.EmployeeRepository;
import com.kristi.service.DepartmentManagerService;


@RestController
@RequestMapping("/restAPI")
public class DepartmentManagerController {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private DepartmentManagerService departmentManagerService;
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a GET request, mapping to /departments/{departmentId}/departmentManager
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, a list of all the department managers for the given
	 * department will be shown
	 */
	@GetMapping("/departments/{departmentId}/departmentManager")
	public ResponseEntity<List<DepartmentManager>>  findAll(@PathVariable(value = "departmentId")
	Long departmentId) {
		return ResponseEntity.ok(departmentManagerService.findAll(departmentId));
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a POST request, mapping to /departments/{departmentId}/employees/
	 * {employeeId}/departmentManager
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, the service will take the departmentManager body, the id of a department, 
	 * the id of an employee. By invoking the method from the service object, the given employee
	 * will become a manager of the given department
	 */
	@PostMapping("/departments/{departmentId}/employees/{employeeId}/departmentManager")
	public ResponseEntity createDepartmentManager(@PathVariable(value = "departmentId")
	Long departmentId, @PathVariable(value = "employeeId") Long employeeId, 
	@Valid @RequestBody DepartmentManager departmentManager) {
		return ResponseEntity.ok(departmentManagerService.save(departmentManager, 
				departmentId,
				employeeId));
	}

}
