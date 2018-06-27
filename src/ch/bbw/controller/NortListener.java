package ch.bbw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import ch.bbw.controller.interfaces.ComponentSetterGetter;
import ch.bbw.model.User;
import ch.bbw.view.enums.NortWindow;

/**
 * Listener for all components, handles all actions
 * @author 5ia16padraheim
 */
public class NortListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("loginQuitBtn") || e.getActionCommand().equals("registerQuitBtn") || 
				e.getActionCommand().equals("mainMenuQuitBtn")) {
			System.exit(0);
		}
		else if (e.getActionCommand().equals("loginBtn")) {
			QueryExecuter queryExecuter = new QueryExecuter();
			ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();
			
			String password = valueSetterGetter.getLoginPassword();
			String username = valueSetterGetter.getLoginUsername();

			String hashedEnteredPassword = hashPassword(password);
			
			boolean hasMatchingUser = false;
			
			for (User user : queryExecuter.getUsers()) {
				if (user.getPassword().equals(hashedEnteredPassword) && 
						user.getUsername().equals(username)) {
					hasMatchingUser = true;
				}
			}
			
			if(!hasMatchingUser) {
				valueSetterGetter.setLoginInfoText("Incorrect combination of username and password");
			} else {
				Starter.getNavigator().navigate(NortWindow.MAINMENU);
			}
		}
		else if (e.getActionCommand().equals("registerBtn")) {
			QueryExecuter queryExecuter = new QueryExecuter();
			ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();
			
			String password = valueSetterGetter.getRegisterPassword();
			String username = valueSetterGetter.getRegisterUsername();
			
			boolean isUsernameTaken = false;
			
			for (User user : queryExecuter.getUsers()) {
				if (user.getUsername().equals(username)) {
					isUsernameTaken = true;
				}
			}
			
			if (!isUsernameTaken) {
				queryExecuter.addUser(username, hashPassword(password));
				
				Starter.getNavigator().navigate(NortWindow.MAINMENU);
			}
			else {
				valueSetterGetter.setRegisterInfoText("Username already taken!");
			}
		}
		else if (e.getActionCommand().equals("goToRegisterBtn")) {
			Starter.getNavigator().navigate(NortWindow.REGISTER);
		}
		else if (e.getActionCommand().equals("goToLoginBtn") || 
				e.getActionCommand().equals("mainMenuLogoutBtn")) {
			Starter.getNavigator().navigate(NortWindow.LOGIN);
		}
		else if (e.getActionCommand().equals("mainMenuPlayerTwoBtn")) {
			Starter.getNavigator().navigate(NortWindow.PLAYERTWO);
		}
	}
	
	/**
	 * Hashes an entered password
	 * @param enteredPassword The password that was entered by a user
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
