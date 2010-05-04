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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

@Name("systemConfiguration")
@Scope(ScopeType.APPLICATION)
@Startup
public class SystemConfiguration {

	private Integer maxLengthName = 10;
	private Integer minAge = 18;
	private Integer maxAge = 25;
	private Integer minLengthZip = 5;
	private Integer maxLengthZip = 10;
	private Integer minRating = 0;
	private Integer maxRating = 5;
	private Integer minColors = 0;
	private Integer maxColors = 4;
	private Boolean mustReadDocs = true;
	private String emailPattern = "[a-z]+@[a-z]+\\.com";
	private Double maxHeight = 210.5;
	private Double minHeight = 123.45;
	private Double minWeight = 30.1;
	private Double maxWeight = 456.789;
	private Date maxDateOfBirth;
	private Date minDateOfBirth;

	private Integer maxScoreAverageIntegerDigits = 3;
	private Integer minScoreAverageIntegerDigits;
	private Integer maxScoreAverageFractionDigits = 2;
	private Integer minScoreAverageFractionDigits;

	@Create
	public void init() {
		System.out.println("initializing SystemConfiguration...");

		try {
			maxDateOfBirth = new SimpleDateFormat("dd-MMM-yyyy z")
					.parse("01-jan-2010 GMT");
			minDateOfBirth = new SimpleDateFormat("dd-MMM-yyyy z")
					.parse("31-dec-1986 GMT");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
		return maxAge;
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

	public Double getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(Double maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Double getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(Double minHeight) {
		this.minHeight = minHeight;
	}

	public Double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Double minWeight) {
		this.minWeight = minWeight;
	}

	public Double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Date getMaxDateOfBirth() {
		return maxDateOfBirth;
	}

	public void setMaxDateOfBirth(Date minDateOfBirth) {
		this.maxDateOfBirth = minDateOfBirth;
	}

	public Date getMinDateOfBirth() {
		return minDateOfBirth;
	}

	public void setMinDateOfBirth(Date minDateOfBirth) {
		this.minDateOfBirth = minDateOfBirth;
	}

	public Integer getMaxScoreAverageIntegerDigits() {
		return maxScoreAverageIntegerDigits;
	}

	public void setMaxScoreAverageIntegerDigits(
			Integer maxScoreAverageIntegerDigits) {
		this.maxScoreAverageIntegerDigits = maxScoreAverageIntegerDigits;
	}

	public Integer getMinScoreAverageIntegerDigits() {
		return minScoreAverageIntegerDigits;
	}

	public void setMinScoreAverageIntegerDigits(
			Integer minScoreAverageIntegerDigits) {
		this.minScoreAverageIntegerDigits = minScoreAverageIntegerDigits;
	}

	public Integer getMaxScoreAverageFractionDigits() {
		return maxScoreAverageFractionDigits;
	}

	public void setMaxScoreAverageFractionDigits(
			Integer maxScoreAverageFractionDigits) {
		this.maxScoreAverageFractionDigits = maxScoreAverageFractionDigits;
	}

	public Integer getMinScoreAverageFractionDigits() {
		return minScoreAverageFractionDigits;
	}

	public void setMinScoreAverageFractionDigits(
			Integer minScoreAverageFractionDigits) {
		this.minScoreAverageFractionDigits = minScoreAverageFractionDigits;
	}

}
