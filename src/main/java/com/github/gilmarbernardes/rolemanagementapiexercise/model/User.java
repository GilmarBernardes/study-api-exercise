package com.github.gilmarbernardes.rolemanagementapiexercise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that implements the User structure.
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String displayName;
	private String avatarUrl;
	private String location;

	private String role;

	public User(String role) {
		this.role = role;
	}
}
