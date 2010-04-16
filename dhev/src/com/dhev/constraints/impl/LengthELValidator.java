package com.dhev.constraints.impl;

import org.hibernate.validator.Length;
import org.hibernate.validator.LengthValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.LengthEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class LengthELValidator implements Validator<LengthEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	private String maxEL;
	private String minEL;

	private boolean includeMax;
	private boolean includeMin;

	public void initialize(LengthEL annotation) {
		maxEL = annotation.max();
		minEL = annotation.min();

		includeMax = annotation.includeMax();
		includeMin = annotation.includeMin();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Length length = ValidatorAnnotationProxy
				.createProxy(this, Length.class);
		LengthValidator validator = new LengthValidator();
		validator.initialize(length);

		return validator.isValid(param);
	}

	public int max() {
		if (isParamNotSet(maxEL))
			return Integer.MAX_VALUE;

		Integer max = expressionLanguageUtils.getInteger(maxEL);
		if (!includeMax)
			max--;
		return max;
	}

	public int min() {
		if (isParamNotSet(minEL))
			return Integer.MIN_VALUE;

		Integer min = expressionLanguageUtils.getInteger(minEL);
		if (!includeMin)
			min++;
		return min;
	}

	private boolean isParamNotSet(String el) {
		return el != null && el.trim().length() == 0;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
