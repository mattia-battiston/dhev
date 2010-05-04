package com.dhev.constraints.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.DigitsEL;

//TODO: refactor tests after implementing minIntegerDigits and minFractionDigits
public class DigitsELValidatorTest {

	private DigitsELValidator validator = new DigitsELValidator();

	@Mock
	private DigitsEL digitsELAnnotation;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		when(digitsELAnnotation.maxIntegerDigits()).thenReturn(
				"#{integerDigits}");
		when(digitsELAnnotation.maxFractionalDigits()).thenReturn(
				"#{fractionDigits}");
		validator.initialize(digitsELAnnotation);

		when(expressionLanguageUtils.getInteger("#{integerDigits}"))
				.thenReturn(3);
		when(expressionLanguageUtils.getInteger("#{fractionDigits}"))
				.thenReturn(2);
		validator.setExpressionLanguageUtils(expressionLanguageUtils);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertTrue(validator.isValid(null));
	}

	@Test
	public void isValidReturnsTrueIfNumberHasMaxIntegerAndFractionDigits() {
		assertTrue(validator.isValid(123.45));
	}

	@Test
	public void isValidReturnsTrueIfNumberHasLessIntegerDigitsThanMax() {
		assertTrue(validator.isValid(23.45));
		assertTrue(validator.isValid(3.45));
		assertTrue(validator.isValid(.45));
	}

	@Test
	public void isValidReturnsTrueIfNumberHasLessFractionDigitsThanMax() {
		assertTrue(validator.isValid(123.4));
		assertTrue(validator.isValid(123));
	}

	@Test
	public void isValidReturnsFalseIfNumberHasMoreIntegerDigitsThanMax() {
		assertFalse(validator.isValid(1234.45));
		assertFalse(validator.isValid(1234));
	}

	@Test
	public void isValidReturnsFalseIfNumberHasMorefractionDigitsThanMax() {
		assertFalse(validator.isValid(123.456));
		assertFalse(validator.isValid(.456));
	}

	@Test
	public void isValidReturnsFalseIfParamIsNotANumber() {
		assertFalse(validator.isValid("not a number"));
	}

}
