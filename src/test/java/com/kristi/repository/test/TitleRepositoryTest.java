package com.kristi.repository.test;


import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.kristi.model.Employee;
import com.kristi.model.Title;
import com.kristi.repository.TitleRepository;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TitleRepositoryTest {

	/*
	 * We will be using TestEntityManager interface, which is used to create an remove
	 * entity instances
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Test
	public void testTitle() {
		Title tl = getTitle();
		Title savedInDb = entityManager.persist(tl);
		Title getFromDb = titleRepository.getTitle
				(tl.getTitle())
				.orElse(null);
		/*
		 * Asserting that the value saved in the database is the same as
		 * the value that is fetched from the database
		 */
		assertThat(savedInDb).isEqualTo(getFromDb);
		/*
		 * Asserting that the repository size is not zero
		 */
		assertThat(titleRepository.count()).isNotZero();
		System.out.println("TITLE REPOSITORY SUCCESSFULLY TESTED!");
	}
	
		/*
		 * Method that creates and returns a title instance
		 */
	private Title getTitle() {
		Employee emp = getEmployee();
		Title title = new Title();
		title.setTitle("Pool master");
		title.setFrom_date(new Date());
		title.setEmployee(emp);
		
		return title;
		
	}
	
	/*
	 * Method that creates and returns an employee instance
	 */
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("Rei");
		employee.setLastName("Beshello");
		employee.setGender('M');
		employee.setHireDate(new Date());
		employee.setBirthDate(new Date());
		
		return employee;
	}
}
