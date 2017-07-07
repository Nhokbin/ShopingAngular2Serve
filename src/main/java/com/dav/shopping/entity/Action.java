package com.dav.shopping.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "actions")
public class Action implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;

	@OneToMany(mappedBy = "primaryKey.action", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Permission> permissions;

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

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

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

	public void addPermission(Permission permission){
		this.permissions.add(permission);
	}
}
