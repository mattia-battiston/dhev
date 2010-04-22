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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.MaxEL;

public class MaxELValidatorTest {

	private final MaxELValidator maxELImpl = new MaxELValidator();

	@Mock
	private MaxEL mockMaxElAnnotation;

	@Mock
	private ExpressionLanguageUtils mockExpressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		maxELImpl.setExpressionLanguageUtils(mockExpressionLanguageUtils);

		when(mockExpressionLanguageUtils.getLong(Matchers.anyString()))
				.thenReturn(10L);

		initializeMaxElImpl(true);
	}

	@Test
	public void isValidReturnsTrueIfNumberPassedIsLessThanMax() {
		assertThat(maxELImpl.isValid(9l), is(true));
	}

	@Test
	public void isValidReturnsFalseIfNumberPassedIsGreaterThanMax() {
		assertThat(maxELImpl.isValid(11l), is(false));
	}

	@Test
	public void isValidReturnsTrueIfNumberPassedIsEqualToMax() {
		assertThat(maxELImpl.isValid(10l), is(true));
	}

	@Test
	public void isValidReturnsFalseIfNumberPassedIsEqualToMaxAndIncludeLimitIsSetToFalse() {
		initializeMaxElImpl(false);

		assertThat(maxELImpl.isValid(10l), is(false));
	}

	@Test
	public void isValidReturnsTrueIfNumberPassedIsLessThanMaxAndIncludeLimitIsSetToFalse() {
		initializeMaxElImpl(false);

		assertThat(maxELImpl.isValid(9l), is(true));
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertThat(maxELImpl.isValid(null), is(true));

	}

	private void initializeMaxElImpl(boolean includeLimit) {
		when(mockMaxElAnnotation.includeLimit()).thenReturn(includeLimit);
		maxELImpl.initialize(mockMaxElAnnotation);
	}

	@Test
	public void isValidReturnsFalseIfParamIsNotANumber() {
		assertFalse(maxELImpl.isValid("not a number"));
	}
}
