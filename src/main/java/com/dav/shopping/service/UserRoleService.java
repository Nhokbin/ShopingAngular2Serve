package com.dav.shopping.service;

import com.dav.shopping.entity.UserRole;

/**
 * Created by Duong Vu on 03/07/2017.
 */
public interface UserRoleService {

    void deleteWithUser(long userId);
    void save(UserRole userRole);
}
