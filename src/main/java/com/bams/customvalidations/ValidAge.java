package com.bams.customvalidations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AgeValidator.class) // This says which class will handle validation
@Target({ ElementType.FIELD }) // You will use this on fields
@Retention(RetentionPolicy.RUNTIME) // Available at runtime

public @interface ValidAge {
	
	   String message() default "Age must be 18 or older";

	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};

		int minAge();

}
