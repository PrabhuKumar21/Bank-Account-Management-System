package com.bams.dtos;

import java.time.LocalDate;

import com.bams.customvalidations.ValidAge;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllUsersDto {
	
	  @NotBlank
	    private String name;

	    @Email
	    private String email;
	    
	    @NotNull
	    @ValidAge(minAge=18)
	    private LocalDate dob;
	    
	    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
	    private String phone_num;

}
