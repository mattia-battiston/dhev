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

	public Boolean getBoolean(String expression) {
		return evaluateEl(expression, Boolean.class);
	}
}
