package ch.bbw.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ch.bbw.model.User;

/**
 * Executes queries with the DatabaseConnection created in the Starter class
 * @author 5ia16padraheim
 */
public class QueryExecuter {
	
	/**
	 * Returns all users from the Database
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		
		ResultSet rs = null;
		
        try {
            String query = "select * from user";
            
            rs = Starter.getDatabaseConnector().getSt().executeQuery(query);
            
            while (rs.next()) {
                User user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"));
                users.add(user);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
        	try {
	        	if (rs != null) {
					rs.close();
	        	}
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        return users;
	}
	
	/**
	 * Adds a user to the user table
	 * @param username The username of the new user
	 * @param password The hashed password of the new User
	 */
	public void addUser(String username, String password) {
		PreparedStatement st = null;
		
		try {
            String query = "insert into user (username, password) values (?, ?)";
            
            st = Starter.getDatabaseConnector().getCon().prepareStatement(query);
            
            st.setString(1, username);
            st.setString(2, password);
            
            st.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
		finally {
			try {
				if (st != null) {
					st.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
