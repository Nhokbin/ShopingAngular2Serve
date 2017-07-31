package com.dav.shopping.service;

import com.dav.shopping.entity.Permission;

import java.util.List;

/**
 * Created by Duong Vu on 02/07/2017.
 */
public interface PermissionService {

    int countPermission(String actionName, long userId);
    List<Permission>getPermission(long roleId, long functionId, String nameAction);

    boolean save(Permission permission);
}
