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
import com.dhev.constraints.DecimalRangeEL;

public class DecimalRangeELValidatorTest {

	private DecimalRangeELValidator decimalRangeELValidator = new DecimalRangeELValidator();

	@Mock
	private DecimalRangeEL decimalRangeELAnnotation;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		decimalRangeELValidator
				.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.getDouble("#{min}")).thenReturn(2.5);
		when(expressionLanguageUtils.getDouble("#{max}")).thenReturn(5.2);

		when(decimalRangeELAnnotation.min()).thenReturn("#{min}");
		when(decimalRangeELAnnotation.max()).thenReturn("#{max}");
		when(decimalRangeELAnnotation.includeMin()).thenReturn(true);
		when(decimalRangeELAnnotation.includeMax()).thenReturn(true);

		decimalRangeELValidator.initialize(decimalRangeELAnnotation);
	}

	@Test
	public void isValidReturnsTrueIfParamIsBetweenMinAndMax() {
		assertTrue(decimalRangeELValidator.isValid(3.1));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMin() {
		assertTrue(decimalRangeELValidator.isValid(2.5));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMax() {
		assertTrue(decimalRangeELValidator.isValid(5.2));
	}

	@Test
	public void isValidReturnsFalseIfParamIsLowerThanMin() {
		assertFalse(decimalRangeELValidator.isValid(2.4));
	}

	@Test
	public void isValidReturnsFalseIfParamIsGreaterThanMax() {
		assertFalse(decimalRangeELValidator.isValid(5.3));
	}

	@Test
	public void isValidReturnsFalsIfParamIsEqualToMinAndIncudeMinIsFalse() {
		when(decimalRangeELAnnotation.includeMin()).thenReturn(false);
		decimalRangeELValidator.initialize(decimalRangeELAnnotation);

		assertFalse(decimalRangeELValidator.isValid(2.5));
	}

	@Test
	public void isValidReturnsFalsIfParamIsEqualToMaxAndIncudeMaxIsFalse() {
		when(decimalRangeELAnnotation.includeMax()).thenReturn(false);
		decimalRangeELValidator.initialize(decimalRangeELAnnotation);

		assertFalse(decimalRangeELValidator.isValid(5.2));
	}

	@Test
	public void isValidReturnsTrueIfMinIsNullAndParamIsLessThanMax() {
		when(decimalRangeELAnnotation.min()).thenReturn("");
		decimalRangeELValidator.initialize(decimalRangeELAnnotation);

		assertTrue(decimalRangeELValidator.isValid(2.4));
	}

	@Test
	public void isValidReturnsTrueIfMaxIsNullAndParamIsMoreThanMax() {
		when(decimalRangeELAnnotation.max()).thenReturn("");
		decimalRangeELValidator.initialize(decimalRangeELAnnotation);

		assertTrue(decimalRangeELValidator.isValid(5.3));
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertTrue(decimalRangeELValidator.isValid(null));
	}

	@Test
	public void isValidReturnsFalseIfParamIsNotANumber() {
		assertFalse(decimalRangeELValidator.isValid("not a number"));
	}

}
