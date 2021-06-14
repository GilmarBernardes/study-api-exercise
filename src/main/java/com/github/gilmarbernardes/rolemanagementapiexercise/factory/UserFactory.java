package com.github.gilmarbernardes.rolemanagementapiexercise.factory;

import com.github.gilmarbernardes.rolemanagementapiexercise.model.User;

/**
 * Interface that provides method to manipulate a User object
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */
public interface UserFactory {

	User createUser (String role);
}
