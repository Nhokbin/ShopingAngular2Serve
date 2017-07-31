package com.dav.shopping.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Duong Vu on 01/07/2017.
 */
@Entity
@Table(name = "permission")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.function",
                joinColumns = @JoinColumn(name = "functionId")),
        @AssociationOverride(name = "primaryKey.action",
                joinColumns = @JoinColumn(name = "actionId")),
        @AssociationOverride(name = "primaryKey.role",
                joinColumns = @JoinColumn(name = "roleId")) })
public class Permission implements Serializable{

    @EmbeddedId
    private PermissionId primaryKey = new PermissionId();

    public PermissionId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PermissionId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Function getFunction() {
        return getPrimaryKey().getFunction();
    }

    public void setFunction(Function function) {
        getPrimaryKey().setFunction(function);
    }

    @Transient
    public Role getRole() {
        return getPrimaryKey().getRole();
    }

    public void setRole(Role role) {
        getPrimaryKey().setRole(role);
    }

    @Transient
    public Action getAction() {
        return getPrimaryKey().getAction();
    }

    public void setAction(Action action) {
        getPrimaryKey().setAction(action);
    }
}
