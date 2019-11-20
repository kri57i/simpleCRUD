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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "departments")
@EntityListeners(AuditingEntityListener.class)
public class Department {
	
	//primary key for table "department"
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "dept_name")
	private String name;
	
	/*
	 * A department has a "one to many" relationship with department employees,
	 * which means that a department can have multiple department employees
	 */
	@OneToMany(mappedBy = "department", cascade = {
			CascadeType.ALL
	})
	private List<DepartmentEmployee> departmentEmployees;
	
	/*
	 * A department has a "one to many" relationship with department managers,
	 * which means that a department can have multiple department managers
	 */
	@OneToMany(mappedBy = "department", cascade = {
			CascadeType.ALL
	})
	private List<DepartmentManager> departmentManagers;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) {
		this.departmentEmployees = departmentEmployees;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}
