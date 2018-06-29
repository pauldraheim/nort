package ch.bbw.services.impl;

import ch.bbw.services.NortServiceProvider;
import ch.bbw.services.UserService;

public class MySQLNortServiceProvider implements NortServiceProvider {
	//nur eine instanz
	
	@Override
	public UserService getUserService() {
		return new MySQLUserService();
	}
}
