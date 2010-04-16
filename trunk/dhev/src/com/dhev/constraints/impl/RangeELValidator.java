package com.dhev.constraints.impl;

import org.hibernate.validator.Range;
import org.hibernate.validator.RangeValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.RangeEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class RangeELValidator implements Validator<RangeEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	private String maxEL;
	private String minEL;

	private boolean includeMax;
	private boolean includeMin;

	public void initialize(RangeEL annotation) {
		minEL = annotation.min();
		maxEL = annotation.max();

		includeMax = annotation.includeMax();
		includeMin = annotation.includeMin();
	}

	public boolean isValid(Object param) {
		Range range = ValidatorAnnotationProxy.createProxy(this, Range.class);
		RangeValidator validator = new RangeValidator();
		validator.initialize(range);

		return validator.isValid(param);
	}

	public Long min() {
		if (isParamNotSet(minEL))
			return Long.MIN_VALUE;

		Long min = expressionLanguageUtils.getLong(minEL);
		if (!includeMin)
			min++;
		return min;
	}

	public Long max() {
		if (isParamNotSet(maxEL))
			return Long.MAX_VALUE;

		Long max = expressionLanguageUtils.getLong(maxEL);
		if (!includeMax)
			max--;
		return max;
	}

	private boolean isParamNotSet(String el) {
		return el != null && el.trim().length() == 0;
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
