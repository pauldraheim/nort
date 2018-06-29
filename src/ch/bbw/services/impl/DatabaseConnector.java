package ch.bbw.services.impl;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Link between Database and program, able to return all needed Database entries
 * @author 5ia16padraheim
 */
public class DatabaseConnector {
	
	private static final DatabaseConnector dbCon = new DatabaseConnector();
	
	private Statement st;
	
	private Connection con;
	
	private ResultSet rs;
	
	/**
	 * Standard constructor that also initializes Connection and Statement parameter
	 */
	private DatabaseConnector() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nort", "root", "");
	        st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DatabaseConnector getInstance() {
		return dbCon;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
