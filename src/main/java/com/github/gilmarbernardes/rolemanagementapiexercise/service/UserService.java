package com.github.gilmarbernardes.rolemanagementapiexercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.github.gilmarbernardes.rolemanagementapiexercise.enumeration.RoleEnum;
import com.github.gilmarbernardes.rolemanagementapiexercise.factory.UserFactory;
import com.github.gilmarbernardes.rolemanagementapiexercise.factory.impl.UserFactoryImpl;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.User;

@Service
public class UserService {

	private List<User> users;
	private UserFactory factory;

	/**
	 * Method to create an user factory.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 */
	public void createFactory() {
		if (factory == null) {
			factory = new UserFactoryImpl();
		}
	}

	/**
	 * Method to create an user list.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 */
	public void createUserList() {
		if (users == null) {
			users = new ArrayList<User>();
		}
	}


	/**
	 * Method to fullfil an User object
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonUser
	 * @param user
	 */
	public void setUserValues(JSONObject jsonUser, User user) {
		user.setId(jsonUser.get("id") != null ? (String) jsonUser.get("id") : user.getId());
		user.setFirstName(jsonUser.get("firstName") != null ? (String) jsonUser.get("firstName") : user.getFirstName());
		user.setLastName(jsonUser.get("lastName") != null ? (String) jsonUser.get("lastName") : user.getLastName());
		user.setDisplayName(jsonUser.get("displayName") != null ? (String) jsonUser.get("displayName") : user.getDisplayName());
		user.setAvatarUrl(jsonUser.get("avatarUrl") != null ? (String) jsonUser.get("avatarUrl") : user.getAvatarUrl());
		user.setLocation(jsonUser.get("location") != null ? (String) jsonUser.get("location") : user.getLocation());

		user.setRole(jsonUser.get("role") != null ? (String) jsonUser.get("role") : RoleEnum.DEV.getValue());
	}

	/**
	 * Method to create an User
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonUser
	 * @return User
	 */
	public User create(JSONObject jsonUser) {
		createFactory();

		User user = factory.createUser(RoleEnum.DEV.getValue());
		setUserValues(jsonUser, user);

		return user;
	}

	/**
	 * Method to update an User
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonUser
	 * @return User
	 */
	public User update(User user, JSONObject jsonUser) {
		setUserValues(jsonUser, user);

		return user;
	}

	/**
	 * Method to add an User to an users list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param user
	 */
	public void add(User user) {
		createUserList();
		users.add(user);
	}

	/**
	 * Method that get all users
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @return List
	 */
	public List<User> find() {
		createUserList();
		return users;
	}

	/**
	 * Method to find an User in users list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param id
	 */
	public User findByID(String id) {
		return users.stream().filter(u -> id.equalsIgnoreCase(u.getId())).collect(Collectors.toList()).get(0);
	}

	/**
	 * Method to clear an users list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 */
	public void deleteAllUsers() {
		users.clear();
	}

	/**
	 * Method to clear the objects that I used in this class (users and factory)
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 */
	public void clearObjects() {
		users = null;
		factory = null;
	}
}

