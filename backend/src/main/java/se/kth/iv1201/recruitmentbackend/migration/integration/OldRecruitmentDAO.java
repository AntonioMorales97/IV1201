package se.kth.iv1201.recruitmentbackend.migration.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.kth.iv1201.recruitmentbackend.migration.dto.AvailabilityDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.CompetenceDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.CompetenceProfileDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.RoleDTO;

public class OldRecruitmentDAO {
	private OldDbConnection oldDbConn;
	private Connection conn;
	
	public OldRecruitmentDAO() {
		try {
			this.oldDbConn = new OldDbConnection();
			this.conn = this.oldDbConn.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			//Log?
		}
	}
	
	public void closeConnection() {
		try {
			this.oldDbConn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
			//Log?
		}
	}
	
	public List<PersonDTO> getPersons(){
		String query = "SELECT person.person_id, person.name, person.surname, person.ssn, person.email, person.password, person.username, (SELECT name FROM role WHERE person.role_id = role.role_id) AS role FROM person";
		Statement stmt;
		List<PersonDTO> personDTOs = new ArrayList<>();
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Long id = rs.getLong("person_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String ssn = rs.getString("ssn");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String role = rs.getString("role");
				personDTOs.add(new PersonDTO(id, name, surname, ssn, email, password, username, role));
			}
		} catch (SQLException e) {
			//Log?
			e.printStackTrace();
		}
		return personDTOs;
	}
	
	public List<AvailabilityDTO> getAvailabilities(Long personID){
		String query = "SELECT availability.from_date, availability.to_date FROM availability WHERE availability.person_id = ?";
		PreparedStatement prepStmt;
		List<AvailabilityDTO> availabilitiesDTOs = new ArrayList<>();
		try {
			prepStmt = this.conn.prepareStatement(query);
			prepStmt.setLong(1, personID);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				Date from = rs.getDate("from_date");
				Date to = rs.getDate("to_date");
				availabilitiesDTOs.add(new AvailabilityDTO(from, to));
			}
		} catch (SQLException e) {
			//Log?
			e.printStackTrace();
		}
		return availabilitiesDTOs;
	}
	
	public List<CompetenceProfileDTO> getCompetenceProfiles(Long personID){
		String query = "SELECT competence_profile.years_of_experience, (SELECT name FROM competence WHERE competence_profile.competence_id = competence.competence_id) AS competence FROM competence_profile WHERE competence_profile.person_id = ?";
		PreparedStatement prepStmt;
		List<CompetenceProfileDTO> competenceProfileDTOs = new ArrayList<>();
		try {
			prepStmt = this.conn.prepareStatement(query);
			prepStmt.setLong(1, personID);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				competenceProfileDTOs.add(new CompetenceProfileDTO(rs.getString("competence"), rs.getDouble("years_of_experience")));
			}
		} catch (SQLException e) {
			//Log?
			e.printStackTrace();
		}
		return competenceProfileDTOs;
	}
	
	public List<CompetenceDTO> getCompetences(){
		String query = "SELECT name FROM competence";
		Statement stmt;
		List<CompetenceDTO> competenceDTOs = new ArrayList<>();
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString("name");
				competenceDTOs.add(new CompetenceDTO(name));
			}		
		} catch (SQLException e) {
			//Log?
			e.printStackTrace();
		}
		return competenceDTOs;
	}
	
	public List<RoleDTO> getRoles(){
		String query = "SELECT name FROM role";
		Statement stmt;
		List<RoleDTO> roleDTOs = new ArrayList<>();
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString("name");
				roleDTOs.add(new RoleDTO(name));			
			}
		} catch (SQLException e) {
			//Log?
			e.printStackTrace();
		}
		return roleDTOs;
	}

}
