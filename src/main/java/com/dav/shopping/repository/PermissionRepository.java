package com.dav.shopping.repository;

import com.dav.shopping.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Duong Vu on 02/07/2017.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(value = "SELECT COUNT(p) FROM Permission p " +
            "WHERE p.primaryKey.action.name = :actionName " +
            "AND p.primaryKey.role.id IN (SELECT ur.userRoleId.role.id FROM UserRole ur WHERE ur.userRoleId.user.id=:userId)")
    int countPermission(@Param("actionName") String actionName, @Param("userId") long userId);


    @Query(value = "SELECT p.primaryKey.action.id FROM Permission p " +
            "WHERE p.primaryKey.role.id= :roleId " +
            "AND p.primaryKey.function.id = :functionId " +
            "AND p.primaryKey.action.name LIKE %:nameAction%")
    List<Permission> getPermission(@Param("roleId") long roleId,
                                   @Param("functionId") long functionId,
                                   @Param("nameAction") String nameAction);
}
