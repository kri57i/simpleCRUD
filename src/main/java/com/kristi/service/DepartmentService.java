package com.kristi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristi.model.Department;
import com.kristi.model.DepartmentEmployee;
import com.kristi.model.DepartmentManager;
import com.kristi.repository.DepartmentEmployeeRepository;
import com.kristi.repository.DepartmentManagerRepository;
import com.kristi.repository.DepartmentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
	
	//Autowired annotation is used, in order to inject collaborating beans
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired 
	private DepartmentEmployeeRepository departmentEmployeeRepository;
	
	@Autowired
	private DepartmentManagerRepository departmentManagerRepository;
	
	@Autowired 
	private DepartmentManagerService departmentManagerService;
	
	@Autowired
	private DepartmentEmployeeService departmentEmployeeService;
	
	/*
	 * The following method returns a list of all departments
	 */
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}
	
	/*
	 * The following method takes an id as an argument and returns the department
	 * if it is present in the repository, otherwise it throws an exception
	 */
	public <Optional>Department getDepartmentById(Long departmentId) {
		if(!departmentRepository
				.findById(departmentId)
				.isPresent()) {
			throw new IllegalArgumentException("Department doesn't exist!");
		}
		return departmentRepository
				.findById(departmentId)
				.orElse(null);
	}
	
	//The following method takes the body of a department and saves the department
	public Department save(Department department) {
		return departmentRepository.save(department);
	}
	
	
	/*
	 * The following function takes a department id and department as an argument, checks
	 * whether the department exists or not and then it sets a terminating date for the department
	 * (current date of the moment we call the method).
	 * It also runs two queries: one query to update all the managers of the department
	 * and changing their to_date, which means that they job on this department is terminated
	 * and one other query, completing the same task for the terminated department's employees
	 */
	public Department terminateDepartment(Long departmentId, Department department) {
		if(!departmentRepository
				.findById(departmentId)
				.isPresent()) {
			throw new IllegalArgumentException("Department doesn't exist!");
		}
		
		Department currentDepartment = departmentRepository
				.findById(departmentId)
				.orElse(null);
		if(department.getName() != null) {
		currentDepartment.setName(department.getName());
		}
		currentDepartment.setToDate(new Date());
		
		//running the queries in the two of the following instructions
		departmentManagerRepository.updateManagers(departmentId, new Date());
		
		departmentEmployeeRepository.updateEmployees(departmentId, new Date());
		
		Department updatedDepartment = departmentRepository.save(currentDepartment);
		
		return updatedDepartment;
	}
	
	
}
