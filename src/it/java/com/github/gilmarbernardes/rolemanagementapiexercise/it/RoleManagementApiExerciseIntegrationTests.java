package com.github.gilmarbernardes.rolemanagementapiexercise.it;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class RoleManagementApiExerciseIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void contextLoad() {
		assertNotNull(mockMvc);
	}

	@Test
	@Order(2)
	public void shouldReturnCreateUser() throws Exception {

		JSONObject mapToCreate = setObjectToCreate();
		this.mockMvc
				.perform(post("/api-roles/users")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper()
				.writeValueAsString(mapToCreate)))
				.andExpect(status()
				.isCreated());
	}

	@Test
	@Order(3)
	public void shouldReturnUpdateUser() throws Exception {

		JSONObject mapToUpdate = setObjectToUpdate();
		this.mockMvc
				.perform(put("/api-roles/users/as123456-d1ww-1564-q3w8-z1s2d3f1w5e2")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(new ObjectMapper()
				.writeValueAsString(mapToUpdate)))
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void shouldReturnGetAllUser() throws Exception {
		this.mockMvc
				.perform(get("/api-roles/users"))
				.andExpect(status().isOk());
    }

	@Test
	@Order(5)
	public void shouldReturnRemoveAllUser() throws Exception {
		this.mockMvc
				.perform(delete("/api-roles/users"))
				.andExpect(status().isNoContent());
    }

	@SuppressWarnings("unchecked")
	private JSONObject setObjectToCreate() {

		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id", "as123456-d1ww-1564-q3w8-z1s2d3f1w5e2");
		jsonUser.put("firstName", "Kobe");
		jsonUser.put("lastName", "Bryant");
		jsonUser.put("displayName", "KobeBryant");
		jsonUser.put("avatarUrl", "www.google.com");
		jsonUser.put("location", "Los Angeles");
		jsonUser.put("role", "Developer");

		return jsonUser;
	}

	@SuppressWarnings("unchecked")
	private JSONObject setObjectToUpdate() {

		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id", "as123456-d1ww-1564-q3w8-z1s2d3f1w5e2");
		jsonUser.put("role", "Tester");

		return jsonUser;
	}
}
