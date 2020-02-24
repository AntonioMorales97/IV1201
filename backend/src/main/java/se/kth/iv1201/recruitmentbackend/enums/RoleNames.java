package se.kth.iv1201.recruitmentbackend.enums;

public enum RoleNames {
	RECRUIT("recruit"),
	APPLICANT("applicant");
	
	private String name;
	
	RoleNames(String roleName){
		this.name =roleName;
	}
	public String getRole() {
		return this.name;
	}
}
