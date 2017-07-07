package com.dav.shopping.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;

    private String description;

    @OneToMany(mappedBy = "userRoleId.role", cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<UserRole> userRoles;

    @OneToMany(mappedBy = "primaryKey.role", cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Permission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
