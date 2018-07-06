package ch.bbw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.bbw.controller.enums.Direction;

/**
 * Listener for all components, handles all actions
 * @author 5ia16padraheim
 */
public class NortListener implements ActionListener, KeyListener {
	
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
			case "mainMenuLeaderboardsBtn":
				inputHandler.handleGoToLeaderboard();
				
				break;
			case "leaderboardBackBtn":
			case "playerTwoBackBtn":
			case "gameSettingsBackBtn":
				inputHandler.handleGoToMainmenu();
				
				break;
			case "gameQuitBtn":
				inputHandler.handleQuitGame();
				
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
			case "mainMenuPlayBtn":
				inputHandler.handleGoToGameSettings();
				
				break;
			case "gameSettingsPlayBtn":
				inputHandler.handleGoToGame();
				
				break;
			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		UserInputHandler inputHandler = new UserInputHandler();
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer2Direction(Direction.UP);
				}
				else {
					inputHandler.handlePlayerReady(2);
				}
				
				break;
			case KeyEvent.VK_RIGHT:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer2Direction(Direction.RIGHT);
				}
				
				break;
			case KeyEvent.VK_DOWN:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer2Direction(Direction.DOWN);
				}
				
				break;
			case KeyEvent.VK_LEFT:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer2Direction(Direction.LEFT);
				}
				
				break;
			case KeyEvent.VK_W:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer1Direction(Direction.UP);
				}
				else {
					inputHandler.handlePlayerReady(1);
				}
				
				break;
			case KeyEvent.VK_D:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer1Direction(Direction.RIGHT);
				}
				
				break;
			case KeyEvent.VK_S:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer1Direction(Direction.DOWN);
				}
				
				break;
			case KeyEvent.VK_A:
				if (Game.getInstance().isRoundRunning()) {
					Game.getInstance().setPlayer1Direction(Direction.LEFT);
				}
				
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}