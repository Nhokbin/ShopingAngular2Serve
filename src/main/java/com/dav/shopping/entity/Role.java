package com.dav.shopping.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
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
}
