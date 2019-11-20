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
import com.kristi.model.Title;
import com.kristi.repository.DepartmentRepository;
import com.kristi.repository.EmployeeRepository;
import com.kristi.repository.TitleRepository;
import com.kristi.service.TitleService;

@RestController
@RequestMapping("/restAPI")
public class TitleController {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private TitleService titleService;
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a GET request, mapping to /employees/{employeeId}/titles
	 * The return statement will return a response entity with a service method invoking inside the "ok" body
	 * When mapped to this request, a list of titles for the given employee will be shown
	 */
	@GetMapping("/employees/{employeeId}/titles")
	public ResponseEntity<List<Title>> findAll(@PathVariable(value = "employeeId") Long employeeId) {
		return ResponseEntity.ok(titleService.findAll(employeeId));
	}
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a POST request, mapping to /employees/{employeeId}/titles
	 * The method will take an employee id and employee body as an argument, and will
	 * invoke the method save from the service, saving the given employee  
	 */
	@PostMapping("/employees/{employeeId}/titles")
	public ResponseEntity createTitle(@PathVariable(value = "employeeId") 
	Long employeeId, @Valid @RequestBody Title title) {
		return ResponseEntity.ok(titleService.save(title, employeeId));
	}
	
	
	/*
	 * The following method uses a ResponseEntity to represent a http response
	 * The method will use a PUT request, mapping to /employees/{employeeId}/titles/
	 *{title_name}/terminate
	 * The method will take an employee id and title name as an argument, and will
	 * invoke the method updateTitle from the title service, in order to terminate
	 * the requested title(changing the to_date field to the current date)
	 */
	@PutMapping("/employees/{employeeId}/titles/{title_name}/terminate")
	public ResponseEntity<Title> updateTitle(@PathVariable(value = "employeeId") Long employeeId, 
			@PathVariable(value = "title_name") String title_name) {
		return ResponseEntity.ok(titleService.updateTitle(employeeId, title_name));
	}
	
}
