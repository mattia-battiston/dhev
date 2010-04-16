package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.MinLengthEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

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
		return ValidatorAnnotationProxy.createProxy(this, Length.class);

	}

	public int min() {
		Integer minLength = expressionLanguageUtils
				.getInteger(minLengthExpression);
		if (!includeLimit)
			minLength++;
		return minLength;
	}

	public int max() {
		return Integer.MAX_VALUE;
	}
}
