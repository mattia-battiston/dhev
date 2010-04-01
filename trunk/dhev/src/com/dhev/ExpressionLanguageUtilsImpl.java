package com.dhev;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

public class ExpressionLanguageUtilsImpl implements ExpressionLanguageUtils {

	public Object evaluateEl(String expression) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();

		ExpressionFactory factory = context.getApplication()
				.getExpressionFactory();

		ValueExpression valExpr = factory.createValueExpression(elCtx,
				expression, Object.class);
		return valExpr.getValue(elCtx);
	}
}
