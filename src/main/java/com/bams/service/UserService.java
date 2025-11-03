package com.bams.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bams.dtos.AllUsersDto;
import com.bams.dtos.UserDto;
import com.bams.entity.Role;
import com.bams.entity.User;
import com.bams.exception.ResourceNotFoundException;
import com.bams.mapper.UserMapper;
import com.bams.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

	private final ModelMapper modelMapper = new ModelMapper();

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDto createUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);
		user.setRole(Role.CUSTOMER);
		user.setPassword(userDto.getPassword());
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);

	}

	public UserDto getUser(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not found with ID: " + id));
		return modelMapper.map(user, UserDto.class);

	}

	public List<AllUsersDto> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		return allUsers.stream().map(
			
				user -> UserMapper.toAllUserDto(user, new AllUsersDto())
		
				).toList();
		
	}

	public boolean updateUser(UserDto userDto) {
		
		boolean isUpdated=false;
		
		String email = userDto.getEmail();
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email));
		
		User updatedUser = UserMapper.toUser(userDto, user);
		
		userRepository.save(updatedUser);
		isUpdated =true;
		return isUpdated;
	}

}
