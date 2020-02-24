package se.kth.iv1201.recruitmentbackend.enums;

public enum ApplicationStatus {
	ACCEPTED("accepted"),
	REJECTED("rejected"),
	UNHANDLED("unhandled");
	
	private String status;
	
	ApplicationStatus(String statusName){
		this.status =statusName;
	}
	public String getStatus() {
		return this.status;
	}
}
