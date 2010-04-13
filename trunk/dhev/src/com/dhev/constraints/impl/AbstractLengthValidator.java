package com.dhev.constraints.impl;

import java.lang.annotation.Annotation;

import org.hibernate.validator.Length;
import org.hibernate.validator.LengthValidator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;

abstract class AbstractLengthValidator {

	protected ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		Length minLength = getLength();
		LengthValidator validator = new LengthValidator();
		validator.initialize(minLength);

		return validator.isValid(param);
	}

	abstract Length getLength();

	protected class LengthImpl implements Length {

		private final Integer minLength;
		private final Integer maxLength;

		public LengthImpl(Integer minLength, Integer maxLength) {
			this.minLength = minLength;
			this.maxLength = maxLength;
		}

		public int min() {
			return minLength;
		}

		public int max() {
			return maxLength;
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
