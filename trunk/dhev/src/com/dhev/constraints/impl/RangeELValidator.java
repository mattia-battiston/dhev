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

	private String minEL;

	private String maxEL;

	public void initialize(RangeEL parameters) {
		minEL = parameters.min();
		maxEL = parameters.max();
	}

	public boolean isValid(Object param) {
		Range range = ValidatorAnnotationProxy.createProxy(this, Range.class);
		RangeValidator validator = new RangeValidator();
		validator.initialize(range);

		return validator.isValid(param);
	}

	public Long min() {
		return expressionLanguageUtils.getLong(minEL);
	}

	public Long max() {
		return expressionLanguageUtils.getLong(maxEL);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
