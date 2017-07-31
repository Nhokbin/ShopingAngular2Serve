package com.dav.shopping.entity;

<<<<<<< HEAD
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
=======
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "actions")
<<<<<<< HEAD
public class Action {
=======
public class Action implements Serializable{
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
<<<<<<< HEAD
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "permission", joinColumns = @JoinColumn(name = "action_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private Set<Role> roles;
=======

	@OneToMany(mappedBy = "primaryKey.action", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Permission> permissions;
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92

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

<<<<<<< HEAD
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
=======
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null || obj.getClass() != getClass()) {
		    result = false;
		} else {
			Action action = (Action) obj;
		    if (this.name.equals(action.getName())) {
		        result = true;
		    }
		}
		return result;
	}
<<<<<<< HEAD
=======

	public void addPermission(Permission permission){
		this.permissions.add(permission);
	}
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
}
