package com.dav.shopping.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.dav.shopping.entity.Role;

public interface RoleService {

	List<Role> findAll();
	Page<Role> findAll(int page, int pageSize, String filter);
<<<<<<< HEAD
	List<Role> findByUser(String email);
=======
	List<Role> findByUser(long userId);
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92

	Role findByRole(String role);
	
	
	Role findById(long id);

	boolean createRole(Role role);
	boolean update(Role role);
	boolean delete(Role role);
	Role savePermission(Role role);
}
