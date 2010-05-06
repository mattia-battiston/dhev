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
				"#{maxIntegerDigits}");
		when(digitsELAnnotation.minIntegerDigits()).thenReturn(
				"#{minIntegerDigits}");
		when(digitsELAnnotation.maxFractionalDigits()).thenReturn(
				"#{maxFractionDigits}");
		when(digitsELAnnotation.minFractionalDigits()).thenReturn(
				"#{minFractionDigits}");
		validator.initialize(digitsELAnnotation);

		when(expressionLanguageUtils.getInteger("#{maxIntegerDigits}"))
				.thenReturn(4);
		when(expressionLanguageUtils.getInteger("#{minIntegerDigits}"))
				.thenReturn(2);
		when(expressionLanguageUtils.getInteger("#{maxFractionDigits}"))
				.thenReturn(3);
		when(expressionLanguageUtils.getInteger("#{minFractionDigits}"))
				.thenReturn(1);
		validator.setExpressionLanguageUtils(expressionLanguageUtils);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertTrue(validator.isValid(null));
	}

	@Test
	public void isValidReturnsTrueIfIntegerDigitsAreBetweenMinAndMax() {
		assertTrue(validator.isValid(123.45));
	}

	@Test
	public void isValidReturnsTrueIfIntegerDigitsAreEqualToMax() {
		assertTrue(validator.isValid(1234.45));
	}

	@Test
	public void isValidReturnsTrueIfIntegerDigitsAreEqualToMin() {
		assertTrue(validator.isValid(12.45));
	}

	@Test
	public void isValidReturnsFalseIfIntegerDigitsAreMoreThanMax() {
		assertFalse(validator.isValid(12345.45));
	}

	@Test
	public void isValidReturnsFalseIfIntegerDigitsAreLessThanMin() {
		assertFalse(validator.isValid(1.45));
	}

	@Test
	public void isValidReturnsTrueIfFractionalDigitsAreBetweenMinAndMax() {
		assertTrue(validator.isValid(123.45));
	}

	@Test
	public void isValidReturnsTrueIfFractionalDigitsAreEqualToMax() {
		assertTrue(validator.isValid(123.456));
	}

	@Test
	public void isValidReturnsTrueIfFractionalDigitsAreEualToMin() {
		assertTrue(validator.isValid(123.4));
	}

	@Test
	public void isValidReturnsFalseIfFractionalDigitsAreMoreThanMax() {
		assertFalse(validator.isValid(123.4567));
	}

	@Test
	public void isValidReturnsFalseIfFractionalDigitsAreLessThanMin() {
		assertFalse(validator.isValid(123));
	}

	@Test
	public void isValidReturnTrueIfIntegerDigitsAreMoreThanMaxButMaxIsNotSet() {
		when(digitsELAnnotation.maxIntegerDigits()).thenReturn("");
		validator.initialize(digitsELAnnotation);

		assertTrue(validator.isValid(12345.45));
	}

	@Test
	public void isValidReturnTrueIfIntegerDigitsAreLessThanMinButMinIsNotSet() {
		when(digitsELAnnotation.minIntegerDigits()).thenReturn("");
		validator.initialize(digitsELAnnotation);

		assertTrue(validator.isValid(1.45));
	}

	@Test
	public void isValidReturnTrueIfFractionalDigitsAreMoreThanMaxButMaxIsNotSet() {
		when(digitsELAnnotation.maxFractionalDigits()).thenReturn("");
		validator.initialize(digitsELAnnotation);

		assertTrue(validator.isValid(12.4567));
	}

	@Test
	public void isValidReturnTrueIfFractionalDigitsAreLessThanMinButMinIsNotSet() {
		when(digitsELAnnotation.minFractionalDigits()).thenReturn("");
		validator.initialize(digitsELAnnotation);

		assertTrue(validator.isValid(12.4));
	}

}
