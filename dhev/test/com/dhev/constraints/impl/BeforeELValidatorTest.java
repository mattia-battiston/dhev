package com.dhev.constraints.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dhev.ExpressionLanguageUtils;
import com.dhev.constraints.BeforeEL;

public class BeforeELValidatorTest {

	private BeforeELValidator validator = new BeforeELValidator();

	@Mock
	private BeforeEL beforeELAnnotation;

	@Mock
	private ExpressionLanguageUtils expressionLanguageUtils;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		when(beforeELAnnotation.value()).thenReturn("#{testExpression}");
		when(beforeELAnnotation.includeLimit()).thenReturn(true);
		validator.initialize(beforeELAnnotation);

		when(expressionLanguageUtils.getDate("#{testExpression}")).thenReturn(
				parseDate("21-dec-2010 23:23"));
		validator.setExpressionLanguageUtils(expressionLanguageUtils);
	}

	@Test
	public void isValidReturnsTrueIfParamIsNull() {
		assertTrue(validator.isValid(null));
	}

	@Test
	public void isValidReturnsFalseIfParamIsNotADate() {
		assertFalse(validator.isValid("not a date"));
	}

	@Test
	public void isValidReturnsTrueIfParamIsBeforeLimit() {
		assertTrue(validator.isValid(parseDate("20-dec-2010 23:23")));
		assertTrue(validator.isValid(parseDate("21-nov-2010 23:23")));
		assertTrue(validator.isValid(parseDate("21-dec-2009 23:23")));
		assertTrue(validator.isValid(parseDate("21-dec-2010 22:23")));
		assertTrue(validator.isValid(parseDate("21-dec-2010 23:22")));
	}

	@Test
	public void isValidReturnsFalseIfParamIsAfterLimit() {
		assertFalse(validator.isValid(parseDate("22-dec-2010 23:23")));
		assertFalse(validator.isValid(parseDate("21-jan-2011 23:23")));
		assertFalse(validator.isValid(parseDate("21-dec-2011 23:23")));
		assertFalse(validator.isValid(parseDate("21-dec-2010 23:24")));
	}

	@Test
	public void isValidReturnsTrueIfParamIsEqualToLimit() {
		assertTrue(validator.isValid(parseDate("21-dec-2010 23:23")));
	}

	@Test
	public void isValidReturnsFalseIfParamIsEqualToLimitAndIncludeLimitIsFalse() {
		when(beforeELAnnotation.includeLimit()).thenReturn(false);
		validator.initialize(beforeELAnnotation);

		assertFalse(validator.isValid(parseDate("21-dec-2010 23:23")));
	}

	private Date parseDate(String date) {
		try {
			return new SimpleDateFormat("dd-MMM-yyyy h:mm").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
