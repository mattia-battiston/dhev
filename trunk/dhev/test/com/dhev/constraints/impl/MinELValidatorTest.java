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