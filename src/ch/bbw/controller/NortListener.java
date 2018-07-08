package ch.bbw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.bbw.controller.enums.Direction;

/**
 * Listener for all components
 * @author 5ia16padraheim
 */
public class NortListener implements ActionListener, KeyListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "loginQuitBtn":
			case "registerQuitBtn":
			case "mainMenuQuitBtn":
				Starter.getInstance().getUserInputHandler().handleQuit();
				
				break;
			case "loginBtn":
				Starter.getInstance().getUserInputHandler().handleLogin();
				
				break;
			case "registerBtn":
				Starter.getInstance().getUserInputHandler().handleRegister();
				
				break;
			case "goToRegisterBtn":
				Starter.getInstance().getUserInputHandler().handleGoToRegister();
				
				break;
			case "goToLoginBtn":
			case "mainMenuLogoutBtn":
				Starter.getInstance().getUserInputHandler().handleGoToLogin();
				
				break;
			case "mainMenuPlayerTwoBtn":
				Starter.getInstance().getUserInputHandler().handleGoToPlayerTwo();				
				
				break;
			case "mainMenuLeaderboardsBtn":
				Starter.getInstance().getUserInputHandler().handleGoToLeaderboards();
				
				break;
			case "leaderboardBackBtn":
			case "playerTwoBackBtn":
			case "gameSettingsBackBtn":
				Starter.getInstance().getUserInputHandler().handleGoToMainmenu();
				
				break;
			case "gameQuitBtn":
				Starter.getInstance().getUserInputHandler().handleQuitGame();
				
				break;
			case "playerTwoLoginBtn":
				Starter.getInstance().getUserInputHandler().handlePlayerTwoLogin();
				
				break;
			case "playerTwoRegisterBtn":
				Starter.getInstance().getUserInputHandler().handlePlayerTwoRegister();
				
				break;
			case "playerTwoLogoutBtn":
				Starter.getInstance().getUserInputHandler().handlePlayerTwoLogout();
				
				break;
			case "mainMenuPlayBtn":
				Starter.getInstance().getUserInputHandler().handleGoToGameSettings();
				
				break;
			case "gameSettingsPlayBtn":
				Starter.getInstance().getUserInputHandler().handleGoToGame();
				
				break;
			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		UserInputHandler inputHandler = new UserInputHandler();
		
		if (!NortGameLoop.getInstance().isRoundRunning()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					inputHandler.handlePlayerReady(2);
					
					break;
				case KeyEvent.VK_W:
					inputHandler.handlePlayerReady(1);
					
					break;
			}
		} else {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer2().getDirection().equals(Direction.UP.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer2().setDirection(Direction.UP);
					}
					
					break;
				case KeyEvent.VK_RIGHT:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer2().getDirection().equals(Direction.RIGHT.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer2().setDirection(Direction.RIGHT);
					}
					
					break;
				case KeyEvent.VK_DOWN:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer2().getDirection().equals(Direction.DOWN.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer2().setDirection(Direction.DOWN);
					}
					
					break;
				case KeyEvent.VK_LEFT:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer2().getDirection().equals(Direction.LEFT.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer2().setDirection(Direction.LEFT);
					}
					
					break;
				case KeyEvent.VK_W:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer1().getDirection().equals(Direction.UP.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer1().setDirection(Direction.UP);
					}
					
					break;
				case KeyEvent.VK_D:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer1().getDirection().equals(Direction.RIGHT.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
					}
					
					break;
				case KeyEvent.VK_S:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer1().getDirection().equals(Direction.DOWN.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer1().setDirection(Direction.DOWN);
					}
					
					break;
				case KeyEvent.VK_A:
					if (NortGameLoop.getInstance().isRoundRunning() && 
							!NortGameLoop.getInstance().getPlayer1().getDirection().equals(Direction.LEFT.getOppositeDirection())) {
						NortGameLoop.getInstance().getPlayer1().setDirection(Direction.LEFT);
					}
					
					break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}