package com.dhev.constraints.impl;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MinLengthEL;

public class MinLengthELValidator implements Validator<MinLengthEL> {

	private String minLengthExpression;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MinLengthEL annotation) {
		minLengthExpression = annotation.value();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Long minLength = expressionLanguageUtils.getLong(minLengthExpression);

		return ((String) param).length() >= minLength;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
