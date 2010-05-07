package com.dhev.constraints.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.test.TestResolver;
import com.dhev.test.TestSchema;

public class SizeELValidatorIntTest {

	@BeforeClass
	public static void before() {
		TestSchema.config.addAnnotatedClass(SizeELValidatorModel.class);
		ExpressionLanguageResolverFactory.registerResolver(new TestResolver());

	}

	@Test
	public void testWithMinMax() {

		SizeELValidatorModel testModel = new SizeELValidatorModel();
		testModel.setSizeMin2Max5(new String[1]);
		try {
			TestSchema.save(testModel);
		} catch (InvalidStateException e) {
			InvalidValue invalidValue = e.getInvalidValues()[0];
			assertThat(invalidValue.getPropertyName(), is("sizeMin2Max5"));
			assertThat(invalidValue.getMessage(),
					is("size must be between 2 and 5"));
		}
	}

	@Test
	public void testWithMinMax2() {

		SizeELValidatorModel testModel = new SizeELValidatorModel();
		testModel.setSizeMin2Max5(new String[3]);
		try {
			TestSchema.save(testModel);
		} catch (InvalidStateException e) {
			fail();
		}
	}

	@Test
	public void testWithMinMax3() {

		SizeELValidatorModel testModel = new SizeELValidatorModel();
		testModel.setSizeMin2Max5(new String[8]);
		try {
			TestSchema.save(testModel);
		} catch (InvalidStateException e) {
			InvalidValue invalidValue = e.getInvalidValues()[0];
			assertThat(invalidValue.getPropertyName(), is("sizeMin2Max5"));
			assertThat(invalidValue.getMessage(),
					is("size must be between 2 and 5"));
		}
	}

	@Test
	public void testWithMinDefaultMax() {

		SizeELValidatorModel testModel = new SizeELValidatorModel();
		testModel.setTestDefaultMinMax5(new String[8]);
		try {
			TestSchema.save(testModel);
		} catch (InvalidStateException e) {
			InvalidValue invalidValue = e.getInvalidValues()[0];
			assertThat(invalidValue.getPropertyName(), is("testDefaultMinMax5"));
			assertThat(invalidValue.getMessage(),
					is("size must be between 0 and 5"));
		}
	}
}
