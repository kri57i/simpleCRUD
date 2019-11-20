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
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

	//Primary key for table "employees"
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "first_name")
	private String firstName;
	
	@Column(nullable = false, name = "last_name")
	private String lastName;
	
	@Column(nullable = false, name = "gender")
	private char gender;
	
	/*
	 * The following column has to be non-updatable, because
	 * the hiring date of an employee is always unchangeable
	 */
	@Column(nullable = false,
			updatable = false, 
			name = "hire_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date hireDate;
	
	@Column(nullable = false, name = "birth_date")
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date birthDate;
	
	/*An employee has a "one to many" relationship with Title,
	 * which means that an employee can hold multiple titles
	 */
	@OneToMany(mappedBy = "employee", cascade = {
			CascadeType.ALL
	})
	private List<Title> titles;
	
	/*An employee has a "one to many" relationship with Department Manager,
	 * which means that an employee can be the manager of multiple departments
	 */
	@OneToMany(mappedBy = "employee", 
			cascade = {
			CascadeType.ALL
	})
	private List<DepartmentManager> departmentManagers;
	
	/*
	 * An employee has a "one to many" relationship with Department Employee,
	 * which means that an employee can be part of multiple departmetns
	 */
	@OneToMany(mappedBy = "employee", 
			cascade = {
			CascadeType.ALL
	})
	private List<DepartmentEmployee> departmentEmployees;
	
	//implementation of getters and setters in the following section
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	
	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}
	
	public void setDepartmentManagers(List<DepartmentManager> departmentManagers) {
		this.departmentManagers = departmentManagers;
	}
	
	public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) {
		this.departmentEmployees = departmentEmployees;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
