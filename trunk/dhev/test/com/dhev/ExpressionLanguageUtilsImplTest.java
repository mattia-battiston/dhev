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

import javax.el.ELContext;
import javax.el.ExpressionFactory;
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

import com.dhev.exception.DhevClassCastException;

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
	private String booleanLiteralExpression = "true";

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
		configureExpressionFactory(booleanLiteralExpression,
				booleanLiteralExpression);

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
		when(mock.getValue(mockELContext)).thenReturn(value);
		when(mock.isLiteralText()).thenReturn(!el.startsWith("#{"));
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
		} catch (DhevClassCastException e) {
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
		} catch (DhevClassCastException e) {
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
		} catch (DhevClassCastException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Boolean: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

}
