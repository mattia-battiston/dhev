package com.dhev.config;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("systemConfiguration")
@Scope(ScopeType.APPLICATION)
public class SystemConfiguration {

	private Integer minAge = 18;
	private String minLengthZip = "5";
	private Integer minRating = 0;
	private Integer maxRating = 5;

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public String getMinLengthZip() {
		return minLengthZip;
	}

	public void setMinLengthZip(String minLengthZip) {
		this.minLengthZip = minLengthZip;
	}

	public String updateConfiguration() {
		System.out.println("Updating configuration...");
		return "configurationUpdated";
	}

	public Integer getMinRating() {
		return minRating;
	}

	public void setMinRating(Integer minRating) {
		this.minRating = minRating;
	}

	public Integer getMaxRating() {
		return maxRating;
	}

	public void setMaxRating(Integer maxRating) {
		this.maxRating = maxRating;
	}

}