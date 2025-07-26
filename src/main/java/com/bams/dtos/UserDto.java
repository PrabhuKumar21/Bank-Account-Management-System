
package com.bams.dtos;

import java.time.LocalDate;

import com.bams.customvalidations.ValidAge;
import com.bams.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @ValidAge(minAge=18)
    private LocalDate dob;
    
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
    private String phone_num;

}
