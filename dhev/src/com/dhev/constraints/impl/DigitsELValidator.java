package com.dhev.constraints.impl;

import org.hibernate.validator.Digits;
import org.hibernate.validator.DigitsValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.DigitsEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class DigitsELValidator implements Validator<DigitsEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String maxIntegerDigitsEL;
	private String maxFractionalDigitsEL;

	public void initialize(DigitsEL annotation) {
		maxIntegerDigitsEL = annotation.maxIntegerDigits();
		maxFractionalDigitsEL = annotation.maxFractionalDigits();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Digits digits = ValidatorAnnotationProxy
				.createProxy(this, Digits.class);
		DigitsValidator validator = new DigitsValidator();
		validator.initialize(digits);

		return validator.isValid(param);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	public int integerDigits() {
		return expressionLanguageUtils.getInteger(maxIntegerDigitsEL);
	}

	public int fractionalDigits() {
		return expressionLanguageUtils.getInteger(maxFractionalDigitsEL);
	}

}
