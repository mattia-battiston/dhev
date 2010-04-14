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
import com.dhev.constraints.MaxLengthEL;

public class MaxLengthELValidatorTest {

	private MaxLengthELValidator maxLengthELImpl = new MaxLengthELValidator();

	@Mock
	private MaxLengthEL maxLengthEL;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		initializeValidator(5);
	}

	@Test
	public void parameterIsValidIfItIsShorterThanMax() {
		assertThat(maxLengthELImpl.isValid("1"), is(true));
	}

	@Test
	public void parameterIsValidIfItIsAsLongAsMax() {
		assertThat(maxLengthELImpl.isValid("12345"), is(true));
	}

	@Test
	public void parameterIsNotValidIfItIsAsLongAsMaxAndIncludeLimitIsFalse() {
		when(maxLengthEL.includeLimit()).thenReturn(false);
		maxLengthELImpl.initialize(maxLengthEL);

		assertThat(maxLengthELImpl.isValid("12345"), is(false));
	}

	@Test
	public void parameterIsNotValidIfItIsLongerThanMax() {
		assertThat(maxLengthELImpl.isValid("123456"), is(false));
	}

	@Test
	public void parameterIsValidIfItIsNull() {
		assertThat(maxLengthELImpl.isValid(null), is(true));
	}

	private void initializeValidator(Integer maxLength) {
		maxLengthELImpl.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.getInteger(Matchers.any(String.class)))
				.thenReturn(5);
		when(maxLengthEL.includeLimit()).thenReturn(true);

		maxLengthELImpl.initialize(maxLengthEL);
	}
}
