package com.github.gilmarbernardes.rolemanagementapiexercise.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

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
}
