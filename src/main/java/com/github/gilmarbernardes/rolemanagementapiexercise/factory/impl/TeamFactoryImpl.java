package com.github.gilmarbernardes.rolemanagementapiexercise.factory.impl;

import com.github.gilmarbernardes.rolemanagementapiexercise.factory.TeamFactory;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.Team;

/**
 * Factory class to Team entity
 *
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */
public class TeamFactoryImpl implements TeamFactory {

	@Override
	public Team createTeam() {
		return new Team();
	}

}
