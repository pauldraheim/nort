package ch.bbw.services.impl;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ch.bbw.model.User;
import ch.bbw.services.UserService;

public class MySQLUserService implements UserService {

	@Override
	public List<Object> getAllFromDataSource() {
		List<Object> users = new ArrayList<>();
		
		ResultSet rs = null;
		
        try {
            String query = "select * from user";
            
            rs = DatabaseConnector.getInstance().getSt().executeQuery(query);
            
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

	@Override
	public void addToDataSource(Object objectToAdd) {
		PreparedStatement st = null;
		
		try {
			User user = (User) objectToAdd;
			
            String query = "insert into user (username, password) values (?, ?)";
            
            st = DatabaseConnector.getInstance().getCon().prepareStatement(query);
            
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            
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

	@Override
	public User login(String username, String password) {
		User user = null;
		
		ResultSet rs = null;
		
		PreparedStatement st = null;
		
        try {
            String query = "select * from user where username = ? and password = ?";
            
            st = DatabaseConnector.getInstance().getCon().prepareStatement(query);
            
            st.setString(1, username);
            st.setString(2, password);
            
            rs = st.executeQuery();
            
            while (rs.next()) {
            	user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"));
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
	        	if (st != null) {
	        		st.close();
	        	}
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        return user;
		
	}
}
