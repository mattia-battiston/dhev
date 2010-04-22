package com.dhev.constraints.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.DecimalMaxEL;

public class DecimalMaxELValidatorTest {

	private DecimalMaxELValidator validator = new DecimalMaxELValidator();

	@Mock
	private DecimalMaxEL decimalMaxELAnnotation;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		when(expressionLanguageUtils.getDouble(Matchers.anyString()))
				.thenReturn(2.5);
		validator.setExpressionLanguageUtils(expressionLanguageUtils);

		when(decimalMaxELAnnotation.value()).thenReturn("#{testExpression}");
		when(decimalMaxELAnnotation.includeLimit()).thenReturn(true);
		validator.initialize(decimalMaxELAnnotation);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertTrue(validator.isValid(null));
	}

	@Test
	public void isValidReturnsFalseIfParamIsNotANumber() {
		assertFalse(validator.isValid("not a number"));
	}

	@Test
	public void isValidReturnsTrueIfParamIsLessThanMax() {
		assertTrue(validator.isValid(2.4));
	}

	@Test
	public void isValidReturnsFalseIfParamIsMoreThanMax() {
		assertFalse(validator.isValid(2.6));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMax() {
		assertTrue(validator.isValid(2.5));
	}

	@Test
	public void isValidReturnsFalseIfParamIsEqualToMaxAndIncludeLimitIsFalse() {
		when(decimalMaxELAnnotation.includeLimit()).thenReturn(false);
		validator.initialize(decimalMaxELAnnotation);

		assertFalse(validator.isValid(2.5));
	}

}
