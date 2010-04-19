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

import org.hibernate.validator.Min;
import org.hibernate.validator.MinValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MinEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class MinELValidator implements Validator<MinEL> {

	private String minExpression;
	private boolean includeLimit;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MinEL annotation) {
		minExpression = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Min min = ValidatorAnnotationProxy.createProxy(this, Min.class);
		MinValidator validator = new MinValidator();
		validator.initialize(min);

		return validator.isValid(param);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	public long value() {
		Long minValue = expressionLanguageUtils.getLong(minExpression);
		if (!includeLimit)
			minValue++;
		return minValue;
	}
}
