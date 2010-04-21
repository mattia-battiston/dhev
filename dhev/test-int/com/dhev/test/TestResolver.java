package com.dhev.test;

import com.dhev.ExpressionLanguageUtils;

public class TestResolver implements ExpressionLanguageUtils {

	@Override
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

	@Override
	public Long getLong(String expression) {
		return Long.parseLong(expression);
	}

	@Override
	public Boolean getBoolean(String expression) {
		return Boolean.parseBoolean(expression);
	}

}
