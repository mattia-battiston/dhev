package com.dhev.validator.impl;

import java.lang.annotation.Annotation;

import org.hibernate.validator.Range;
import org.hibernate.validator.RangeValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.validator.RangeEL;

public class RangeELValidator implements Validator<RangeEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	private String minEL;

	private String maxEL;

	public void initialize(RangeEL parameters) {

		minEL = parameters.min();
		maxEL = parameters.max();

	}

	public boolean isValid(Object param) {
		Number min = (Number) expressionLanguageUtils.evaluateEl(minEL);
		Number max = (Number) expressionLanguageUtils.evaluateEl(maxEL);

		Range range = new RangeImpl(min, max);
		RangeValidator validator = new RangeValidator();
		validator.initialize(range);

		return validator.isValid(param);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	private class RangeImpl implements Range {

		private final Number min;
		private final Number max;

		public RangeImpl(Number min, Number max) {
			this.min = min;
			this.max = max;
		}

		public long min() {
			return min.longValue();
		}

		public long max() {
			return max.longValue();
		}

		public String message() {
			return null;
		}

		public Class<? extends Annotation> annotationType() {
			return null;
		}

	}

}
