package com.github.gilmarbernardes.rolemanagementapiexercise.ut;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.github.gilmarbernardes.rolemanagementapiexercise.enumeration.RoleEnum;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.Role;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.Team;
import com.github.gilmarbernardes.rolemanagementapiexercise.model.User;
import com.github.gilmarbernardes.rolemanagementapiexercise.service.RoleService;
import com.github.gilmarbernardes.rolemanagementapiexercise.service.TeamService;
import com.github.gilmarbernardes.rolemanagementapiexercise.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class RoleManagementApiExerciseUnitTests {

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private RoleService roleService;

	@BeforeAll
	public void setUp() {
		// User
		userService.createFactory();
		userService.createUserList();

		// Team
		teamService.createFactory();
		teamService.createTeamList();

		// Role
		roleService.createFactory();
		roleService.createRoleList();
	}

	@Test
	@Order(1)
	public void shouldReturnNotNullUserService() {
		assertNotNull(userService);
	}

	@Test
	@Order(2)
	public void shouldReturnNotNullTeamService() {
		assertNotNull(teamService);
	}

	@Test
	@Order(3)
	public void shouldReturnNotNullRoleService() {
		assertNotNull(roleService);
	}

	@Test
	@Order(4)
	@SuppressWarnings("unchecked")
	public void shouldReturnUserCreatedWithSuccess() throws Exception {
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id", "as123456-d1ww-1564-q3w8-z1s2d3f1w5e2");
		jsonUser.put("firstName", "Kobe");
		jsonUser.put("lastName", "Bryant");
		jsonUser.put("displayName", "KobeBryant");
		jsonUser.put("avatarUrl", "www.google.com");
		jsonUser.put("location", "Los Angeles");
		jsonUser.put("role", "Developer");

		User user = userService.create(jsonUser);

		assertNotNull(user);
		assertEquals(user.getId(), jsonUser.get("id"));
		assertEquals(user.getFirstName(), jsonUser.get("firstName"));
		assertEquals(user.getLastName(), jsonUser.get("lastName"));
		assertEquals(user.getDisplayName(), jsonUser.get("displayName"));
		assertEquals(user.getAvatarUrl(), jsonUser.get("avatarUrl"));
		assertEquals(user.getLocation(), jsonUser.get("location"));
		assertEquals(user.getRole(), jsonUser.get("role"));

	}

	@Test
	@Order(5)
	@SuppressWarnings("unchecked")
	public void shouldReturnTeamCreatedWithSuccess() throws Exception {
		JSONObject jsonTeam = new JSONObject();
		jsonTeam.put("id", "7676a4bf-adfe-415c-941b-1739af070123");
		jsonTeam.put("name", "TeamLakers");
		jsonTeam.put("teamLeadId", "as123456-d1ww-1564-q3w8-z1s2d3f1w5e2");

		Team team = teamService.create(jsonTeam);

		assertNotNull(team);
		assertEquals(team.getId(), jsonTeam.get("id"));
		assertEquals(team.getName(), jsonTeam.get("name"));
		assertEquals(team.getTeamLeadId(), jsonTeam.get("teamLeadId"));
	}

	@Test
	@Order(6)
	@SuppressWarnings("unchecked")
	public void shouldReturnRoleCreatedWithSuccess() throws Exception {
		JSONObject jsonRole = new JSONObject();
		jsonRole.put("id", "a1");
		jsonRole.put("name", RoleEnum.PO.getValue());

		Role role = roleService.create(jsonRole);

		assertNotNull(role);
		assertEquals(role.getId(), jsonRole.get("id"));
		assertEquals(role.getName(), jsonRole.get("name"));
	}


	@AfterAll
	public void tearDown() {
		teamService.clearObjects();
		userService.clearObjects();
		roleService.clearObjects();

	}
}
