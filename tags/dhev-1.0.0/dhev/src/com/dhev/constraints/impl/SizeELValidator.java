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

import org.hibernate.validator.Size;
import org.hibernate.validator.SizeValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.SizeEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class SizeELValidator implements Validator<SizeEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String minEL;
	private String maxEL;

	public void initialize(SizeEL parameters) {
		minEL = parameters.min();
		maxEL = parameters.max();
	}

	public boolean isValid(Object param) {
		Size size = ValidatorAnnotationProxy.createProxy(this, Size.class);
		SizeValidator validator = new SizeValidator();
		validator.initialize(size);

		return validator.isValid(param);

	}

	public Integer min() {
		if (isParamNotSet(minEL))
			return 0;

		return expressionLanguageUtils.getInteger(minEL);
	}

	public Integer max() {
		if (isParamNotSet(maxEL))
			return Integer.MAX_VALUE;

		return expressionLanguageUtils.getInteger(maxEL);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
