package com.dav.shopping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "functions")
public class Function implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Integer displayOrder;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean status;

    private String url;
    private String iconCss;

    @Transient
    private long _parentId;

    @OneToMany(mappedBy = "primaryKey.function", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Permission> permissions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @JsonBackReference
    private Function parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Function> functions;

    public void addPermission(Permission permission){
        this.permissions.add(permission);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCss() {
        return iconCss;
    }

    public void setIconCss(String iconCss) {
        this.iconCss = iconCss;
    }

    public long get_parentId() {
        return _parentId;
    }

    public void set_parentId(long _parentId) {
        this._parentId = _parentId;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Function getParentId() {
        return parentId;
    }

    public void setParentId(Function parentId) {
        this.parentId = parentId;
    }

    public Set<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(Set<Function> functions) {
        this.functions = functions;
    }
}
