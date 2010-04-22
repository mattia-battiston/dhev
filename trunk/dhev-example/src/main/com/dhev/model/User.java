/**
 * Copyright 2010, DHEV project's members and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.dhev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import com.dhev.constraints.AssertEL;
import com.dhev.constraints.DecimalMaxEL;
import com.dhev.constraints.LengthEL;
import com.dhev.constraints.MaxEL;
import com.dhev.constraints.MinEL;
import com.dhev.constraints.PatternEL;
import com.dhev.constraints.RangeEL;
import com.dhev.constraints.SizeEL;

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
	@LengthEL(max = "#{systemConfiguration.maxLengthName}", includeMax = false, message = "{validator.maxLengthExcluded}")
	private String name;

	@Column
	private String surname;

	@Column
	private String userName;// TODO: minLenght is 5

	@Column
	@MaxEL(value = "#{systemConfiguration.maxAge}")
	@MinEL(value = "#{systemConfiguration.minAge}", includeLimit = true, message = "{validator.minAge}")
	private Integer age;// TODO: min is 18

	@Column
	@PatternEL(regex = "#{systemConfiguration.emailPattern}")
	private String email;

	@Column
	@LengthEL(min = "#{systemConfiguration.minLengthZip}", max = "#{systemConfiguration.maxLengthZip}", includeMax = false)
	private String zip;

	@Column
	@RangeEL(min = "#{systemConfiguration.minRating}", max = "#{systemConfiguration.maxRating}", includeMin = false)
	private Integer rating;

	@Column
	@SizeEL(min = "#{systemConfiguration.minColors}", max = "#{systemConfiguration.maxColors}")
	private String[] colors;

	@Column
	@AssertEL(value = "#{systemConfiguration.mustReadDocs}")
	private Boolean hasReadDocs;

	@Column
	@DecimalMaxEL(value = "#{systemConfiguration.maxHeight}", includeLimit = false)
	private Double height;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public Boolean getHasReadDocs() {
		return hasReadDocs;
	}

	public void setHasReadDocs(Boolean hasReadDocs) {
		this.hasReadDocs = hasReadDocs;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

}
