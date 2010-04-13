package com.dhev.constraints.impl;

import java.lang.annotation.Annotation;

import org.hibernate.validator.Max;
import org.hibernate.validator.MaxValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MaxEL;

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

		Long maxValue = expressionLanguageUtils.getLong(maxEL);
		if (!includeLimit)
			maxValue--;

		Max max = new MaxImpl(maxValue);
		MaxValidator validator = new MaxValidator();
		validator.initialize(max);

		return validator.isValid(param);
	}

	private class MaxImpl implements Max {

		private final Number max;

		public MaxImpl(Number max) {
			this.max = max;
		}

		public long value() {
			return max.longValue();
		}

		public String message() {
			// TODO Auto-generated method stub
			return null;
		}

		public Class<? extends Annotation> annotationType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
