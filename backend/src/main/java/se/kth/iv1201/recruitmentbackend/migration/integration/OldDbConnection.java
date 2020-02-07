package se.kth.iv1201.recruitmentbackend.migration.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;



public class OldDbConnection {
	private final String dbUrl = System.getenv("HEROKU_POSTGRESQL_CRIMSON_JDBC_URL"); //"jdbc:postgresql://localhost/old_recruitment?user=recruitment_acc&password=123123";
	private static final String SQL_DRIVER = "org.postgresql.Driver";
	private Connection conn;
	private static final Logger logger = LoggerFactory.getLogger(OldDbConnection.class);
	
	public OldDbConnection() throws SQLException {
		try {
			Class.forName(SQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		this.conn = DriverManager.getConnection(dbUrl);
		this.conn.setAutoCommit(false);
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public void disconnect() throws SQLException {
		this.conn.close();
	}
}
