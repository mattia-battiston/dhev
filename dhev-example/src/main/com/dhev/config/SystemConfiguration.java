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
	private Integer minColors = 2;
	private Integer maxColors = 4;
	private Boolean mustReadDocs = true;
	private String emailPattern = "[a-z]+@[a-z]+\\.com";

	/*
	 * Use following lines to replace the Integer version of maxAge to test the
	 * exception management when param does not evaluate to correct class type
	 * (see documentation at section ClassCastException management). TODO: write
	 * that documentation!!
	 * 
	 * private String maxAge = "25";
	 * 
	 * public String getMaxAge() { return maxAge; }
	 * 
	 * public void setMaxAge(String maxAge) { this.maxAge = maxAge; }
	 */

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

	public Integer getMinLengthZip() {
		return minLengthZip;
	}

	public Integer getMaxAge() {
		return null;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
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

	public Integer getMinColors() {
		return minColors;
	}

	public void setMinColors(Integer minColors) {
		this.minColors = minColors;
	}

	public Integer getMaxColors() {
		return maxColors;
	}

	public void setMaxColors(Integer maxColors) {
		this.maxColors = maxColors;
	}

	public Boolean getMustReadDocs() {
		return mustReadDocs;
	}

	public void setMustReadDocs(Boolean mustReadDocs) {
		this.mustReadDocs = mustReadDocs;
	}

	public String getEmailPattern() {
		return emailPattern;
	}

	public void setEmailPattern(String emailPattern) {
		this.emailPattern = emailPattern;
	}

}
