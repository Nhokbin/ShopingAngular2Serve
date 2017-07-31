package com.dav.shopping.service.impl;

import com.dav.shopping.entity.UserRole;
import com.dav.shopping.repository.UserRoleRepository;
import com.dav.shopping.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Duong Vu on 03/07/2017.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void deleteWithUser(long userId) {
        userRoleRepository.deleteWithUser(userId);
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
