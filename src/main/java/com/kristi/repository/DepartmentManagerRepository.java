package com.kristi.repository;


import com.kristi.model.DepartmentManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, 
Long>{
	/*
	 * The following method will be implemented in order to return a list of department managers,
	 * according to a given department id(they belong to the given department)
	 */
	List<DepartmentManager> getByDepartmentId(Long departmentId); 
	
	/*
	 * The following query will set a to_date to all the department managers that belong to the department 
	 * with the given id, in order to indicate the terminating date of the department
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE department_manager dm SET dm.to_date = :new_date WHERE dm.department_id = :dept_id",
			nativeQuery = true)
	/*
	 * The method will be called inside the body of the method that sets a terminating date to the department
	 */
	void updateManagers(@Param("dept_id") Long dept_id, 
			@Param("new_date") Date new_date);
}
