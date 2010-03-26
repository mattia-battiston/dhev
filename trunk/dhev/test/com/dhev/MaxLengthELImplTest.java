package com.dhev;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MaxLengthELImplTest {

	private MaxLengthELImpl maxLengthELImpl = new MaxLengthELImpl();

	@Mock
	private MaxLengthEL maxLengthEL;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		maxLengthELImpl.setExpressionLanguageUtils(expressionLanguageUtils);
		setExpressionLanguageMockMaxLength(5L);
		maxLengthELImpl.initialize(maxLengthEL);
	}

	@Test
	public void isValidReturnsTrueIfStringIsShorterThanMax() {
		assertThat(maxLengthELImpl.isValid("1"), is(true));
	}

	@Test
	public void isValidReturnsTrueIfStringIsAsLongAsMax() {
		assertThat(maxLengthELImpl.isValid("12345"), is(true));
	}

	@Test
	public void isValidReturnsFalseIfStringIsLongerThanMax() {
		assertThat(maxLengthELImpl.isValid("123456"), is(false));
	}

	private void setExpressionLanguageMockMaxLength(Long maxLength) {
		when(expressionLanguageUtils.evaluateEl(Matchers.any(String.class)))
				.thenReturn(maxLength);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertThat(maxLengthELImpl.isValid(null), is(true));
	}
}
