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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.SizeEL;

public class SizeELValidatorTest {

	private SizeELValidator sizeELValidator = new SizeELValidator();

	@Mock
	private SizeEL sizeEL;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		sizeELValidator.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.getInteger("#{min}")).thenReturn(5);
		when(expressionLanguageUtils.getInteger("#{max}")).thenReturn(10);

		when(sizeEL.min()).thenReturn("#{min}");
		when(sizeEL.max()).thenReturn("#{max}");

		sizeELValidator.initialize(sizeEL);
	}

	@Test
	public void isValidReturnsTrueIfParamIsBetweenMinAndMax() {
		assertTrue(sizeELValidator.isValid(new int[] { 1, 2, 3, 4, 5, 6 }));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMin() {
		assertTrue(sizeELValidator.isValid(new int[] { 1, 2, 3, 4, 5 }));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMax() {
		assertTrue(sizeELValidator.isValid(new int[] { 1, 2, 3, 4, 5, 6, 7, 8,
				9, 10 }));
	}

	@Test
	public void isValidReturnsFalseIfParamIsLowerThanMin() {
		assertFalse(sizeELValidator.isValid(new int[] { 1, 2, 3 }));
	}

	@Test
	public void isValidReturnsFalseIfParamIsGreaterThanMax() {
		assertFalse(sizeELValidator.isValid(new int[] { 1, 2, 3, 4, 5, 6, 7, 8,
				9, 10, 11 }));
	}
}
