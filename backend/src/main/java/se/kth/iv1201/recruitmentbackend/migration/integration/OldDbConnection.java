package se.kth.iv1201.recruitmentbackend.migration.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Connection class for the old database used during migration to the new database.
 */
public class OldDbConnection {
	private static final String SQL_DRIVER = "org.postgresql.Driver";
	private Connection conn;
	private static final Logger logger = LoggerFactory.getLogger(OldDbConnection.class);

	/**
	 * Creates a new connection to the old database
	 * @throws SQLException if connection to the old database can not be established
	 */
	public OldDbConnection() throws SQLException {
		try {
			Class.forName(SQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		String oldDbKey = System.getenv("OLD_DB");
		String dbUrl = System.getenv(oldDbKey); //"jdbc:postgresql://localhost/old_recruitment?user=recruitment_acc&password=123123";
		this.conn = DriverManager.getConnection(dbUrl);
		this.conn.setAutoCommit(false);
	}

	/**
	 * Get the connection object to the old database
	 * @return jdbc Connection object to the old database
	 */
	public Connection getConnection() {
		return this.conn;
	}

	/**
	 * Close the connection to the old database
	 * @throws SQLException if the connection can not be closed.
	 */
	public void disconnect() throws SQLException {
		this.conn.close();
	}
}
