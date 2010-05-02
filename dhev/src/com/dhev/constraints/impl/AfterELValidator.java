package com.dhev.constraints.impl;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.validator.Validator;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.AfterEL;

public class AfterELValidator implements Validator<AfterEL> {

	private ExpressionLanguageUtils expressionLanguageUtils = ExpressionLanguageResolverFactory
			.createResolver();

	private String dateEL;
	private boolean includeLimit;

	public void initialize(AfterEL annotation) {
		dateEL = annotation.value();
		includeLimit = annotation.includeLimit();
	}

	public boolean isValid(Object param) {
		if (param == null)
			return true;

		if (param instanceof Date) {
			Date limit = expressionLanguageUtils.getDate(dateEL);
			Calendar limitCalendar = Calendar.getInstance();
			limitCalendar.setTime(limit);

			Calendar paramCalendar = Calendar.getInstance();
			paramCalendar.setTime((Date) param);

			return includeLimit ? paramCalendar.compareTo(limitCalendar) >= 0
					: paramCalendar.compareTo(limitCalendar) > 0;
		} else {
			return false;
		}
	}

	public void setExpressionLanguageUtils(
			ExpressionLanguageUtils expressionLanguageUtils) {
		this.expressionLanguageUtils = expressionLanguageUtils;
	}

}
