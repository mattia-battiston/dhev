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

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.DecimalMaxEL;

public class DecimalMaxELValidator implements Validator<DecimalMaxEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String maxEL;

	private boolean includeLimit;

	public void initialize(DecimalMaxEL annotation) {
		maxEL = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Double max = expressionLanguageUtils.getDouble(maxEL);
		if (param instanceof Number)
			return includeLimit ? max.compareTo(((Number) param).doubleValue()) >= 0
					: max.compareTo(((Number) param).doubleValue()) > 0;
		else
			return false;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
