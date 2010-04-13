package com.dhev;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

public class ExpressionLanguageUtilsImpl implements ExpressionLanguageUtils {

	@SuppressWarnings("unchecked")
	public <T> T evaluateEl(String expression, Class<T> clazz) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();

		ExpressionFactory factory = context.getApplication()
				.getExpressionFactory();

		ValueExpression valExpr = factory.createValueExpression(elCtx,
				expression, Object.class);
		return (T) valExpr.getValue(elCtx);
	}

	public Long getLong(String expression) {
		return evaluateEl(expression, Number.class).longValue();
	}
}
