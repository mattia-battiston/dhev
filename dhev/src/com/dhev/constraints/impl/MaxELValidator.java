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

import org.hibernate.validator.Max;
import org.hibernate.validator.MaxValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MaxEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class MaxELValidator implements Validator<MaxEL> {

	private boolean includeLimit;

	private String maxEL;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MaxEL annotation) {
		maxEL = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Max max = ValidatorAnnotationProxy.createProxy(this, Max.class);
		MaxValidator validator = new MaxValidator();
		validator.initialize(max);

		return validator.isValid(param);
	}

	public long value() {
		Long maxValue = expressionLanguageUtils.getLong(maxEL);
		if (!includeLimit)
			maxValue--;
		return maxValue;
	}
}
