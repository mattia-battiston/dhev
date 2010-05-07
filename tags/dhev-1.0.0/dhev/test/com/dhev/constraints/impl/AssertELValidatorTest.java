package com.dhev.constraints.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.AssertEL;

public class AssertELValidatorTest {

	private AssertELValidator validator = new AssertELValidator();

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Mock
	private AssertEL assertELAnnotation;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		when(expressionLanguageUtils.getBoolean("#{testExpression}"))
				.thenReturn(false);
		validator.setExpressionLanguageUtils(expressionLanguageUtils);

		when(assertELAnnotation.value()).thenReturn("#{testExpression}");
		validator.initialize(assertELAnnotation);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertTrue(validator.isValid(null));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToAssert() {
		assertTrue(validator.isValid(false));
	}

	@Test
	public void isValidReturnsFalseIfParamIsNotEqualToAssert() {
		assertFalse(validator.isValid(true));
	}

}
