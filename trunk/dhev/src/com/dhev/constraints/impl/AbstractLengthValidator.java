package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.LengthValidator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;

abstract class AbstractLengthValidator {

	protected ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Length minLength = getLength();
		LengthValidator validator = new LengthValidator();
		validator.initialize(minLength);

		return validator.isValid(param);
	}

	abstract Length getLength();

}
