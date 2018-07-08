package ch.bbw.services;

import java.util.List;

import ch.bbw.model.LeaderboardPlacement;
import ch.bbw.model.User;

/**
 * Interface for different operations connected to the user data source
 * @author 5ia16padraheim
 */
public interface UserService {
	
	/**
	 * Returns all users from the data source
	 * @return A list of all users contained in the data source
	 */
	public List<User> getAllFromDataSource();
	
	/**
	 * Interacts with the database and executes the login process
	 * @param username The username that was entered
	 * @param hashedPassword The hashed version of the password that was entered
	 * @return The user that logged in, null if no user found
	 */
	public User login(String username, String hashedPassword);
	
	/**
	 * Interacts with the database and executes the register process
	 * @param username The username that was entered
	 * @param password The password that was entered
	 * @return The user that registered, null if the registration process was unsuccesful
	 */
	public User register(String username, String password);
	
	/**
	 * Returns the leaderboards, containing the three best players but also player one and player two regardless of placement
	 * @return List of LeaderboardPlacements ordered as a leaderboard
	 */
	public List<LeaderboardPlacement> getLeaderboards();
	
	/**
	 * Adds a user to the list of users in the data source
	 * @param user The user that is to be added to the data source
	 */
	public void addToDataSource(User user);

	/**
	 * Updates the datasource, possible changes include round wins and game wins
	 */
	public void updateDatasource();
}
