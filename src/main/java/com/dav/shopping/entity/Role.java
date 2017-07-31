package com.dav.shopping.entity;

<<<<<<< HEAD
import java.util.List;
=======
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
<<<<<<< HEAD
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="role")
	private String role;

	private String description;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "roles", targetEntity = User.class)
	@JsonIgnore
	private List<User> users;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL},fetch = FetchType.EAGER, mappedBy = "roles", targetEntity = Action.class)
	@JsonIgnore
	private List<Action> actions;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL},fetch = FetchType.EAGER, mappedBy = "roles", targetEntity = Function.class)
	@JsonIgnore
	private List<Function> functions;

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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
=======
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
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
}
