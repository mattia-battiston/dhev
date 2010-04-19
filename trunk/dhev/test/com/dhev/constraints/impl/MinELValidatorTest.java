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
package com.dhev.constraints.impl;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.MinEL;


public class MinELValidatorTest {
	private final MinELValidator minELImpl = new MinELValidator();

	@Mock
	private MinEL mockMinElAnnotation;

	@Mock
	private ExpressionLanguageUtils mockExpressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		minELImpl.setExpressionLanguageUtils(mockExpressionLanguageUtils);

		when(mockExpressionLanguageUtils.getLong(Matchers.anyString()))
				.thenReturn(10L);

		initializeMinElImpl(true);
	}

	@Test
	public void isValidReturnsTrueIfNumberPassedIsGreaterThanMin() {
		assertThat(minELImpl.isValid(11l), is(true));
	}

	@Test
	public void isValidReturnsFalseIfNumberPassedIsLessThanMin() {
		assertThat(minELImpl.isValid(9l), is(false));
	}

	@Test
	public void isValidReturnsTrueIfNumberPassedIsEqualToMin() {
		assertThat(minELImpl.isValid(10l), is(true));
	}

	@Test
	public void isValidReturnsFalseIfNumberPassedIsEqualToMinAndIncludeLimitIsSetToFalse() {
		initializeMinElImpl(false);

		assertThat(minELImpl.isValid(10l), is(false));
	}

	@Test
	public void isValidReturnsTrueIfValueIsDouble() {
		assertThat(minELImpl.isValid(10.1), is(true));
	}

	private void initializeMinElImpl(boolean includeLimit) {
		when(mockMinElAnnotation.includeLimit()).thenReturn(includeLimit);
		minELImpl.initialize(mockMinElAnnotation);
	}

	@Test
	public void isValidCallsEvaluateElWithRightParam() {
		String expression = "#{testMaxExpression}";
		when(mockExpressionLanguageUtils.getLong(expression)).thenReturn(10l);
		when(mockMinElAnnotation.value()).thenReturn(expression);

		minELImpl.initialize(mockMinElAnnotation);
		minELImpl.isValid(9l);

		verify(mockExpressionLanguageUtils).getLong(expression);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertThat(minELImpl.isValid(null), is(true));

	}
}