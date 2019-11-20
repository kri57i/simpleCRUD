package com.kristi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.kristi.model.Department;
import com.kristi.model.DepartmentEmployee;
import com.kristi.model.DepartmentManager;
import com.kristi.model.Employee;
import com.kristi.repository.DepartmentRepository;
import com.kristi.repository.EmployeeRepository;
import com.kristi.repository.DepartmentEmployeeRepository;
import com.kristi.repository.DepartmentManagerRepository;

@Service
public class DepartmentEmployeeService {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private DepartmentEmployeeRepository departmentEmployeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/*
	 * The following method takes the id of a department as an argument
	 * and checks if the department is present or not.
	 * If the department is present, it calls the getByDepartmentId
	 * which returns a list of department employees of the given department
	 */
	public List<DepartmentEmployee> findAll(Long departmentId) {
		if(!departmentRepository
				.findById(departmentId)
				.isPresent()) {
			throw new IllegalArgumentException("Department doesn't exist!");
		}
		return departmentEmployeeRepository.getByDepartmentId(departmentId);
	}
	
	/*
	 * The following method saves a new department employee.
	 * To accomplish that, it takes the id of the employee that will soon be the department employee,
	 * the id of the department and the body of the department employee.
	 * If the employee and department exist, the new department employee is saved into the database
	 */
	public DepartmentEmployee save(DepartmentEmployee departmentEmployee, 
			Long departmentId, Long employeeId) {
		if(!departmentRepository
				.findById(departmentId)
				.isPresent()) {
			throw new IllegalArgumentException("Department doesn't exist!");
		}
		if(!employeeRepository
				.findById(employeeId)
				.isPresent()) {
			throw new IllegalArgumentException("Employee doesn't exist!");
		}
		Department department = departmentRepository
				.findById(departmentId)
				.orElse(null);
		Employee employee = employeeRepository
				.findById(employeeId)
				.orElse(null);
		
		//setting the department field for the department manager(set method in department employee class)
		departmentEmployee.setDepartment(department);
		//setting the employee field
		departmentEmployee.setEmployee(employee);
		return departmentEmployeeRepository.save(departmentEmployee);
	}
	
}
