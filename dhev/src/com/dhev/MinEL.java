package com.dhev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidatorClass(MinELImpl.class)
public @interface MinEL {

	String message() default "{validator.min}";

	String value() default "";

	boolean includeLimit() default true;

}
