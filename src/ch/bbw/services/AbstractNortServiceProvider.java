package ch.bbw.services;

import ch.bbw.services.impl.MySQLNortServiceProvider;

/**
 * Able to return the active instance of the NortServiceProvider class
 * @author 5ia16padraheim
 */
public abstract class AbstractNortServiceProvider {
	
	private static NortServiceProvider serviceProvider;
	
	private AbstractNortServiceProvider() {}
	
	public static NortServiceProvider getInstance() {
		if (serviceProvider == null){
			serviceProvider = MySQLNortServiceProvider.getInstance();
		}
		
		return serviceProvider;
	}
}
