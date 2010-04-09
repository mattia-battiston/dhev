package com.dhev.constraints.impl;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MaxEL;

public class MaxELValidator implements Validator<MaxEL> {

	private boolean includeLimit;

	private String maxExpression;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MaxEL annotation) {
		maxExpression = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Long max = ((Number) expressionLanguageUtils.evaluateEl(maxExpression))
				.longValue();

		if (includeLimit)
			return max.compareTo(((Number) param).longValue()) >= 0;
		else
			return max.compareTo(((Number) param).longValue()) > 0;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
