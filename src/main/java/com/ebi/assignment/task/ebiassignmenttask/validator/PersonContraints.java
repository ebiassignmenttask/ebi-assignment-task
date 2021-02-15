package com.ebi.assignment.task.ebiassignmenttask.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PersonRootValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonContraints {
	String message() default "{Pesondetails.invalid}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
