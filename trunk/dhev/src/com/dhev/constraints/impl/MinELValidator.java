package com.dhev.constraints.impl;

import java.lang.annotation.Annotation;

import org.hibernate.validator.Min;
import org.hibernate.validator.MinValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MinEL;

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

		Long minValue = expressionLanguageUtils.getLong(minExpression);
		if (!includeLimit)
			minValue++;

		Min min = new MinImpl(minValue);
		MinValidator validator = new MinValidator();
		validator.initialize(min);

		return validator.isValid(param);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	private class MinImpl implements Min {

		private final Long min;

		public MinImpl(Long min) {
			this.min = min;
		}

		public long value() {
			return min;
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
