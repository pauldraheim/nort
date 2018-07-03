package ch.bbw.services.impl;

import ch.bbw.services.NortServiceProvider;
import ch.bbw.services.UserService;

public class MySQLNortServiceProvider implements NortServiceProvider {
	
	private static MySQLNortServiceProvider mySqlNortServiceProvider;
	
	private MySQLNortServiceProvider() {}
	
	@Override
	public UserService getUserService() {
		return new MySQLUserService();
	}
	
	public static MySQLNortServiceProvider getInstance() {
		if (mySqlNortServiceProvider == null) {
			mySqlNortServiceProvider = new MySQLNortServiceProvider();
		}
		
		return mySqlNortServiceProvider;
	}
}
