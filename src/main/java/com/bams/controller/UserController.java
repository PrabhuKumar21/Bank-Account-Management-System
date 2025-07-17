package com.bams.controller;

import com.bams.entity.User;
import com.bams.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService){
      this.userService=userService;
  }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
        User savedUser= userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
