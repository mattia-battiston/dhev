package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.MaxLengthEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

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
		return ValidatorAnnotationProxy.createProxy(this, Length.class);
	}

	public int min() {
		return Integer.MIN_VALUE;
	}

	public int max() {
		Integer maxLength = expressionLanguageUtils
				.getInteger(maxLengthExpression);
		if (!includeLimit)
			maxLength--;
		return maxLength;
	}

}
