package com.dhev;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.jboss.seam.el.SeamExpressionFactory;

public class ExpressionLanguageUtilsImpl implements ExpressionLanguageUtils {

	public Object evaluateEl(String expression) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();

		SeamExpressionFactory factory = ((SeamExpressionFactory) context
				.getApplication().getExpressionFactory());

		ValueExpression valExpr = factory.createValueExpression(elCtx,
				expression, Object.class);
		return valExpr.getValue(elCtx);
	}

}
