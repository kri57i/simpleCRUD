package com.kristi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "department_manager")
@EntityListeners(AuditingEntityListener.class)
public class DepartmentManager {
	
	//primary key for table "Department Manager"
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * Manager's hiring date can not be changed
	 */
	@Column(nullable = false, 
			updatable = false, 
			name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date hireDate;
	
	/*
	 * A department manager has a "many to one" relationship with department,
	 * which means that a department manager belongs to a single department
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;
	
	/*
	 * A department manager has a "many to one" relationship with employee, 
	 * which means that a department manager is a single employee
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "to_date")
	private Date toDate;

	//implementation of getters and setters in the following section
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}




