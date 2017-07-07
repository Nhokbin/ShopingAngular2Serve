package com.dav.shopping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Duong Vu on 02/07/2017.
 */
@Entity
@Table(name = "userRole")
@AssociationOverrides({
        @AssociationOverride(name = "userRoleId.user",
                joinColumns = @JoinColumn(name = "userId")),
        @AssociationOverride(name = "userRoleId.role",
                joinColumns = @JoinColumn(name = "roleId")) })
public class UserRole implements Serializable{

    @EmbeddedId
    private UserRoleId userRoleId = new UserRoleId();

    public UserRoleId getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRoleId userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Transient
    public User getUser() {
        return getUserRoleId().getUser();
    }

    public void setUser(User user) {
        getUserRoleId().setUser(user);
    }

    @Transient
    public Role getRole() {
        return getUserRoleId().getRole();
    }

    public void setRole(Role role) {
        getUserRoleId().setRole(role);
    }
}
