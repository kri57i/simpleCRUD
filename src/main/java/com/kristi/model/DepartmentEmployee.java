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
@Table(name = "department_employee")
@EntityListeners(AuditingEntityListener.class)
public class DepartmentEmployee {
	
	//primary key for table department employee
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * A department employee has a many to one relationship with employee,
	 * which means that a department employee is a single employee
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	/*
	 * A department employee has a many to one relationship with department,
	 * which means that e department employee belongs to a single department
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;
	
	@Column(nullable = false, 
			updatable = false, 
			name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;

	//implementation of getters and setters in the following section
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
}
