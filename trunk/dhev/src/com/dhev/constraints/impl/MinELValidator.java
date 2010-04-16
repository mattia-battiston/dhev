package com.dhev.constraints.impl;

import org.hibernate.validator.Min;
import org.hibernate.validator.MinValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MinEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

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

		Min min = ValidatorAnnotationProxy.createProxy(this, Min.class);
		MinValidator validator = new MinValidator();
		validator.initialize(min);

		return validator.isValid(param);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	public long value() {
		Long minValue = expressionLanguageUtils.getLong(minExpression);
		if (!includeLimit)
			minValue++;
		return minValue;
	}
}
