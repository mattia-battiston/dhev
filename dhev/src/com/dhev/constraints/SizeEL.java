package com.dhev.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

import com.dhev.constraints.impl.SizeELValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidatorClass(SizeELValidator.class)
public @interface SizeEL {

	String max() default "" + Integer.MAX_VALUE;

	String min() default "0";

	String message() default "{validator.size}";

}
