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
package com.dhev.constraints.impl;

import static com.dhev.constraints.utils.ValidatorUtils.isParamNotSet;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.DecimalRangeEL;

public class DecimalRangeELValidator implements Validator<DecimalRangeEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String minEL;
	private String maxEL;

	private boolean includeMin;
	private boolean includeMax;

	public void initialize(DecimalRangeEL annotation) {
		minEL = annotation.min();
		maxEL = annotation.max();

		includeMax = annotation.includeMax();
		includeMin = annotation.includeMin();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		if (param instanceof Number)
			return checkMin(param) && checkMax(param);
		else
			return false;
	}

	private boolean checkMin(Object param) {
		if (isParamNotSet(minEL))
			return true;

		Double min = expressionLanguageUtils.getDouble(minEL);
		return includeMin ? min.compareTo(((Number) param).doubleValue()) <= 0
				: min.compareTo(((Number) param).doubleValue()) < 0;
	}

	private boolean checkMax(Object param) {
		if (isParamNotSet(maxEL))
			return true;

		Double max = expressionLanguageUtils.getDouble(maxEL);
		return includeMax ? max.compareTo(((Number) param).doubleValue()) >= 0
				: max.compareTo(((Number) param).doubleValue()) > 0;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
