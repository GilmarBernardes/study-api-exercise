package com.github.gilmarbernardes.rolemanagementapiexercise.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that implements the Team structure.
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

	private String id;
	private String name;
	private String teamLeadId;
	private String[] teamMemberIds;

	private User teamLead;
	private List<User> teamMember;
}
