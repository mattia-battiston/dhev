package com.dhev.config;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("systemConfiguration")
@Scope(ScopeType.APPLICATION)
public class SystemConfiguration {

	private Integer minAge = 18;
	private Integer minLengthZip = 5;

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMinLengthZip() {
		return minLengthZip;
	}

	public void setMinLengthZip(Integer minLengthZip) {
		this.minLengthZip = minLengthZip;
	}

	public String updateConfiguration() {
		System.out.println("Updating configuration...");
		return "configurationUpdated";
	}

}
