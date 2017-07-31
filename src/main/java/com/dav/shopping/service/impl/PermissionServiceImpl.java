package com.dav.shopping.service.impl;

import com.dav.shopping.entity.Permission;
import com.dav.shopping.repository.PermissionRepository;
import com.dav.shopping.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Duong Vu on 02/07/2017.
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public int countPermission(String actionName, long userId) {
        return permissionRepository.countPermission(actionName, userId);
    }

    @Override
    public List<Permission> getPermission(long roleId, long functionId, String nameAction) {
        return permissionRepository.getPermission(roleId, functionId, nameAction);
    }

    @Override
    @Transactional
    public boolean save(Permission permission) {
        try{
            permissionRepository.save(permission);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
