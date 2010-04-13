package com.dhev.constraints.impl;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MaxLengthEL;

public class MaxLengthELValidator implements Validator<MaxLengthEL> {

	private String maxLengthExpression;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MaxLengthEL annotation) {
		maxLengthExpression = annotation.value();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Number maxLength = (Number) expressionLanguageUtils
				.evaluateEl(maxLengthExpression);

		return ((String) param).length() <= maxLength.longValue();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
