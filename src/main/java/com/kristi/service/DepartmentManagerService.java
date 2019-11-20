package com.kristi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.kristi.model.Department;
import com.kristi.model.DepartmentManager;
import com.kristi.model.Employee;
import com.kristi.repository.DepartmentRepository;
import com.kristi.repository.EmployeeRepository;
import com.kristi.repository.DepartmentManagerRepository;

@Service
public class DepartmentManagerService {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DepartmentManagerRepository departmentManagerRepository;
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	/*
	 * The following method takes the id of a department as an argument
	 * and checks if the department is present or not.
	 * If the department is present, it calls the getByDepartmentId
	 * which returns a list of department managers of the given department
	 */
	public List<DepartmentManager> findAll(Long departmentId) {
		if(!departmentRepository
				.findById(departmentId)
				.isPresent()) {
			throw new IllegalArgumentException("Department not found!");
		}
		return departmentManagerRepository.getByDepartmentId(departmentId);
	}
	
	
	/*
	 * The following method saves a new department manager.
	 * To accomplish that, it takes the id of the employee that will soon be the manager,
	 * the id of the department and the body of the department manager.
	 * If the employee and department exist, the new department manager is saved into the database
	 */
	public DepartmentManager save(DepartmentManager departmentManager, 
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
		Employee employee = employeeRepository
				.findById(employeeId)
				.orElse(null);
		Department department = departmentRepository
				.findById(departmentId)
				.orElse(null);
		//setting the department field for the department manager(set method in department manager class)
		departmentManager.setDepartment(department);
		//setting the employee field for the department manager(- || -)
		departmentManager.setEmployee(employee);
		
		return departmentManagerRepository.save(departmentManager);
	}
	
}
