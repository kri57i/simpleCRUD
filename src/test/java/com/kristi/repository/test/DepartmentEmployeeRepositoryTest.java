package com.kristi.repository.test;


import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.kristi.model.Department;
import com.kristi.model.DepartmentEmployee;
import com.kristi.model.Employee;
import com.kristi.repository.DepartmentEmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentEmployeeRepositoryTest {
	
	/*
	 * We will be using TestEntityManager interface, which is used to create an remove
	 * entity instances
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private DepartmentEmployeeRepository departmentEmployeeRepository;
	
	@Test
	public void testDepartmentEmployee() {
		DepartmentEmployee deptEmployee = getDepartmentEmployee();
		DepartmentEmployee savedInDb = entityManager.persist(deptEmployee);
		DepartmentEmployee getFromDb = departmentEmployeeRepository
				.findById(savedInDb.getId())
				.orElse(null);
		/*
		 * Asserting that the value saved in the database is the same as
		 * the value that is fetched from the database
		 */
		assertThat(savedInDb).isEqualTo(getFromDb);
		/*
		 * Asserting that the repository size is not zero
		 */
		assertThat(departmentEmployeeRepository.count()).isNotZero();
		System.out.println("DEPARTMENT EMPLOYEE REPOSITORY SUCCESSFULLY TESTED!");
	}
	
	/*
	 * Method that creates and returns a departmentEmployee instance
	 */
	public DepartmentEmployee getDepartmentEmployee() {
		DepartmentEmployee deptEmp = new DepartmentEmployee();
		deptEmp.setEmployee(getEmployee());
		deptEmp.setDepartment(getDepartment());
		deptEmp.setFromDate(new Date());
		
		return deptEmp;
	}
	
	/*
	 * Method that creates and returns an employee instance
	 */
	public Employee getEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("Hermes");
		employee.setLastName("Alillari");
		employee.setGender('M');
		employee.setHireDate(new Date());
		employee.setBirthDate(new Date());
		
		return employee;
	}
	
	/*
	 * Method that creates and returns a department instance
	 */
	public Department getDepartment() {
		Department department = new Department();
		department.setName("Departamenti i Kimise Industriale");
		
		return department;
	}
	
	

}
