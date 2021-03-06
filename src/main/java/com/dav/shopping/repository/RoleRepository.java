package com.dav.shopping.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dav.shopping.entity.Action;
import com.dav.shopping.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query(value = "SELECT r FROM Role r WHERE r.role LIKE %:filter% OR r.description LIKE %:filter%")
	Page<Role> findAll(@Param("filter")String filter, Pageable pageable);
	
<<<<<<< HEAD
	@Query(value = "SELECT r FROM Role r  JOIN r.users u WHERE u.email = :email")
	List<Role> findByUser(@Param("email")String email);
	
	
	
=======
	@Query(value = "SELECT ur.userRoleId.role FROM UserRole ur WHERE ur.userRoleId.user.id= :userId")
	List<Role> findByUser(@Param("userId")long userId);

>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
	Role findByRole(String role);
	
}
