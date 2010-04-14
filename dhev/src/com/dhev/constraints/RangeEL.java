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

	String min() default "" + Long.MIN_VALUE;

	String max() default "" + Long.MAX_VALUE;

	String message() default "{validator.range}";

}
