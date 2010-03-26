package com.dhev;

import org.hibernate.validator.Validator;

public class MinELImpl implements Validator<MinEL> {

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

		Number min = (Number) expressionLanguageUtils.evaluateEl(minExpression);

		if (includeLimit)
			return ((Number) param).doubleValue() >= min.doubleValue();
		else
			return ((Number) param).doubleValue() > min.doubleValue();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
