package com.kristi.repository.test;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.kristi.model.Department;
import com.kristi.repository.DepartmentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class DepartmentRepositoryTest {
	
	/*
	 * We will be using TestEntityManager interface, which is used to create an remove
	 * entity instances
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void testDepartment() {
		Department dept = getDepartment();
		Department savedInDb = entityManager.persist(dept);
		Department getFromDb = departmentRepository
				.findById
				(savedInDb.getId())
				.orElse(null);
		/*
		 * Asserting that the value saved in the database is the same as
		 * the value that is fetched from the database
		 */
		assertThat(getFromDb).isEqualTo(savedInDb);
		/*
		 * Asserting that the repository size is not zero
		 */
		assertThat(departmentRepository.count()).isNotZero();
		System.out.println("DEPARTMENT REPOSITORY SUCCESSFULLY TESTED!");
		
	}
	
	/*
	 * Method that creates and returns a department instance
	 */
	private Department getDepartment() {
		Department department = new Department();
		department.setName("Departamenti i Kimise");
		
		return department;
	}
	
	

}




