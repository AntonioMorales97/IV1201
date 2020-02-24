package se.kth.iv1201.recruitmentbackend.enums;

public enum MigrationEnums {
	PERSON_ID("person_id"),
	PERSON_NAME("name"),
	PERSON_SURNAME("surname"),
	PERSON_SSN("ssn"),
	PERSON_EMAIL("email"),
	PERSON_PASS("password"),
	PERSON_USER("username"),
	PERSON_ROLE("role"),
	DATE_FROM("from_date"),
	DATE_TO("to_date"),
	COMPETENCE("competence"),
	COMPETENCE_NAME("name"),
	ROLE_NAME("name"),
	YEARS_EXPERIANCE("years_of_experience");
	
	private String value;
	
	MigrationEnums(String value){
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}

}
