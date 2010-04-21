package com.dhev.constraints.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dhev.constraints.SizeEL;

@Entity
public class SizeELValidatorModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@SizeEL(min = "2", max = "5")
	private String[] size2_5;

	@SizeEL(max = "5")
	private String[] testDefaultMin;

	@SizeEL(min = "2")
	private String[] testDefaultMax;

	@SizeEL
	private String[] testDefaults;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getSize2_5() {
		return size2_5;
	}

	public void setSize2_5(String[] range2_5) {
		this.size2_5 = range2_5;
	}

	public String[] getTestDefaultMin() {
		return testDefaultMin;
	}

	public void setTestDefaultMin(String[] testDefaultMin) {
		this.testDefaultMin = testDefaultMin;
	}

	public String[] getTestDefaultMax() {
		return testDefaultMax;
	}

	public void setTestDefaultMax(String[] testDefaultMax) {
		this.testDefaultMax = testDefaultMax;
	}

	public String[] getTestDefaults() {
		return testDefaults;
	}

	public void setTestDefaults(String[] testDefaults) {
		this.testDefaults = testDefaults;
	}

}