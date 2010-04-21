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

	@SizeEL(min = "#{config.min}", max = "#{config.max}")
	private String[] sizeMin2Max5;

	@SizeEL(max = "#{config.max}")
	private String[] testDefaultMinMax5;

	@SizeEL(min = "#{config.min}")
	private String[] testMin2DefaultMax;

	@SizeEL
	private String[] testDefaultMinDefaultMax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getSizeMin2Max5() {
		return sizeMin2Max5;
	}

	public void setSizeMin2Max5(String[] sizeMin2Max5) {
		this.sizeMin2Max5 = sizeMin2Max5;
	}

	public String[] getTestDefaultMinMax5() {
		return testDefaultMinMax5;
	}

	public void setTestDefaultMinMax5(String[] testDefaultMinMax5) {
		this.testDefaultMinMax5 = testDefaultMinMax5;
	}

	public String[] getTestMin2DefaultMax() {
		return testMin2DefaultMax;
	}

	public void setTestMin2DefaultMax(String[] testMin2DefaultMax) {
		this.testMin2DefaultMax = testMin2DefaultMax;
	}

	public String[] getTestDefaultMinDefaultMax() {
		return testDefaultMinDefaultMax;
	}

	public void setTestDefaultMinDefaultMax(String[] testDefaultMinDefaultMax) {
		this.testDefaultMinDefaultMax = testDefaultMinDefaultMax;
	}

}