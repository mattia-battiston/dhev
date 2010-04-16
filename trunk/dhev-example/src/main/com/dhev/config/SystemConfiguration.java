package com.dhev.config;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("systemConfiguration")
@Scope(ScopeType.APPLICATION)
public class SystemConfiguration {

	private Integer maxLengthName = 10;
	private Integer minAge = 18;
	private Integer maxAge = 25;
	private Integer minLengthZip = 5;
	private Integer maxLengthZip = 10;
	private Integer minRating = 0;
	private Integer maxRating = 5;

	public Integer getMaxLengthName() {
		return maxLengthName;
	}

	public void setMaxLengthName(Integer maxLengthName) {
		this.maxLengthName = maxLengthName;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Integer getMinLengthZip() {
		return minLengthZip;
	}

	public void setMinLengthZip(Integer minLengthZip) {
		this.minLengthZip = minLengthZip;
	}

	public Integer getMaxLengthZip() {
		return maxLengthZip;
	}

	public void setMaxLengthZip(Integer maxLengthZip) {
		this.maxLengthZip = maxLengthZip;
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
