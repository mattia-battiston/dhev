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
}
