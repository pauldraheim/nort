package ch.bbw.services;

import ch.bbw.services.impl.MySQLNortServiceProvider;

public abstract class AbstractNortServiceProvider {
	
	private static NortServiceProvider serviceProvider;
	
	public static NortServiceProvider getInstance() {
		if (serviceProvider == null){
			serviceProvider = MySQLNortServiceProvider.getInstance();
		}
		
		return serviceProvider;
	}
}
