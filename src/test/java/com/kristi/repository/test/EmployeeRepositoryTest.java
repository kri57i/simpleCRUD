package com.kristi.repository.test;


import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.kristi.model.Employee;
import com.kristi.repository.EmployeeRepository;


import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class EmployeeRepositoryTest {
	
	/*
	 * We will be using TestEntityManager interface, which is used to create an remove
	 * entity instances
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testEmployee() {
		Employee emp = getEmployee();
		Employee savedInDb = entityManager.persist(emp);
		Employee getFromDb = employeeRepository
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
		assertThat(employeeRepository.count()).isNotZero();
		System.out.println("EMPLOYEE REPOSITORY SUCCESSFULLY TESTED!");
		
	}
	
	/*
	 * Method that creates and returns an employee instance
	 */
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("Kristi");
		employee.setLastName("Beshello");
		employee.setGender('M');
		employee.setHireDate(new Date());
		employee.setBirthDate(new Date());
		
		return employee;
	}

}




