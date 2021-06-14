package com.github.gilmarbernardes.rolemanagementapiexercise.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gilmarbernardes.rolemanagementapiexercise.factory.TeamFactory;
import com.github.gilmarbernardes.rolemanagementapiexercise.factory.impl.TeamFactoryImpl;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.Team;

@Service
public class TeamService {

	private List<Team> teams;
	private TeamFactory factory;

	/**
	 * Method to create a team factory.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 */
	public void createFactory() {
		if (factory == null) {
			factory = new TeamFactoryImpl();
		}
	}

	/**
	 * Method to create a team list.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 */
	public void createTeamList() {
		if (teams == null) {
			teams = new ArrayList<Team>();
		}
	}

	/**
	 * Method that check if JSON is valid.
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonInString
	 * @return boolean
	 */
	public boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Method to fullfil a Team object
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonTeam
	 * @param team
	 */
	public void setTeamValues(JSONObject jsonTeam, Team team) {
		team.setId(jsonTeam.get("id") != null ? (String) jsonTeam.get("id") : team.getId());
		team.setName(jsonTeam.get("name") != null ? (String) jsonTeam.get("name") : team.getName());
		team.setTeamLeadId(jsonTeam.get("teamLeadId") != null ? (String) jsonTeam.get("teamLeadId") : team.getTeamLeadId());

		String teamMemberStr = (String) jsonTeam.get("teamMemberIds");
		team.setTeamMemberIds(teamMemberStr.split(","));
	}

	/**
	 * Method to create a Team
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonTeam
	 * @return Team
	 */
	public Team create(JSONObject jsonTeam) {
		createFactory();

		Team team = factory.createTeam();
		setTeamValues(jsonTeam, team);

		return team;
	}

	/**
	 * Method to update a Team
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param jsonTeam
	 * @return Team
	 */
	public Team update(Team team, JSONObject jsonTeam) {
		setTeamValues(jsonTeam, team);

		return team;
	}

	/**
	 * Method to add a Team to a team list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param team
	 */
	public void add(Team team) {
		createTeamList();
		teams.add(team);
	}

	/**
	 * Method that get all teams
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @return List
	 */
	public List<Team> find() {
		createTeamList();
		return teams;
	}

	/**
	 * Method to find a team in team list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 * @param id
	 */
	public Team findByID(String id) {
		return teams.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}

	/**
	 * Method to clear a team list
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 */
	public void deleteAllTeams() {
		teams.clear();
	}

	/**
	 * Method to clear the objects that I used in this class (teams and factory)
	 *
	 * @author Gilmar Alves Bernardes Júnior
	 * @since 06/13/2021
	 *
	 */
	public void clearObjects() {
		teams = null;
		factory = null;
	}
}
