package com.dhev.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

import com.dhev.constraints.impl.MaxELValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidatorClass(MaxELValidator.class)
public @interface MaxEL {

	String message() default "{validator.max}";

	String value() default "";

	boolean includeLimit() default true;

}
