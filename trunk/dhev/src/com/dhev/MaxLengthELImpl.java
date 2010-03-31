package com.dhev;

import org.hibernate.validator.Validator;

public class MaxLengthELImpl implements Validator<MaxLengthEL> {

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
