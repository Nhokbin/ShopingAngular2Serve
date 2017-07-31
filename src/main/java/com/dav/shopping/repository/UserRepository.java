package com.dav.shopping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dav.shopping.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User save(User user);

	@Query(value = "SELECT u FROM User u WHERE u.lastName LIKE %:filter% OR u.email LIKE %:filter% OR u.firstName LIKE %:filter%")
	Page<User> findAll(@Param("filter")String filter, Pageable pageable);

	User findByEmail(String email);
}

