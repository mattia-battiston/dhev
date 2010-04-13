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
import com.dhev.constraints.MinLengthEL;

public class MinLengthELValidatorTest {

	private MinLengthELValidator minLengthELImpl = new MinLengthELValidator();

	@Mock
	private MinLengthEL minLengthEL;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		setMinLengthForTest(5);
	}

	@Test
	public void parameterIsValidIfItIsLongerThanMin() {
		assertThat(minLengthELImpl.isValid("12345678"), is(true));
	}

	@Test
	public void parameterIsNotValidIfItIsAsLongAsMin() {
		assertThat(minLengthELImpl.isValid("12345"), is(true));
	}

	@Test
	public void parameterIsNotValidIfItIsShorterThanMin() {
		assertThat(minLengthELImpl.isValid("1234"), is(false));
	}

	@Test
	public void parameterIsValidIfItIsNull() {
		assertThat(minLengthELImpl.isValid(null), is(true));
	}

	private void setMinLengthForTest(Integer minLength) {
		minLengthELImpl.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.getInteger(Matchers.any(String.class)))
				.thenReturn(minLength);
		minLengthELImpl.initialize(minLengthEL);
	}
}
