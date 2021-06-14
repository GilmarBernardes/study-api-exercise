package com.github.gilmarbernardes.rolemanagementapiexercise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that implements the Role structure.
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	private String id;
	private String name;
}
