package com.dhev.constraints.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.RangeEL;

public class RangeELValidatorTest {

	private RangeELValidator rangeELValidator = new RangeELValidator();

	@Mock
	private RangeEL rangeEL;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		rangeELValidator.setExpressionLanguageUtils(expressionLanguageUtils);
		when(expressionLanguageUtils.getLong("#{min}")).thenReturn(5L);
		when(expressionLanguageUtils.getLong("#{max}")).thenReturn(10L);

		when(rangeEL.min()).thenReturn("#{min}");
		when(rangeEL.max()).thenReturn("#{max}");
		when(rangeEL.includeMin()).thenReturn(true);
		when(rangeEL.includeMax()).thenReturn(true);

		rangeELValidator.initialize(rangeEL);
	}

	@Test
	public void isValidReturnsTrueIfParamIsBetweenMinAndMax() {
		assertTrue(rangeELValidator.isValid(7L));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMin() {
		assertTrue(rangeELValidator.isValid(5L));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToMax() {
		assertTrue(rangeELValidator.isValid(10L));
	}

	@Test
	public void isValidReturnsFalseIfParamIsLowerThanMin() {
		assertFalse(rangeELValidator.isValid(4L));
	}

	@Test
	public void isValidReturnsFalseIfParamIsGreaterThanMax() {
		assertFalse(rangeELValidator.isValid(11L));
	}

	@Test
	public void isValidReturnsFalsIfParamIsEqualToMinAndIncudeMinIsFalse() {
		when(rangeEL.includeMin()).thenReturn(false);
		rangeELValidator.initialize(rangeEL);

		assertFalse(rangeELValidator.isValid(5L));
	}

	@Test
	public void isValidReturnsFalsIfParamIsEqualToMaxAndIncudeMaxIsFalse() {
		when(rangeEL.includeMax()).thenReturn(false);
		rangeELValidator.initialize(rangeEL);

		assertFalse(rangeELValidator.isValid(10L));
	}

	@Test
	public void isValidReturnsTrueIfMinIsNullAndLengthIsLessThanMax() {
		when(rangeEL.min()).thenReturn("");
		rangeELValidator.initialize(rangeEL);

		assertTrue(rangeELValidator.isValid(2L));
	}

	@Test
	public void isValidReturnsTrueIfMaxIsNullAndLengthIsMoreThanMax() {
		when(rangeEL.max()).thenReturn("");
		rangeELValidator.initialize(rangeEL);

		assertTrue(rangeELValidator.isValid(12L));
	}

}
