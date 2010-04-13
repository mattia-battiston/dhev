package com.dhev.constraints.impl;

import java.lang.annotation.Annotation;

import org.hibernate.validator.Length;
import org.hibernate.validator.LengthValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.MinLengthEL;

public class MinLengthELValidator implements Validator<MinLengthEL> {

	private String minLengthExpression;

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public void initialize(MinLengthEL annotation) {
		minLengthExpression = annotation.value();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Integer minLengthValue = expressionLanguageUtils
				.getInteger(minLengthExpression);

		Length minLength = new LengthImpl(minLengthValue);
		LengthValidator validator = new LengthValidator();
		validator.initialize(minLength);

		return validator.isValid(param);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

	private class LengthImpl implements Length {

		private final Integer minLength;

		public LengthImpl(Integer minLength) {
			this.minLength = minLength;
		}

		public int min() {
			return minLength;
		}

		public int max() {
			return Integer.MAX_VALUE;
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
