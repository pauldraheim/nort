package ch.bbw.controller;

import ch.bbw.view.NortFrame;
import ch.bbw.view.SwingNavigator;
import ch.bbw.view.interfaces.Navigator;

/**
 * Starts the program and initializes what needs to be initialized
 * @author 5ia16padraheim
 */
public class Starter {

	/**
	 * NortFrame singleton since there will never be more than this instance running at the same time
	 */
	private static NortFrame nortFrame;
	
	/**
	 * DatabaseConnector singleton so the program won't have to create a connection all the time
	 */
	private static DatabaseConnector dbCon;
	
	/**
	 * Navigator singleton since there will only be one necessary to switch between all windows
	 */
	private static Navigator navigator;
	
	/**
	 * 
	 */
	public void startProgram() {
		dbCon = new DatabaseConnector();
		
		navigator = new SwingNavigator();
		
		nortFrame = new NortFrame();
		nortFrame.initGui();
	}

	public static NortFrame getFrame() {
		return nortFrame;
	}
	
	public static DatabaseConnector getDatabaseConnector() {
		return dbCon;
	}

	public static Navigator getNavigator() {
		return navigator;
	}
}
