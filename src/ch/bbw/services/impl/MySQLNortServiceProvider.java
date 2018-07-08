package ch.bbw.services.impl;

import ch.bbw.services.NortServiceProvider;
import ch.bbw.services.UserService;

/**
 * The NortServiceProvider suited for MySQL operations
 * @author 5ia16padraheim
 */
public class MySQLNortServiceProvider implements NortServiceProvider {
	
	private static MySQLNortServiceProvider mySqlNortServiceProvider;
	
	private MySQLNortServiceProvider() {}
	
	@Override
	public UserService getUserService() {
		return new MySQLUserService();
	}
	
	/**
	 * Returns the only instance of this class and initiates it if it hasn't been yet
	 * @return The only instance of this class
	 */
	public static MySQLNortServiceProvider getInstance() {
		if (mySqlNortServiceProvider == null) {
			mySqlNortServiceProvider = new MySQLNortServiceProvider();
		}
		
		return mySqlNortServiceProvider;
	}
}
