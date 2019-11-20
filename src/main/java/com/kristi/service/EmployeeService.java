package com.kristi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.kristi.model.Employee;
import com.kristi.repository.EmployeeRepository;

@Service
public class EmployeeService {

	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/*
	 * The following method returns a list of all employees
	 */
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	//The following method takes the body of an employee and saves the employee
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	/*
	 * The following method takes an id as an argument and returns the employee
	 * if it is present in the repository, otherwise it throws an exception
	 */
	public <Optional>Employee getEmployeeById(Long employeeId) {
		if(!employeeRepository
				.findById(employeeId)
				.isPresent()) {
			throw new IllegalArgumentException("Employee doesn't exist!");
		}
		return employeeRepository
				.findById(employeeId)
				.orElse(null);
	}
	
	/*
	 * The following method takes an id as an argument and also the body of an employee
	 * and updates the employee, according to the changing or not values, given by the user
	 */
	public Employee update(Long employeeId, 
			Employee employee) {
		if(!employeeRepository.findById(employeeId).isPresent()) {
			throw new IllegalArgumentException("Employee not found!");
		}
		//getting the employee
		Employee currentEmployee = employeeRepository
				.findById(employeeId)
				.orElse(null);
		//updating the requested fields
		if(employee.getFirstName() != null) {
		currentEmployee.setFirstName(employee.getFirstName());
		}
		if(employee.getLastName() != null) {
		currentEmployee.setLastName(employee.getLastName());
		}
		currentEmployee.setGender(employee.getGender());
		
		
		Employee updatedEmployee = employeeRepository.save(currentEmployee);
		
		return updatedEmployee;
	}
}
