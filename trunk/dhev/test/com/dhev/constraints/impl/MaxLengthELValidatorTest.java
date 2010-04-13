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

		setMaxLengthForTest(5L);
	}

	@Test
	public void parameterIsValidIfItIsShorterThanMax() {
		assertThat(maxLengthELImpl.isValid("1"), is(true));
	}

	@Test
	public void parameterIsNotValidIfItIsAsLongAsMax() {
		assertThat(maxLengthELImpl.isValid("12345"), is(true));
	}

	@Test
	public void parameterIsNotValidIfItIsLongerThanMax() {
		assertThat(maxLengthELImpl.isValid("123456"), is(false));
	}

	@Test
	public void parameterIsValidIfItIsNull() {
		assertThat(maxLengthELImpl.isValid(null), is(true));
	}

	private void setMaxLengthForTest(Long maxLength) {
		maxLengthELImpl.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.getLong(Matchers.any(String.class)))
				.thenReturn(maxLength);
		maxLengthELImpl.initialize(maxLengthEL);

	}
}
