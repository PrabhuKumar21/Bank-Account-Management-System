package com.bams.repository;

import com.bams.dtos.UserDto;
import com.bams.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
