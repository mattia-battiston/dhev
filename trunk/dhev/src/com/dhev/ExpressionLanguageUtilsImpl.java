/**
 * Copyright 2010, DHEV project's members and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.dhev;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import com.dhev.exception.DhevClassCastException;

public class ExpressionLanguageUtilsImpl implements ExpressionLanguageUtils {

	public Long getLong(String expression) {
		ValueExpression valueExpression = getValueExpression(expression);

		if (valueExpression.isLiteralText())
			return Long.parseLong(valueExpression.getExpressionString());
		else
			return evaluate(valueExpression, Number.class).longValue();
	}

	public Integer getInteger(String expression) {
		ValueExpression valueExpression = getValueExpression(expression);

		if (valueExpression.isLiteralText())
			return Integer.parseInt(valueExpression.getExpressionString());
		else
			return evaluate(valueExpression, Number.class).intValue();
	}

	public Boolean getBoolean(String expression) {
		ValueExpression valueExpression = getValueExpression(expression);

		if (valueExpression.isLiteralText())
			return Boolean.parseBoolean(valueExpression.getExpressionString());
		else
			return evaluate(valueExpression, Boolean.class);
	}

	private <T> T evaluate(ValueExpression valueExpression, Class<T> clazz) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();
		try {
			return clazz.cast(valueExpression.getValue(elCtx));
		} catch (ClassCastException ex) {
			throw new DhevClassCastException(
					"Following EL expression does not evaluate to "
							+ clazz.getName() + ": \""
							+ valueExpression.getExpressionString() + "\"", ex);
		}

	}

	private ValueExpression getValueExpression(String expression) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elCtx = context.getELContext();

		ExpressionFactory factory = context.getApplication()
				.getExpressionFactory();

		return factory.createValueExpression(elCtx, expression, Object.class);
	}
}
