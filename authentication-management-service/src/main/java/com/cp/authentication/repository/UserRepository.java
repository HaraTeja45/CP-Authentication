package com.cp.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp.authentication.model.UserTable;

public interface UserRepository extends JpaRepository<UserTable, Long> {

	Optional<UserTable> findByUsernameAndIsactive(String username, Integer isactive);

}
