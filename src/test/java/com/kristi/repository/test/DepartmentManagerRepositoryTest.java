package com.kristi.repository.test;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.kristi.model.Department;
import com.kristi.model.Employee;
import com.kristi.model.DepartmentManager;
import com.kristi.repository.DepartmentManagerRepository;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentManagerRepositoryTest {

	/*
	 * We will be using TestEntityManager interface, which is used to create an remove
	 * entity instances
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private DepartmentManagerRepository departmentManagerRepository;
	
	@Test
	public void testDepartmentManager() {
		DepartmentManager deptManager = getDepartmentManager();
		DepartmentManager savedInDb = entityManager.persist(deptManager);
		DepartmentManager getFromDb = departmentManagerRepository
				.findById
				(savedInDb.getId())
				.orElse(null);
		/*
		 * Asserting that the value saved in the database is the same as
		 * the value that is fetched from the database
		 */
		assertThat(savedInDb).isEqualTo(getFromDb);
		/*
		 * Asserting that the repository size is not zero
		 */
		assertThat(departmentManagerRepository.count()).isNotZero();
		System.out.println("DEPARTMENT MANAGER REPOSITORY SUCCESSFULLY TESTED");
		
		
	}
	
	/*
	 * Method that creates and returns a departmentManager instance
	 */
	private DepartmentManager getDepartmentManager() {
		DepartmentManager deptMan = new DepartmentManager();
		deptMan.setEmployee(getEmployee());
		deptMan.setDepartment(getDepartment());
		deptMan.setHireDate(new Date());
		
		return deptMan;
	}
	
	/*
	 * Method that creates and returns an employee instance
	 */
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("Igli");
		employee.setLastName("Kajacka");
		employee.setGender('M');
		employee.setHireDate(new Date());
		employee.setBirthDate(new Date());
		
		return employee;
	}
	
	/*
	 * Method that creates and returns a department instance
	 */
	private Department getDepartment() {
		Department department = new Department();
		department.setName("Departamenti i Fizikes");
		
		return department;
	}
}
