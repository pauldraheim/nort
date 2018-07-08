package ch.bbw.services;


/**
 * Able to provide all services
 * @author 5ia16padraheim
 */
public interface NortServiceProvider {
	
	/**
	 * Returns the instance of the UserService that is active
	 * @return The active UserService instance
	 */
	public UserService getUserService();
}
