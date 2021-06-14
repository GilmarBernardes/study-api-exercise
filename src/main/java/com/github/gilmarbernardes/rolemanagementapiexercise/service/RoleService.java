package com.github.gilmarbernardes.rolemanagementapiexercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.github.gilmarbernardes.rolemanagementapiexercise.factory.RoleFactory;
import com.github.gilmarbernardes.rolemanagementapiexercise.factory.impl.RoleFactoryImpl;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.Role;

@Service
public class RoleService {

	private List<Role> roles;
	private RoleFactory factory;

	/**
	 * Method to create a role factory.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 */
	public void createFactory() {
		if (factory == null) {
			factory = new RoleFactoryImpl();
		}
	}

	/**
	 * Method to create a role list.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 */
	public void createRoleList() {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
	}

	/**
	 * Method to fullfil a role object
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonRole
	 * @param role
	 */
	public void setRoleValues(JSONObject jsonRole, Role role) {
		role.setId(jsonRole.get("id") != null ? (String) jsonRole.get("id") : role.getId());
		role.setName(jsonRole.get("name") != null ? (String) jsonRole.get("name") : role.getName());
	}

	/**
	 * Method to create a Role
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonRole
	 * @return Role
	 */
	public Role create(JSONObject jsonRole) {
		createFactory();

		Role role = factory.createRole();
		setRoleValues(jsonRole, role);

		return role;
	}

	/**
	 * Method to update a Role
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonRole
	 * @return Role
	 */
	public Role update(Role role, JSONObject jsonRole) {
		setRoleValues(jsonRole, role);

		return role;
	}

	/**
	 * Method to add a role to a role list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param Role
	 */
	public void add(Role role) {
		createRoleList();
		roles.add(role);
	}

	/**
	 * Method that get all roles
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @return List
	 */
	public List<Role> find() {
		createRoleList();
		return roles;
	}

	/**
	 * Method to find a role in roles list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param id
	 */
	public Role findByID(String id) {
		return roles.stream().filter(r -> id == r.getId()).collect(Collectors.toList()).get(0);
	}

	/**
	 * Method to clear a role list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 */
	public void deleteAllRoles() {
		roles.clear();
	}

	/**
	 * Method to clear the objects that I used in this class (roles and factory)
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 */
	public void clearObjects() {
		roles = null;
		factory = null;
	}
}
