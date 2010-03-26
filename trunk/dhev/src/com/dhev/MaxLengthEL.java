package com.dhev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidatorClass(MaxLengthELImpl.class)
public @interface MaxLengthEL {

	String message() default "{validator.maxLength}";

	String value() default "";

}
