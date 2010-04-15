package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.MaxLengthEL;

public class MaxLengthELValidator extends AbstractLengthValidator implements
		Validator<MaxLengthEL> {

	private String maxLengthExpression;
	private boolean includeLimit;

	public void initialize(MaxLengthEL annotation) {
		maxLengthExpression = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	@Override
	Length getLength() {
		Integer maxLength = expressionLanguageUtils
				.getInteger(maxLengthExpression);
		if (!includeLimit)
			maxLength--;
		return new LengthImpl(Integer.MIN_VALUE, maxLength);
	}

}
