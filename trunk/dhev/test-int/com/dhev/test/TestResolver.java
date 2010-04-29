package com.dhev.test;

import java.util.Date;

import com.dhev.ExpressionLanguageUtils;

public class TestResolver implements ExpressionLanguageUtils {

	public Integer getInteger(String expression) {
		if (expression.startsWith("#{config."))
			try {
				return (Integer) Config.class.getField(
						expression.replace("#{config.", "").replace("}", ""))
						.get(new Config());
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		else
			return Integer.parseInt(expression);
	}

	public Long getLong(String expression) {
		return Long.parseLong(expression);
	}

	public Boolean getBoolean(String expression) {
		return Boolean.parseBoolean(expression);
	}

	public String getString(String expression) {
		return expression;
	}

	public Double getDouble(String expression) {
		return Double.parseDouble(expression);
	}

	public Date getDate(String expression) {
		// TODO Auto-generated method stub
		return null;
	}

}
