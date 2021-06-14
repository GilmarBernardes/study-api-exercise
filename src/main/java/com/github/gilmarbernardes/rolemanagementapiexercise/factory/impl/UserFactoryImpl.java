package com.github.gilmarbernardes.rolemanagementapiexercise.factory.impl;

import com.github.gilmarbernardes.rolemanagementapiexercise.enumeration.RoleEnum;
import com.github.gilmarbernardes.rolemanagementapiexercise.factory.UserFactory;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.User;

/**
 * Factory class to User entity
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */
public class UserFactoryImpl implements UserFactory {

	@Override
	public User createUser(String role) {
		return new User(RoleEnum.DEV.getValue());
	}
}
