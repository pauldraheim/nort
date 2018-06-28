package ch.bbw.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import ch.bbw.controller.interfaces.ComponentSetterGetter;
import ch.bbw.controller.interfaces.Service;
import ch.bbw.model.User;
import ch.bbw.view.SwingNavigator;
import ch.bbw.view.enums.NortWindow;

/**
 * Handles all sorts of user input
 * @author 5ia16padraheim
 */
public class UserInputHandler {
	
	/**
	 * Handles what to do when the user clicks on a quit button
	 */
	public void handleQuit() {
		System.exit(0);
	}
	
	/**
	 * Handles what to do when the user clicks on the login button
	 */
	public void handleLogin() {
		Service userService = new MySQLUserService();
		
		ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();
		
		String password = valueSetterGetter.getLoginPassword();
		String username = valueSetterGetter.getLoginUsername();

		String hashedEnteredPassword = hashPassword(password);
		
		boolean hasMatchingUser = false;
		
		for (Object userObject : userService.getAllFromDataSource()){
			User user = (User) userObject;
			
			if (user.getPassword().equals(hashedEnteredPassword) && 
					user.getUsername().equals(username)) {
				hasMatchingUser = true;
			}
		}
		
		if(!hasMatchingUser) {
			valueSetterGetter.setLoginInfoText("Incorrect combination of username and password");
		} else {
			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
	}
	
	/**
	 * Handles what to do when the user clicks on the register button
	 */
	public void handleRegister() {
		Service userService = new MySQLUserService();
		
		ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();
		
		String password = valueSetterGetter.getRegisterPassword();
		String username = valueSetterGetter.getRegisterUsername();
		
		boolean isUsernameTaken = false;
		
		for (Object userObject : userService.getAllFromDataSource()) {
			User user = (User) userObject;
			
			if (user.getUsername().equals(username)) {
				isUsernameTaken = true;
			}
		}
		
		if (!isUsernameTaken) {
			userService.addToDataSource(new User(username, hashPassword(password)));
			
			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
		else {
			valueSetterGetter.setRegisterInfoText("Username already taken!");
		}
	}
	
	/**
	 * Handles what to do when the user clicks on a button that should direct towards the login screen
	 */
	public void handleGoToLogin() {
		SwingNavigator.getInstance().navigate(NortWindow.LOGIN);
	}
	
	/**
	 * Handles what to do when the user clicks on a button that should direct towards the register screen
	 */
	public void handleGoToRegister() {
		SwingNavigator.getInstance().navigate(NortWindow.REGISTER);
	}
	
	/**
	 * Handles what to do when the user clicks on a button that should direct towards the player two screen
	 */
	public void handleGoToPlayerTwo() {
		SwingNavigator.getInstance().navigate(NortWindow.PLAYERTWO);
	}

	public void handleGoToMainmenu() {
		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}
	
	/**
	 * Hashes an entered password
	 * @param enteredPassword The password that was entered by the user
	 * @return The hashed password
	 */
	private String hashPassword(String enteredPassword) {
		String hashedEnteredPassword = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(enteredPassword.getBytes());

		    byte[] digest = md.digest();
		    
		    hashedEnteredPassword = DatatypeConverter
		      .printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	    return hashedEnteredPassword;
	}
}
