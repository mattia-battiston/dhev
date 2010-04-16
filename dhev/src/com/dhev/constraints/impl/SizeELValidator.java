package com.dhev.constraints.impl;

import org.hibernate.validator.Size;
import org.hibernate.validator.SizeValidator;
import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.ExpressionLanguageUtilsImpl;
import com.dhev.constraints.SizeEL;
import com.dhev.constraints.utils.ValidatorAnnotationProxy;

public class SizeELValidator implements Validator<SizeEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = new ExpressionLanguageUtilsImpl();

	private String minEL;

	private String maxEL;

	public void initialize(SizeEL parameters) {
		minEL = parameters.min();
		maxEL = parameters.max();
	}

	public boolean isValid(Object param) {
		System.out.println("validating sizeel");
		Size size = ValidatorAnnotationProxy.createProxy(this, Size.class);
		SizeValidator validator = new SizeValidator();
		validator.initialize(size);

		boolean valid = validator.isValid(param);

		System.out.println("returning " + valid);
		return valid;
	}

	public Integer min() {
		System.out.println("");
		return expressionLanguageUtils.getInteger(minEL);
	}

	public Integer max() {
		return expressionLanguageUtils.getInteger(maxEL);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
