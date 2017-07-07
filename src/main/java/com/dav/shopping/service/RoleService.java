package com.dav.shopping.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.dav.shopping.entity.Role;

public interface RoleService {

	List<Role> findAll();
	Page<Role> findAll(int page, int pageSize, String filter);
	List<Role> findByUser(long userId);

	Role findByRole(String role);
	
	
	Role findById(long id);

	boolean createRole(Role role);
	boolean update(Role role);
	boolean delete(Role role);
	Role savePermission(Role role);
}
