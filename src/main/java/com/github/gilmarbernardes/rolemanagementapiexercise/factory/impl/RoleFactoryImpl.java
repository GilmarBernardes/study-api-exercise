package com.github.gilmarbernardes.rolemanagementapiexercise.factory.impl;

import com.github.gilmarbernardes.rolemanagementapiexercise.factory.RoleFactory;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.Role;

/**
 * Factory class to Role entity
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */
public class RoleFactoryImpl implements RoleFactory {

	@Override
	public Role createRole() {
		return new Role();
	}

}
