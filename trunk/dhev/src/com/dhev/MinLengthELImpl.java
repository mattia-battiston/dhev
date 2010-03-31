package com.dhev;

import org.hibernate.validator.Validator;

public class MinLengthELImpl implements Validator<MinLengthEL> {

	private String minLengthExpression;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MinLengthEL annotation) {
		minLengthExpression = annotation.value();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Number minLength = (Number) expressionLanguageUtils
				.evaluateEl(minLengthExpression);

		return ((String) param).length() >= minLength.longValue();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
