package com.dhev.config;

import org.jboss.seam.annotations.Name;

@Name("systemConfiguration")
public class SystemConfiguration {

	private Integer minAge = 18;

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

}
