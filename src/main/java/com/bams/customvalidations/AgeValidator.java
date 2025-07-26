package com.bams.customvalidations;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate>{

	int minAge;
	
	   public void initialize(ValidAge constraintAnnotation) {
	        this.minAge = constraintAnnotation.minAge();  // Read from annotation
	    }
	
	
	@Override
	public boolean isValid(LocalDate Age, ConstraintValidatorContext context) {

		LocalDate today = LocalDate.now();
		
		int years = Period.between(Age, today).getYears();
		

		return years>=minAge;
	}



}
