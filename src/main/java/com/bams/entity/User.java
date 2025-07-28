package com.bams.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bams.customvalidations.ValidAge;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;

	@Column(nullable = false)
	@NotBlank
	private String name;

	@Column(unique = true,nullable = false)
	@Email
	private String email;
	
	@Column(nullable=false)
	@ValidAge(minAge=18)
	private LocalDate dob;

	@Column(nullable = false)
	@NotBlank
	@Pattern(
			regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).{8,}$",
			message = "Password must be at least 8 characters long, include one uppercase, one lowercase, one digit, and one special character"
	)
	private String password;


	@Column(nullable = false,length = 20)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(nullable = false,length=10)
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be exactly 10 digits")
	private String phone_num;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts=new ArrayList<>();





}
