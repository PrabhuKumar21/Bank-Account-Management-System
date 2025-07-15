package com.bams.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;

	@Column(nullable = false)
	@NotBlank
	private String name;

	@Column(unique = true,nullable = false)
	@Email
	private String email;

	@Column(nullable = false)
	@NotBlank
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts=new ArrayList<>();


}
