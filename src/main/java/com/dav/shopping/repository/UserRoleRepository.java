package com.dav.shopping.repository;

import com.dav.shopping.entity.UserRole;
import com.dav.shopping.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Duong Vu on 03/07/2017.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,UserRoleId> {

    @Modifying
    @Query("DELETE UserRole p WHERE p.userRoleId.user.id= :userId")
    void deleteWithUser(@Param("userId")long userId);

}
