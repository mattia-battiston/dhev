package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.MinLengthEL;

public class MinLengthELValidator extends AbstractLengthValidator implements
		Validator<MinLengthEL> {

	private String minLengthExpression;
	private boolean includeLimit;

	public void initialize(MinLengthEL annotation) {
		minLengthExpression = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	@Override
	Length getLength() {
		Integer minLength = expressionLanguageUtils
				.getInteger(minLengthExpression);
		if (!includeLimit)
			minLength++;
		return new LengthImpl(minLength, Integer.MAX_VALUE);
	}

}
