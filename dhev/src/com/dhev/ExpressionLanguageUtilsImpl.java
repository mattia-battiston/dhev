package com.dhev;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import com.dhev.exception.DhevClassCastException;

public class ExpressionLanguageUtilsImpl implements ExpressionLanguageUtils {

	// TODO: wrap this call in a sub call to catch ClassCastException and
	// rethrow it
	@SuppressWarnings("unchecked")
	private <T> T evaluateEl(String expression, Class<T> clazz) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();

		ExpressionFactory factory = context.getApplication()
				.getExpressionFactory();

		ValueExpression valExpr = factory.createValueExpression(elCtx,
				expression, Object.class);

		return (T) valExpr.getValue(elCtx);
	}

	public Long getLong(String expression) {
		Long result = null;
		try {
			result = evaluateEl(expression, Number.class).longValue();
		} catch (ClassCastException ex) {
			throw new DhevClassCastException(
					"Following EL expression does not evaluate to java.lang.Long: \""
							+ expression + "\"", ex);
		}
		return result;
	}

	public Integer getInteger(String expression) {
		Integer result = null;
		try {
			result = evaluateEl(expression, Number.class).intValue();
		} catch (ClassCastException ex) {
			throw new DhevClassCastException(
					"Following EL expression does not evaluate to java.lang.Integer: \""
							+ expression + "\"", ex);
		}
		return result;
	}
}
