package com.github.gilmarbernardes.rolemanagementapiexercise.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.gilmarbernardes.rolemanagementapiexercise.model.User;
import com.github.gilmarbernardes.rolemanagementapiexercise.service.UserService;
import com.github.gilmarbernardes.rolemanagementapiexercise.utils.JsonUtils;

/**
 * SpringBoot RestController that creates all service end-points related to
 * Users.
 *
 * @author Gilmar Alves Bernardes Junior
 * @since 06/13/2021
 */

@RestController
@RequestMapping("/api-roles/users")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;


	/**
	 * Method that list all users
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @return ResponseEntity with a <code>List<User></code> object and the HTTP status
	 *
	 * HTTP Status:
	 *
	 * 200 - OK: Everything worked as expected.
	 * 404 - Not Found: The requested resource doesn't exist.
	 *
	 */
	@GetMapping
	public ResponseEntity<List<User>> find() {
		if (userService.find().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		logger.info(userService.find());

		return ResponseEntity.ok(userService.find());
	}

	/**
	 * Method that deletes all existing users.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @return Returns an empty body with one of the following:
	 *
	 *         204 - if delete with success 205 - if hasn't delete with success. 500
	 *         - Server Errors: something went wrong on API end (These are rare).
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> delete() {
		try {
			userService.deleteAllUsers();
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Method that creates a User.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param User, where:
	 *
	 *        id - User id;
	 *        firstName - Users first name;
	 *        lastName – Users last name;
	 *        displayName – Users name that will be shown;
	 *        avatarUrl – URL where users image was saved;
	 *        location - Users location;
	 *        role - Users role. If empty, will be set with a default role = Developer;
	 *
	 * @return ResponseEntity with a <code>User</code> object and the HTTP status
	 *
	 *         HTTP Status:
	 *
	 *         201 - Created: Everything worked as expected.
	 *         400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
	 *         500 - Server Errors: something went wrong on API end (These are rare).
	 *
	 */
	@PostMapping
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody JSONObject user) {
		try {
			if (JsonUtils.isJSONValid(user.toString())) {
				User userCreated = userService.create(user);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(userCreated.getId()).build().toUri();

				userService.add(userCreated);
				return ResponseEntity.created(uri).body(null);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			logger.error("The request was unacceptable, be sure that is not missing a required parameter. " + e);
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	/**
	 * Method that updates an User.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param User, where:
	 *
	 *        	id - User id;
	 *        	firstName - Users first name;
	 *        	lastName – Users last name;
	 *        	displayName – Users name that will be shown;
	 *        	avatarUrl – URL where users image was saved;
	 *        	location - Users location;
	 *        	role - Users role. If empty, will be set with a default role = Developer;
	 *
	 * @return ResponseEntity with a <code>User</code> object and the HTTP status
	 *
	 *      	HTTP Status:
	 *
	 * 			200 – OK: Everything worked as expected.
	 * 			400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
	 * 			404 - Not Found: The requested resource doesn't exist.
	 * 			500 - Server Errors: something went wrong on API end (These are rare).
	 *
	 */
	@PutMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody JSONObject user) {
		try {
			if (JsonUtils.isJSONValid(user.toString())) {
				User userToUpdate = userService.findByID(id);
				if (userToUpdate == null) {
					logger.error("User not found.");
					return ResponseEntity.notFound().build();
				} else {
					User userUpdated = userService.update(userToUpdate, user);
					return ResponseEntity.ok(userUpdated);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			logger.error("The request was unacceptable, be sure that is not missing a required parameter. " + e);
			return ResponseEntity.badRequest().body(null);
		}
	}
}
