package ch.bbw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for all components, handles all actions
 * @author 5ia16padraheim
 */
public class NortListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		UserInputHandler inputHandler = new UserInputHandler();
		
		switch(e.getActionCommand()) {
			case "loginQuitBtn":
			case "registerQuitBtn":
			case "mainMenuQuitBtn":
				inputHandler.handleQuit();
				
				break;
			case "loginBtn":
				inputHandler.handleLogin();
				
				break;
			case "registerBtn":
				inputHandler.handleRegister();
				
				break;
			case "goToRegisterBtn":
				inputHandler.handleGoToRegister();
				
				break;
			case "goToLoginBtn":
			case "mainMenuLogoutBtn":
				inputHandler.handleGoToLogin();
				
				break;
			case "mainMenuPlayerTwoBtn":
				inputHandler.handleGoToPlayerTwo();				
				
				break;
			case "playerTwoBackBtn":
				inputHandler.handleGoToMainmenu();
				
				break;
			case "playerTwoLoginBtn":
				inputHandler.handlePlayerTwoLogin();
				
				break;
			case "playerTwoRegisterBtn":
				inputHandler.handlePlayerTwoRegister();
				
				break;
			case "playerTwoLogoutBtn":
				inputHandler.handlePlayerTwoLogout();
				
				break;
			default:
				break;
		}
	}
}