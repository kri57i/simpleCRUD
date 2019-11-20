package com.kristi.model;


import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "titles")
@EntityListeners(AuditingEntityListener.class)
public class Title {

	//primary key for table "titles"
	@Id
	private String title;
	
	/*
	 * A title's from_date can not be changed, so it must be marked as a non-nullable field
	 */
	@Column(nullable = false, name = "from_date")
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date from_date;
	
	/*
	 * Title has a "many to one" relationship with employee, which means
	 * that a title can belong only to one employee
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "to_date")
	private Date toDate;

	//implementation of getters and setters in the following section
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}







