package se.kth.iv1201.recruitmentbackend.enums;

public enum JwtEnums  {
	AUTH("Authorization"),
	BEARER("Bearer ");
	
	private String header;
	JwtEnums(String header)
	{
		this.header = header;
	}
	public String getHeader() {
		return this.header;
	}
	
}
