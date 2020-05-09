package br.org.neverbealone.model.enumeration;

public enum TypeUserEnum {

	ADMINISTRATOR("ADMINISTRATOR"),
	PSYCHOLOGIST("PSYCHOLOGIST"),
	PATIENT("PATIENT"),
	PSCYCHOLOGY_STUDENT("PSCYCHOLOGY_STUDENT"),
	VOLUNTARY("VOLUNTARY");
	
	private String type;
	
	private TypeUserEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
