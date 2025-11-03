package com.bams.controller;

import com.bams.dtos.AllUsersDto;
import com.bams.dtos.UserDto;
import com.bams.entity.User;
import com.bams.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

//POST /bank/user/register → Register a new user.
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto savedUser = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

//    GET /bank/user/{id} → Get user by ID.

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable long id) {

		UserDto userById = userService.getUser(id);

		return new ResponseEntity<>(userById, HttpStatus.OK);

	}

//  GET /bank/user/all → Get all users.
	
	@GetMapping("/users")
	public ResponseEntity<List<AllUsersDto>> getAllUser() {

		List<AllUsersDto> allUsers = userService.getAllUsers();

		return new ResponseEntity<>(allUsers, HttpStatus.OK);

	}
	

//  PUT /bank/user/{id} → Update user details.
	
	@PutMapping("/update")
	public ResponseEntity<String> udpdateUser(UserDto userDto) {

		boolean update = userService.updateUser(userDto);
		
		if(update) {
			return new ResponseEntity<>(new String("updated successfully"),HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>(new String("Not Updated"),HttpStatus.BAD_REQUEST);
		}

		

	}
	
	
	
//  DELETE /bank/user/{id} → Delete user.
//  GET /bank/user/{id}/accounts → Get all accounts of a user.
//  GET /bank/user/search?email=xyz@gmail.com → Get user by email.
//  PATCH /bank/user/{id}/password → Change password.
//  POST /bank/user/login → Login user.
//  PATCH /bank/user/{id}/role → Update user role.

}
