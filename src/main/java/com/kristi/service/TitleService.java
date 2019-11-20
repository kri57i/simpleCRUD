package com.kristi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.kristi.model.Employee;
import com.kristi.model.Title;
import com.kristi.repository.EmployeeRepository;
import com.kristi.repository.TitleRepository;

@Service
public class TitleService {

	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	/*
	 * The following method returns a list of titles that belong
	 * to the employee with the id given as an argument
	 */
	public List<Title> findAll(Long employeeId) {
		if(!employeeRepository
				.findById(employeeId)
				.isPresent()) {
			throw new IllegalArgumentException("Employee doesn't exist!");
		}
		return titleRepository.findByEmployeeId(employeeId);
	}
	
	/*
	 * The following method takes the body of a title and the id of the employee
	 * that is likeable to get the title, and saves the title
	 */
	
	public Title save(Title title, Long employeeId) {
		if(!employeeRepository
				.findById(employeeId)
				.isPresent()) {
			throw new IllegalArgumentException("Employee doesn't exist!");
		}
		Employee employee = employeeRepository
				.findById(employeeId)
				.orElse(null);
		title.setEmployee(employee);
		return titleRepository.save(title);
	}
	
	/*
	 * The following method gets the title name as an arugment,
	 * and returns the title
	 */
	public Title getTitle(String title_name) {
		return titleRepository
				.getTitle(title_name)
				.orElse(null);
	}
	
	/*
	 * The following method takes two arguments: the id of an employee and the title name
	 * At first, it returns a title with the given name and terminates the title, belonging to the given
	 * employee. If the title doesn't belong to the employee, no changes will be made
	 */
	public Title updateTitle(Long employeeId, String title_name) {
		if(!employeeRepository
				.findById(employeeId)
				.isPresent()) {
			throw new IllegalArgumentException("Employee not found!");
		}
		Title currentTitle = titleRepository
				.getTitle(title_name)
				.orElse(null);
		
		titleRepository.terminateTitle(employeeId, 
				new Date(), 
				title_name);
		
		//returning the updated title
		return currentTitle;
	}
	
}
