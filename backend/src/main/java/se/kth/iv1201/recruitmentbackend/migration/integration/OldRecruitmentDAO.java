package se.kth.iv1201.recruitmentbackend.migration.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.kth.iv1201.recruitmentbackend.migration.dto.AvailabilityDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.CompetenceDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.CompetenceProfileDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.RoleDTO;

/**
 * Data access object class used to retrieve data from the old database.
 */
public class OldRecruitmentDAO {
	private OldDbConnection oldDbConn;
	private Connection conn;
	private static final Logger logger = LoggerFactory.getLogger(OldDbConnection.class);

	/**
	 * Creates a connection to the old database and returns a DAO.
	 */
	public OldRecruitmentDAO() throws NullPointerException, SQLException {
//		try {
			this.oldDbConn = new OldDbConnection();
			this.conn = this.oldDbConn.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
	}

	/**
	 * Closes the DAO and the connection to the old database.
	 */
	public void closeConnection() {
		try {
			this.oldDbConn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of person entries from the old database in the form od a personDTO list.
	 * @return a list of personDTO, Note: these are personDTO objects used for the old database.
	 */
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
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return personDTOs;
	}

	/**
	 * Retrieves a list of availabilities for a given person.
	 * @param personID is the ID of a person in the old database
	 * @return a list of AvailabilityDTO representing the availabilities of a person in the old database.
	 */
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
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return availabilitiesDTOs;
	}

	/**
	 * Retrieves a list of competence profiles associated with a given person
	 * @param personID is the ID of the person in the old database
	 * @return a list of CompetenceProfileDTO.
	 */
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
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return competenceProfileDTOs;
	}

	/**
	 * Retrieves a list of all competences in the old database
	 * @return competences in the form of a CompetenceDTO list.
	 */
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
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return competenceDTOs;
	}

	/**
	 * Retrieves a list of all the roles in the old database.
	 * @return roles in the form of a RoleDTO list.
	 */
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
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return roleDTOs;
	}

}
