package com.dhev;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dhev.exception.DhevClassCastException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class ExpressionLanguageUtilsImplTest {

	private final ExpressionLanguageUtilsImpl expressionLanguageUtilsImpl = new ExpressionLanguageUtilsImpl();

	@Mock
	private FacesContext mockFacesContext;

	@Mock
	private ELContext mockELContext;

	@Mock
	private Application mockApplication;

	@Mock
	private ExpressionFactory expressionFactory;

	@Mock
	private ValueExpression mockValueExpression;

	private String elExpression = "#{testExpression}";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(mockFacesContext);
		when(mockFacesContext.getELContext()).thenReturn(mockELContext);
		when(mockFacesContext.getApplication()).thenReturn(mockApplication);
		when(mockApplication.getExpressionFactory()).thenReturn(
				expressionFactory);
		when(
				expressionFactory.createValueExpression(mockELContext,
						elExpression, Object.class)).thenReturn(
				mockValueExpression);
		when(mockValueExpression.getValue(mockELContext)).thenReturn(2l);
	}

	@Test
	public void evaluateElObtainsEvaluatedObject() {
		Number result = expressionLanguageUtilsImpl.evaluateEl(elExpression,
				Number.class);

		assertThat(((Long) result), is(2l));
	}

	@Test
	public void getLongReturnsLong() {
		when(mockValueExpression.getValue(mockELContext)).thenReturn(2);

		assertThat(expressionLanguageUtilsImpl.getLong("#{testExpression}"),
				is(2L));
	}

	@Test
	public void getLongThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		when(mockValueExpression.getValue(mockELContext)).thenReturn("string");

		try {
			expressionLanguageUtilsImpl.getLong("#{testExpression}");
			fail("A DhevClassCastException should have been thrown here");
		} catch (DhevClassCastException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Number: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

	@Test
	public void getIntegerReturnsInteger() {
		when(mockValueExpression.getValue(mockELContext)).thenReturn(2.5);

		assertThat(expressionLanguageUtilsImpl.getInteger("#{testExpression}"),
				is(2));
	}

	@Test
	public void getIntegerThrowsCorrectExceptionWhenClassCastExceptionHappens() {
		when(mockValueExpression.getValue(mockELContext)).thenReturn("string");

		try {
			expressionLanguageUtilsImpl.getInteger("#{testExpression}");
			fail("A DhevClassCastException should have been thrown here");
		} catch (DhevClassCastException e) {
			assertThat(
					e.getMessage(),
					is("Following EL expression does not evaluate to java.lang.Number: \"#{testExpression}\""));
			assertTrue(e.getCause() instanceof ClassCastException);
		}
	}

}
