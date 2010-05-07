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

import org.hibernate.validator.Pattern;
import org.hibernate.validator.PatternValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.PatternEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class PatternELValidator implements Validator<PatternEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String regexEL;
	private String flagsEL;

	public void initialize(PatternEL annotation) {
		regexEL = annotation.regex();
		flagsEL = annotation.flags();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Pattern pattern = ValidatorAnnotationProxy.createProxy(this,
				Pattern.class);
		PatternValidator validator = new PatternValidator();
		validator.initialize(pattern);

		return validator.isValid(param);
	}

	public String regex() {
		if (isParamNotSet(regexEL))
			return null;

		return expressionLanguageUtils.getString(regexEL);
	}

	public Integer flags() {
		if (isParamNotSet(flagsEL))
			return 0;

		return expressionLanguageUtils.getInteger(flagsEL);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
