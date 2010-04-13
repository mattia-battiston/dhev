package com.dhev.constraints.impl;

import static org.hamcrest.Matchers.is;
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
}
