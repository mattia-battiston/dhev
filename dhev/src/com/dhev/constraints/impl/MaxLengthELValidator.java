package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MaxLengthEL;

public class MaxLengthELValidator extends AbstractLengthValidator implements
		Validator<MaxLengthEL> {

	private String maxLengthExpression;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MaxLengthEL annotation) {
		maxLengthExpression = annotation.value();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	@Override
	Length getLength() {
		Integer maxLength = expressionLanguageUtils
				.getInteger(maxLengthExpression);
		return new LengthImpl(Integer.MIN_VALUE, maxLength);
	}

}
