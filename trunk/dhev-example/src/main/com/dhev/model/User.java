package com.dhev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Max;
import org.jboss.seam.annotations.Name;

import com.dhev.MinEL;

@Name("user")
@Entity
@Table(name = "USER")
@SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", initialValue = 1)
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166255867788169445L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	private Long id;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String userName;// TODO: minLenght is 5

	@Column
	@Max(20)
	@MinEL(value = "#{systemConfiguration.minAge}", includeLimit = true, message = "{validator.minAge}")
	private Integer age;// TODO: min is 18

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
