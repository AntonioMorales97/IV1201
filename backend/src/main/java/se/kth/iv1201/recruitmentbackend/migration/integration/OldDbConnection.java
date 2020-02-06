package se.kth.iv1201.recruitmentbackend.migration.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OldDbConnection {
	private final String dbUrl = "jdbc:postgresql://localhost/old_recruitment?user=recruitment_acc&password=123123";//System.getenv("OLD_DATABASE_URL");
	private static final String SQL_DRIVER = "org.postgresql.Driver";
	private Connection conn;
	
	public OldDbConnection() throws SQLException {
		try {
			Class.forName(SQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//Log?
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
