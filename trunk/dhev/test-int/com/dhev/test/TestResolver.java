package com.dhev.test;

import com.dhev.ExpressionLanguageUtils;

public class TestResolver implements ExpressionLanguageUtils {

	@Override
	public Integer getInteger(String expression) {

		System.out.println("getInteger[" + expression + "]");
		return Integer.parseInt(expression);
	}

	@Override
	public Long getLong(String expression) {
		System.out.println("getLong[" + expression + "]");
		return Long.parseLong(expression);
	}

	@Override
	public Boolean getBoolean(String expression) {
		// TODO Auto-generated method stub
		return null;
	}

}
