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
import static org.mockito.Mockito.when;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.PatternEL;

public class PatternELValidatorTest {
	private final PatternELValidator patternELImpl = new PatternELValidator();

	@Mock
	private PatternEL mockPatternElAnnotation;

	@Mock
	private ExpressionLanguageUtils mockExpressionLanguageUtils;

	private String regex = "[a-z]+@[a-z]+\\.com";
	private int flags = 0;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		patternELImpl.setExpressionLanguageUtils(mockExpressionLanguageUtils);

		when(mockPatternElAnnotation.regex()).thenReturn("#{regex}");
		when(mockPatternElAnnotation.flags()).thenReturn("#{flags}");

		when(mockExpressionLanguageUtils.getString("#{regex}")).thenReturn(
				regex);
		when(mockExpressionLanguageUtils.getInteger("#{flags}")).thenReturn(
				flags);
		patternELImpl.initialize(mockPatternElAnnotation);

	}

	@Test
	public void isValidReturnsTrueIfStringMatchesRegex() {
		assertThat(patternELImpl.isValid("user@google.com"), is(true));
	}

	@Test
	public void isValidReturnsFalseIfStringDoesNotMatchRegex() {
		assertThat(patternELImpl.isValid("user@google.it"), is(false));
	}

	@Test
	public void isValidReturnsFalseIfStringDoesNotMatchRegexInCase() {
		assertThat(patternELImpl.isValid("USER@GOOGLE.COM"), is(false));
	}

	@Test
	public void isValidReturnsTrueIfStringMatchesRegexWithCaseInsensitiveFlag() {

		when(mockExpressionLanguageUtils.getInteger("#{flags}")).thenReturn(
				Pattern.CASE_INSENSITIVE);

		assertThat(patternELImpl.isValid("USER@GOOGLE.COM"), is(true));
	}
}