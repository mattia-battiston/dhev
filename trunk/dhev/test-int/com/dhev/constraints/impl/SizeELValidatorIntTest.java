package com.dhev.constraints.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
	public void test() {
		ExpressionLanguageResolverFactory.registerResolver(new TestResolver());

		SizeELValidatorModel testModel = new SizeELValidatorModel();
		testModel.setSize2_5(new String[] { "1" });
		try {
			TestSchema.save(testModel);
		} catch (InvalidStateException e) {
			InvalidValue invalidValue = e.getInvalidValues()[0];
			assertThat(invalidValue.getPropertyName(), is("size2_5"));
			assertThat(invalidValue.getMessage(),
					is("size must be between 2 and 5"));
		}
	}

	@Test
	public void test2() {
		System.out.println("ok1");
		SizeELValidatorModel testModel = new SizeELValidatorModel();
		TestSchema.save(testModel);
		System.out.println("ok2");
	}
}
