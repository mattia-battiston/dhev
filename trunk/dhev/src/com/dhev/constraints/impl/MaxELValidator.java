package com.dhev.constraints.impl;

import org.hibernate.validator.Max;
import org.hibernate.validator.MaxValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MaxEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class MaxELValidator implements Validator<MaxEL> {

	private boolean includeLimit;

	private String maxEL;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MaxEL annotation) {
		maxEL = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Max max = ValidatorAnnotationProxy.createProxy(this, Max.class);
		MaxValidator validator = new MaxValidator();
		validator.initialize(max);

		return validator.isValid(param);
	}

	public long value() {
		Long maxValue = expressionLanguageUtils.getLong(maxEL);
		if (!includeLimit)
			maxValue--;
		return maxValue;
	}
}
