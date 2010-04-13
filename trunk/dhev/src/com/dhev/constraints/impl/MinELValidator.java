package com.dhev.constraints.impl;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MinEL;

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

		Long min = ((Number) expressionLanguageUtils.evaluateEl(minExpression))
				.longValue();

		if (includeLimit)
			return min.compareTo(((Number) param).longValue()) <= 0;
		else
			return min.compareTo(((Number) param).longValue()) < 0;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
