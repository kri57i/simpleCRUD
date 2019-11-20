package com.kristi.repository;

import com.kristi.model.Employee;
import com.kristi.model.Title;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long>{
	/*
	 * The following method will be implemented to return a list of titles
	 * that belong to an employee with the given id
	 */
	List<Title> findByEmployeeId(Long employeeId); 
	
	/*
	 * The following query updates the date of the title with the given name,
	 * belonging to the employee with the given id
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE titles t SET t.to_date = :new_date WHERE t.employee_id = :emp_id "
			+ "AND t.title = :title_name", 
	nativeQuery = true)
	
	void terminateTitle(@Param("emp_id") Long emp_id, 
			@Param("new_date") Date new_date, 
			@Param("title_name") String title_name);
	
	/*
	 * The following query returns a title with the given name,
	 * which is the primary key for a title in the title's table
	 */
	@Query(value = "SELECT * FROM titles t WHERE t.title = :title_name", 
			nativeQuery = true)
	Optional<Title> getTitle(@Param("title_name") String title_name);
	
}
