package com.dhev.constraints.impl;

import java.lang.annotation.Annotation;

import org.hibernate.validator.Range;
import org.hibernate.validator.RangeValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.RangeEL;

public class RangeELValidator implements Validator<RangeEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	private String minEL;

	private String maxEL;

	public void initialize(RangeEL parameters) {

		minEL = parameters.min();
		maxEL = parameters.max();

	}

	public boolean isValid(Object param) {
		Long min = expressionLanguageUtils.getLong(minEL);
		Long max = expressionLanguageUtils.getLong(maxEL);

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

		private final Long min;
		private final Long max;

		public RangeImpl(Long min, Long max) {
			this.min = min;
			this.max = max;
		}

		public long min() {
			return min;
		}

		public long max() {
			return max;
		}

		public String message() {
			return null;
		}

		public Class<? extends Annotation> annotationType() {
			return null;
		}

	}

}
