package com.dhev.constraints.utils;

public class ValidatorUtils {
	public static boolean isParamNotSet(String el) {
		return el != null && el.trim().length() == 0;
	}

}
