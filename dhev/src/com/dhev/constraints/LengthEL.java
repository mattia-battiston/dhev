package com.dhev.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

import com.dhev.constraints.impl.LengthELValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidatorClass(LengthELValidator.class)
public @interface LengthEL {

	String max() default "";

	String min() default "";

	String message() default "{validator.length}";

	boolean includeMax() default true;

	boolean includeMin() default true;

}
