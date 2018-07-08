package ch.bbw.controller;

import ch.bbw.controller.interfaces.ComponentInteractor;
import ch.bbw.view.NortComponentFactory;
import ch.bbw.view.NortFrame;
import ch.bbw.view.SwingNavigator;
import ch.bbw.view.interfaces.Navigator;

/**
 * Starts the program and contains all singletons of classes that should only be used once
 * @author 5ia16padraheim
 */
public class Starter {
	
	private static Starter starter;
	
	private UserInputHandler userInputHandler = UserInputHandler.getInstance();
	
	private ComponentInteractor compInteractor = SwingComponentInteractor.getInstance();
	
	private NortComponentFactory nortCompFactory = NortComponentFactory.getInstance();
	
	private NortFrame nortFrame = NortFrame.getInstance();
	
	private Navigator navigator = SwingNavigator.getInstance();
	
	private Starter() {}
	
	/**
	 * Returns the only instance of the Starter class and initiates it if it hasn't been yet
	 * @return The only instance of the Starter class
	 */
	public static Starter getInstance() {
		if (starter == null) {
			starter = new Starter();
		}
		
		return starter;
	}
	
	public UserInputHandler getUserInputHandler() {
		return userInputHandler;
	}
	
	public ComponentInteractor getComponentInteractor() {
		return compInteractor;
	}
	
	public NortComponentFactory getNortComponentFactory() {
		return nortCompFactory;
	}
	
	public NortFrame getNortFrame() {
		return nortFrame;
	}
	
	public Navigator getNavigator() {
		return navigator;
	}
}
