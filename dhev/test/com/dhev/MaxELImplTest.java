package com.dhev;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MaxELImplTest {

	private final MaxELImpl maxELImpl = new MaxELImpl();

	@Mock
	private MaxEL mockMaxElAnnotation;

	@Mock
	private ExpressionLanguageUtils mockExpressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		maxELImpl.setExpressionLanguageUtils(mockExpressionLanguageUtils);

		when(mockExpressionLanguageUtils.evaluateEl(Matchers.anyString()))
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
	public void isValidReturnsTrueIfValueIsDouble() {
		assertThat(maxELImpl.isValid(10.1), is(false));
	}

	@Test
	public void isValidCallsEvaluateElWithRightParam() {
		String expression = "#{testMaxExpression}";
		when(mockExpressionLanguageUtils.evaluateEl(expression))
				.thenReturn(10l);
		when(mockMaxElAnnotation.value()).thenReturn(expression);

		maxELImpl.initialize(mockMaxElAnnotation);
		maxELImpl.isValid(9l);

		verify(mockExpressionLanguageUtils).evaluateEl(expression);
	}

	private void initializeMaxElImpl(boolean includeLimit) {
		when(mockMaxElAnnotation.includeLimit()).thenReturn(includeLimit);
		maxELImpl.initialize(mockMaxElAnnotation);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertThat(maxELImpl.isValid(null), is(true));

	}
}
