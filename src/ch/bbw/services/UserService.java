package ch.bbw.services;

import java.util.List;

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
	
	public User login(String username, String password);
	
	/**
	 * Adds an object of a kind to the list of said objects in the data source
	 * @param objectToAdd The object that is to be added to the data source
	 */
	public void addToDataSource(Object objectToAdd);
}
