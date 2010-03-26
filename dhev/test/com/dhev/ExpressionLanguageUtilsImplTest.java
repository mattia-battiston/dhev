package com.dhev;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.jboss.seam.el.SeamExpressionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
	private SeamExpressionFactory seamExpressionFactory;

	@Mock
	private ValueExpression mockValueExpression;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(mockFacesContext);
	}

	@Test
	public void evaluateElObtainsEvaluatedObject() {
		String expression = "#{testExpresssion}";
		when(mockFacesContext.getELContext()).thenReturn(mockELContext);
		when(mockFacesContext.getApplication()).thenReturn(mockApplication);
		when(mockApplication.getExpressionFactory()).thenReturn(
				seamExpressionFactory);
		when(
				seamExpressionFactory.createValueExpression(mockELContext,
						expression, Object.class)).thenReturn(
				mockValueExpression);
		when(mockValueExpression.getValue(mockELContext)).thenReturn(2l);

		Object result = expressionLanguageUtilsImpl.evaluateEl(expression);

		assertThat(((Long) result), is(2l));

	}
}
