package com.dhev;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MinLengthELImplTest {

	private MinLengthELImpl minLengthELImpl = new MinLengthELImpl();

	@Mock
	private MinLengthEL minLengthEL;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		setMinLengthForTest(5L);
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

	private void setMinLengthForTest(Long minLength) {
		minLengthELImpl.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.evaluateEl(Matchers.any(String.class)))
				.thenReturn(minLength);
		minLengthELImpl.initialize(minLengthEL);
	}
}
