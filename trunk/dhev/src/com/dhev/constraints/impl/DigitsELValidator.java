package com.dhev.constraints.impl;

import static com.dhev.constraints.utils.ValidatorUtils.isParamNotSet;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.DigitsEL;

public class DigitsELValidator implements Validator<DigitsEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String maxIntegerDigitsEL;
	private String minIntegerDigitsEL;
	private String maxFractionalDigitsEL;
	private String minFractionalDigitsEL;

	public void initialize(DigitsEL annotation) {
		maxIntegerDigitsEL = annotation.maxIntegerDigits();
		minIntegerDigitsEL = annotation.minIntegerDigits();
		maxFractionalDigitsEL = annotation.maxFractionalDigits();
		minFractionalDigitsEL = annotation.minFractionalDigits();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		if (param instanceof Number) {

			String stringValue = param.toString();
			int pos = stringValue.indexOf(".");

			Integer integerDigits = (pos == -1) ? stringValue.length() : pos;
			Integer fractionalDigits = (pos == -1) ? 0 : stringValue.length()
					- pos - 1;

			return checkIntegerDigits(integerDigits)
					&& checkFractionalDigits(fractionalDigits);
		} else {
			return false;
		}

	}

	private boolean checkIntegerDigits(Integer integerDigits) {
		Integer maxIntegerDigits = getMaxIntegerDigits();
		Integer minIntegerDigits = getMinIntegerDigits();
		return maxIntegerDigits.compareTo(integerDigits) >= 0
				&& integerDigits.compareTo(minIntegerDigits) >= 0;
	}

	private boolean checkFractionalDigits(Integer fractionalDigits) {
		Integer maxFractionalDigits = getMaxFractionalDigits();
		Integer minFractionalDigits = getMinFractionalDigits();
		return maxFractionalDigits.compareTo(fractionalDigits) >= 0
				&& fractionalDigits.compareTo(minFractionalDigits) >= 0;
	}

	private Integer getMaxIntegerDigits() {
		if (isParamNotSet(maxIntegerDigitsEL))
			return Integer.MAX_VALUE;
		else
			return expressionLanguageUtils.getInteger(maxIntegerDigitsEL);
	}

	private Integer getMinIntegerDigits() {
		if (isParamNotSet(minIntegerDigitsEL))
			return Integer.MIN_VALUE;
		else
			return expressionLanguageUtils.getInteger(minIntegerDigitsEL);
	}

	private Integer getMaxFractionalDigits() {
		if (isParamNotSet(maxFractionalDigitsEL))
			return Integer.MAX_VALUE;
		else
			return expressionLanguageUtils.getInteger(maxFractionalDigitsEL);
	}

	private Integer getMinFractionalDigits() {
		if (isParamNotSet(minFractionalDigitsEL))
			return Integer.MIN_VALUE;
		else
			return expressionLanguageUtils.getInteger(minFractionalDigitsEL);
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
