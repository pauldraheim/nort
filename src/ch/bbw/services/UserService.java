package ch.bbw.services;

import java.util.List;
import java.util.Map;

import ch.bbw.model.User;

/**
 * Interface for different operations connected to data sources
 * @author 5ia16padraheim
 */
public interface UserService {
	
	/**
	 * Returns all objects of a kind from the data source
	 * @return A list of all objects contained in the data source
	 */
	public List<Object> getAllFromDataSource();
	
	/**
	 * Interacts with the database and executes the login process
	 * @param username The username that was entered
	 * @param password The password that was entered
	 * @return The user that logged in, null if no user found
	 */
	public User login(String username, String password);
	
	/**
	 * Returns the leaderboards, containing the three best players but also player one and player two regardless of placement
	 * @return List of users ordered as a leaderboard
	 */
	public Map<Integer, User> getLeaderboards();
	
	/**
	 * Adds an object of a kind to the list of said objects in the data source
	 * @param objectToAdd The object that is to be added to the data source
	 */
	public void addToDataSource(Object objectToAdd);

	/**
	 * Updates the datasource, possible changes include round wins and game wins
	 */
	public void updateDatasource();
}
