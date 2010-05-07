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

import org.hibernate.validator.Length;
import org.hibernate.validator.LengthValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.LengthEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class LengthELValidator implements Validator<LengthEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String maxEL;
	private String minEL;

	private boolean includeMax;
	private boolean includeMin;

	public void initialize(LengthEL annotation) {
		maxEL = annotation.max();
		minEL = annotation.min();

		includeMax = annotation.includeMax();
		includeMin = annotation.includeMin();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Length length = ValidatorAnnotationProxy
				.createProxy(this, Length.class);
		LengthValidator validator = new LengthValidator();
		validator.initialize(length);

		return validator.isValid(param);
	}

	public int max() {
		if (isParamNotSet(maxEL))
			return Integer.MAX_VALUE;

		Integer max = expressionLanguageUtils.getInteger(maxEL);
		if (!includeMax)
			max--;
		return max;
	}

	public int min() {
		if (isParamNotSet(minEL))
			return Integer.MIN_VALUE;

		Integer min = expressionLanguageUtils.getInteger(minEL);
		if (!includeMin)
			min++;
		return min;
	}

	private boolean isParamNotSet(String el) {
		return el != null && el.trim().length() == 0;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
