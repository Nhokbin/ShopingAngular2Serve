package com.dav.shopping.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dav.shopping.entity.Action;
import com.dav.shopping.entity.Role;
import com.dav.shopping.repository.ActionRepository;
import com.dav.shopping.repository.RoleRepository;
import com.dav.shopping.service.ActionService;
import com.dav.shopping.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    private static final int PAGE_SIZE = 20;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findByUser(String email) {

        return roleRepository.findByUser(email);
    }

    @Override
    public Role findByRole(String role) {
        // TODO Auto-generated method stub
        return roleRepository.findByRole(role);
    }

    @Override
    public Page<Role> findAll(int page, int pageSize, String filter) {
        // TODO Auto-generated method stub
        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        return roleRepository.findAll(filter, request);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(long id) {
        // TODO Auto-generated method stub
        return roleRepository.findOne(id);
    }

    @Override
    public boolean createRole(Role role) {
        // TODO Auto-generated method stub
        return roleRepository.saveAndFlush(role) != null ? true : false;
    }

    @Override
    public boolean update(Role role) {
        // TODO Auto-generated method stub
        return roleRepository.save(role) != null ? true : false;
    }

    @Override
    public boolean delete(Role role) {
        // TODO Auto-generated method stub
        try {
            roleRepository.delete(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Role savePermission(Role role) {
        Role entity = roleRepository.findOne(role.getId());

        entity.setId(role.getId());
        entity.setDescription(role.getDescription());
        entity.setRole(role.getRole());

        entity.setActions(role.getActions());

        entity.getFunctions().clear();
        entity.getFunctions().addAll(role.getFunctions());

        entity.setUsers(role.getUsers());

        roleRepository.save(entity);


        return entity;
    }
}
