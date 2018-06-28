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
		
		if (e.getActionCommand().equals("loginQuitBtn") || e.getActionCommand().equals("registerQuitBtn") || 
				e.getActionCommand().equals("mainMenuQuitBtn")) {
			inputHandler.handleQuit();
		}
		else if (e.getActionCommand().equals("loginBtn")) {
			inputHandler.handleLogin();
		}
		else if (e.getActionCommand().equals("registerBtn")) {
			inputHandler.handleRegister();
		}
		else if (e.getActionCommand().equals("goToRegisterBtn")) {
			inputHandler.handleGoToRegister();
		}
		else if (e.getActionCommand().equals("goToLoginBtn") || 
				e.getActionCommand().equals("mainMenuLogoutBtn")) {
			inputHandler.handleGoToLogin();
		}
		else if (e.getActionCommand().equals("mainMenuPlayerTwoBtn")) {
			inputHandler.handleGoToPlayerTwo();
		}
		else if (e.getActionCommand().equals("playerTwoBackBtn")) {
			inputHandler.handleGoToMainmenu();
		}
	}
}