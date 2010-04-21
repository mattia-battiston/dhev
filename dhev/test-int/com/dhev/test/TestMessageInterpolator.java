package com.dhev.test;

import org.hibernate.validator.MessageInterpolator;
import org.hibernate.validator.Validator;

public class TestMessageInterpolator implements MessageInterpolator {

	@Override
	public String interpolate(String message, Validator validator,
			MessageInterpolator defaultInterpolator) {
		System.out.println("message[" + message + "] validator[" + validator
				+ "] defaultInterpolator[" + defaultInterpolator + "]");
		String msg = defaultInterpolator.interpolate(message, validator,
				defaultInterpolator);

		return interpolate(msg);
	}

	private String interpolate(String msg) {

		// TODO
		return msg;
	}
}
