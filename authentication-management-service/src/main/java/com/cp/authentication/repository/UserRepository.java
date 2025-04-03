package com.cp.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp.authentication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndIsactive(String username, Integer isactive);

}
