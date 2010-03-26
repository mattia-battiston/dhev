package com.dhev;

import org.hibernate.validator.Validator;

public class MaxELImpl implements Validator<MaxEL> {

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

		Number max = (Number) expressionLanguageUtils.evaluateEl(maxExpression);

		if (includeLimit)
			return ((Number) param).doubleValue() <= max.doubleValue();
		else
			return ((Number) param).doubleValue() < max.doubleValue();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
