package com.dhev;

public class ExpressionLanguageResolverFactory {

	private static ExpressionLanguageUtils resolver = new ExpressionLanguageUtilsImpl();

	public static ExpressionLanguageUtils createResolver() {
		return resolver;
	}

	public static void registerResolver(ExpressionLanguageUtils resolver) {
		ExpressionLanguageResolverFactory.resolver = resolver;
	}
}
