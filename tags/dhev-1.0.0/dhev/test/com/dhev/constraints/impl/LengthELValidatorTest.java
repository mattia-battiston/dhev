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
import com.dhev.constraints.LengthEL;

public class LengthELValidatorTest {

	private final LengthELValidator validator = new LengthELValidator();

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Mock
	private LengthEL lengthELAnnotation;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		when(expressionLanguageUtils.getInteger("#{min}")).thenReturn(5);
		when(expressionLanguageUtils.getInteger("#{max}")).thenReturn(10);
		validator.setExpressionLanguageUtils(expressionLanguageUtils);

		when(lengthELAnnotation.min()).thenReturn("#{min}");
		when(lengthELAnnotation.max()).thenReturn("#{max}");
		when(lengthELAnnotation.includeMin()).thenReturn(true);
		when(lengthELAnnotation.includeMax()).thenReturn(true);
		validator.initialize(lengthELAnnotation);
	}

	@Test
	public void isValidReturnsTrueIfLengthIsBetweenMinAndMax() {
		assertTrue(validator.isValid("123456"));
	}

	@Test
	public void isValidReturnsFalseIfLengthIsLessThanMin() {
		assertFalse(validator.isValid("1234"));
	}

	@Test
	public void isValidReturnsFalseIfLengthIsMoreThanMax() {
		assertFalse(validator.isValid("12345678910"));
	}

	@Test
	public void isValidReturnsTrueIfLengthIsSameAsMin() {
		assertTrue(validator.isValid("12345"));
	}

	@Test
	public void isValidReturnsTrueIfLengthIsSameAsMax() {
		assertTrue(validator.isValid("1234567890"));
	}

	@Test
	public void isValidReturnsFalseIfLengthIsSameAsMinAndIncludeMinIsFalse() {
		when(lengthELAnnotation.includeMin()).thenReturn(false);
		validator.initialize(lengthELAnnotation);

		assertFalse(validator.isValid("12345"));
	}

	@Test
	public void isValidReturnsFalseIfLengthIsSameAsMaxAndIncludeMaxIsFalse() {
		when(lengthELAnnotation.includeMax()).thenReturn(false);
		validator.initialize(lengthELAnnotation);

		assertFalse(validator.isValid("1234567890"));
	}

	@Test
	public void isValidReturnsTrueIfMinIsNullAndLengthIsLessThanMax() {
		when(lengthELAnnotation.min()).thenReturn("");
		validator.initialize(lengthELAnnotation);

		assertTrue(validator.isValid("123456"));
	}

	@Test
	public void isValidReturnsTrueIfMaxIsNullAndLengthIsMoreThanMax() {
		when(lengthELAnnotation.max()).thenReturn("");
		validator.initialize(lengthELAnnotation);

		assertTrue(validator.isValid("1234567890123456"));
	}

}
