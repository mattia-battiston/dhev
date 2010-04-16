package com.dhev.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

import com.dhev.constraints.impl.RangeELValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidatorClass(RangeELValidator.class)
public @interface RangeEL {

	String min() default "";

	String max() default "";

	String message() default "{validator.range}";

	boolean includeMax() default true;

	boolean includeMin() default true;

}
