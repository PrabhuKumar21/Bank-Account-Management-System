package com.bams.mapper;

import com.bams.dtos.AllUsersDto;
import com.bams.dtos.UserDto;
import com.bams.entity.User;

public class UserMapper {

	public static AllUsersDto toAllUserDto(User user, AllUsersDto allUserDto) {

		allUserDto.setDob(user.getDob());
		allUserDto.setEmail(user.getEmail());
		allUserDto.setName(user.getName());
		allUserDto.setPhone_num(user.getPhone_num());

		return allUserDto;
	}

	public static User toUser(UserDto userDto, User user) {
		
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setPhone_num(userDto.getPhone_num());
		
		return user;
		
		

	}

}