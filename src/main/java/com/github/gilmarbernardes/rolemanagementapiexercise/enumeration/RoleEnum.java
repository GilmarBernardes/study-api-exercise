package com.github.gilmarbernardes.rolemanagementapiexercise.enumeration;

/**
 * // * Enum to manage pre-defined roles Developer, Product Owner, Tester.
 * 
 * @author Gilmar Alves Bernardes Júnior
 * @since 06/13/2021
 */
public enum RoleEnum {

	DEV("Developer"), PO("Product Owner"), TESTER("Tester");

	private String value;

	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	/**
	 * Method that returns the value in the Enum
	 * 
	 * @param value
	 * @return r RoleTypeEnum
	 */
	public static RoleEnum getEnum(String value) {
		for (RoleEnum r : values()) {
			if (value.equals(r.getValue())) {
				return r;
			}
		}

		throw new RuntimeException("Type not found.");
	}
}
