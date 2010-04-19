package com.dhev;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import com.dhev.exception.DhevClassCastException;

public class ExpressionLanguageUtilsImpl implements ExpressionLanguageUtils {

	public <T> T evaluateEl(String expression, Class<T> clazz) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();

		ExpressionFactory factory = context.getApplication()
				.getExpressionFactory();

		ValueExpression valExpr = factory.createValueExpression(elCtx,
				expression, Object.class);

		T result = null;
		try {
			result = clazz.cast(valExpr.getValue(elCtx));
		} catch (ClassCastException ex) {
			throw new DhevClassCastException(
					"Following EL expression does not evaluate to "
							+ clazz.getName() + ": \"" + expression + "\"", ex);
		}
		return result;
	}

	public Long getLong(String expression) {
		return evaluateEl(expression, Number.class).longValue();
	}

	public Integer getInteger(String expression) {
		return evaluateEl(expression, Number.class).intValue();
	}
}
