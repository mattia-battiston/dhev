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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dhev.exception.EvaluationException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class ExpressionLanguageUtilsImplTest {

	private final ExpressionLanguageUtilsImpl expressionLanguageUtilsImpl = new ExpressionLanguageUtilsImpl();

	@Mock
	private FacesContext mockFacesContext;

	@Mock
	private ELContext mockELContext;

	@Mock
	private Application mockApplication;

	@Mock
	private ExpressionFactory expressionFactory;

	private String elExpression = "#{testExpression}";
	private String numericLiteralExpression = "2";
	private String doubleLiteralExpression = "2.5d";
	private String booleanLiteralExpression = "true";
	private String stringLiteralExpression = "string";
	private String dateLiteralExpression = "01-jan-2010 14:08";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(mockFacesContext);
		when(mockFacesContext.getELContext()).thenReturn(mockELContext);
		when(mockFacesContext.getApplication()).thenReturn(mockApplication);

		when(mockApplication.getExpressionFactory()).thenReturn(
				expressionFactory);

		configureExpressionFactory(elExpression, 2L);
		configureExpressionFactory(numericLiteralExpression,
				numericLiteralExpression);
		configureExpressionFactory(doubleLiteralExpression,
				doubleLiteralExpression);
		configureExpressionFactory(booleanLiteralExpression,
				booleanLiteralExpression);
		configureExpressionFactory(stringLiteralExpression,
				stringLiteralExpression);
		configureExpressionFactory(dateLiteralExpression, dateLiteralExpression);

	}

	/**
	 * configure the mocks in order to return the indicated value for the el
	 * expression
	 * 
	 * @param el
	 *            expression
	 * @param value
	 *            value that is to be returned
	 */
	private void configureExpressionFactory(String el, Object value) {
		ValueExpression mock = Mockito.mock(ValueExpression.class);
		when(
				expressionFactory.createValueExpression(mockELContext, el,
						Object.class)).thenReturn(mock);
		when(mock.getExpressionString()).thenReturn(el);
		when(mock.isLiteralText()).thenReturn(!el.startsWith("#{"));

		if (value instanceof Exception)
			when(mock.getValue(mockELContext)).thenThrow((Exception) value);
		else
			when(mock.getValue(mockELContext)).thenReturn(value);
	}

	@Test
	public void getLongReturnsLong() {
		configureExpressionFactory(elExpression, 2L);

		assertThat(expressionLanguageUtilsImpl.getLong(elExpression), is(2L));
	}

	@Test
	public void getLongReturnsLongIfExpressionIsLiteral() {

		assertThat(expressionLanguageUtilsImpl
				.getLong(numericLiteralExpression), is(2L));
	}

	@Test
	public void getLongThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		configureExpressionFactory(elExpression, "string");

		try {
			expressionLanguageUtilsImpl.getLong("#{testExpression}");
			fail("A DhevClassCastException should have been thrown here");
		} catch (EvaluationException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Number: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

	@Test
	public void getIntegerReturnsInteger() {
		configureExpressionFactory(elExpression, 2.5);

		assertThat(expressionLanguageUtilsImpl.getInteger(elExpression), is(2));
	}

	@Test
	public void getIntegerReturnsIntegerIfExpressionIsLiteral() {

		assertThat(expressionLanguageUtilsImpl
				.getInteger(numericLiteralExpression), is(2));
	}

	@Test
	public void getIntegerThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		configureExpressionFactory(elExpression, "string");

		try {
			expressionLanguageUtilsImpl.getInteger("#{testExpression}");
			fail("A DhevClassCastException should have been thrown here");
		} catch (EvaluationException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Number: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

	@Test
	public void getBooleanReturnsBoolean() {
		configureExpressionFactory(elExpression, true);

		assertThat(expressionLanguageUtilsImpl.getBoolean("#{testExpression}"),
				is(true));
	}

	@Test
	public void getBooleanReturnsBooleanIfExpressionIsLiteral() {

		assertThat(expressionLanguageUtilsImpl
				.getBoolean(booleanLiteralExpression), is(true));
	}

	@Test
	public void getBooleanThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		configureExpressionFactory(elExpression, "true");

		try {
			expressionLanguageUtilsImpl.getBoolean("#{testExpression}");
			fail("A DhevClassCastException should have been thrown here");
		} catch (EvaluationException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Boolean: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

	@Test
	public void getStringReturnsString() {
		configureExpressionFactory(elExpression, "string");

		assertThat(expressionLanguageUtilsImpl.getString(elExpression),
				is("string"));
	}

	@Test
	public void getStringReturnsStringIfExpressionIsLiteral() {

		assertThat(expressionLanguageUtilsImpl
				.getString(stringLiteralExpression), is("string"));
	}

	@Test
	public void getStringThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		configureExpressionFactory(elExpression, 2L);

		try {
			expressionLanguageUtilsImpl.getString(elExpression);
			fail("A DhevClassCastException should have been thrown here");
		} catch (EvaluationException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.String: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

	@Test
	public void correctExceptionIsThrownWhenExpressionIsWrong() {
		configureExpressionFactory("#{wrongEL}", new PropertyNotFoundException(
				"Property wrongEL not found"));

		try {
			expressionLanguageUtilsImpl.getLong("#{wrongEL}");
			fail("Should have thrown excepion");
		} catch (EvaluationException ex) {
			assertThat(
					ex.getMessage(),
					is("Can't find property specified in EL expression: \"#{wrongEL}\""));
		}

	}

	@Test
	public void correctExceptionIsThrownWhenExpressionIsEvaluatedToNull() {
		configureExpressionFactory(elExpression, null);

		try {
			expressionLanguageUtilsImpl.getLong("#{testExpression}");
			fail("Should have thrown excepion");
		} catch (EvaluationException ex) {
			assertThat(
					ex.getMessage(),
					is("Following EL expression evaluates to null: \"#{testExpression}\""));
		}

	}

	@Test
	public void getDoubleReturnsDouble() {
		configureExpressionFactory(elExpression, 2.5);

		assertThat(expressionLanguageUtilsImpl.getDouble(elExpression), is(2.5));
	}

	@Test
	public void getDoubleReturnsDoubleIfExpressionIsLiteral() {

		assertThat(expressionLanguageUtilsImpl
				.getDouble(doubleLiteralExpression), is(2.5));
	}

	@Test
	public void getDoubleThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		configureExpressionFactory(elExpression, "string");

		try {
			expressionLanguageUtilsImpl.getDouble("#{testExpression}");
			fail("A DhevClassCastException should have been thrown here");
		} catch (EvaluationException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Number: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

	/**/
	@Test
	public void getDateReturnsDate() throws ParseException {
		Date d = new SimpleDateFormat("dd-MMM-yyyy h:mm")
				.parse(dateLiteralExpression);
		configureExpressionFactory(elExpression, d);

		assertTrue(expressionLanguageUtilsImpl.getDate(elExpression) instanceof Date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(expressionLanguageUtilsImpl.getDate(elExpression));
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), is(1));
		assertThat(calendar.get(Calendar.MONTH), is(Calendar.JANUARY));
		assertThat(calendar.get(Calendar.YEAR), is(2010));
		assertThat(calendar.get(Calendar.HOUR), is(2));
		assertThat(calendar.get(Calendar.MINUTE), is(8));
	}

	@Test
	public void getDateThrowsExceptionIfExpressionIsLiteral() {
		try {
			expressionLanguageUtilsImpl.getDate(dateLiteralExpression);
			fail("exception should have been thrown when trying to get a Date from a literal");
		} catch (EvaluationException ex) {
			assertThat(
					ex.getMessage(),
					is("unsupported feature: Following EL expression is literal: \"01-jan-2010 14:08\". Dates can't be literal, they must evaluate to a java.util.Date object"));
		}
	}

}
