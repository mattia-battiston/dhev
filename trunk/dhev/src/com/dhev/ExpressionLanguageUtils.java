package com.dhev;

public interface ExpressionLanguageUtils {

	<T> T evaluateEl(String expression, Class<T> clazz);

	Long getLong(String expression);

}
