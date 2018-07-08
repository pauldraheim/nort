package ch.bbw.controller;

import java.lang.Thread.State;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import ch.bbw.controller.interfaces.ComponentInteractor;
import ch.bbw.model.User;
import ch.bbw.services.AbstractNortServiceProvider;
import ch.bbw.view.SwingNavigator;
import ch.bbw.view.enums.NortWindow;

/**
 * Handles all sorts of user input
 * @author 5ia16padraheim
 */
public class UserInputHandler {
	
	private static UserInputHandler userInputHandler;

	/**
	 * Handles what to do when the user initiates the quit process
	 */
	public void handleQuit() {
		if (NortGameLoop.getInstance().getPlayer1().getUser() != null) {
			NortGameLoop.getInstance().getPlayer1().setUser(null);
		}
		if (NortGameLoop.getInstance().getPlayer2().getUser() != null) {
			NortGameLoop.getInstance().getPlayer2().setUser(null);
		}
		
		System.exit(0);
	}

	/**
	 * Handles what to do when the user initiates the login process
	 */
	public void handleLogin() {
		ComponentInteractor compInteractor = Starter.getInstance().getComponentInteractor();
		
		String password = compInteractor.getLoginPassword();
		String username = compInteractor.getLoginUsername();

		String hashedEnteredPassword = hashPassword(password);

		User user = AbstractNortServiceProvider.getInstance().getUserService().login(username, hashedEnteredPassword);

		if (user == null) {
			compInteractor.setLoginInfoText("Incorrect combination of username and password");
		} else {
			NortGameLoop.getInstance().getPlayer1().setUser(user);

			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
	}

	/**
	 * Handles what to do when the user initiates the register process
	 */
	public void handleRegister() {
		ComponentInteractor compInteractor = Starter.getInstance().getComponentInteractor();

		String password = compInteractor.getRegisterPassword();
		String username = compInteractor.getRegisterUsername();
		
		User user = AbstractNortServiceProvider.getInstance().getUserService().register(username, hashPassword(password));
		
		if (user != null) {
			NortGameLoop.getInstance().getPlayer1().setUser(user);
			
			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		} else {
			compInteractor.setRegisterInfoText("Username already taken");
		}
	}

	/**
	 * Handles what to do when a user initiates the login process for the second user
	 */
	public void handlePlayerTwoLogin() {
		ComponentInteractor compInteractor = Starter.getInstance().getComponentInteractor();
		
		String password = compInteractor.getPlayerTwoLoginPassword();
		String username = compInteractor.getPlayerTwoLoginUsername();

		String hashedEnteredPassword = hashPassword(password);

		User user = AbstractNortServiceProvider.getInstance().getUserService().login(username, hashedEnteredPassword);

		if (user == null) {
			compInteractor.setPlayerTwoLoginInfoText("Incorrect login details");
		} else {
			NortGameLoop.getInstance().getPlayer2().setUser(user);

			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
	}

	/**
	 * Handles what to do when a user initiates the register process for the second user
	 */
	public void handlePlayerTwoRegister() {
		ComponentInteractor compInteractor = Starter.getInstance().getComponentInteractor();

		String password = compInteractor.getPlayerTwoRegisterPassword();
		String username = compInteractor.getPlayerTwoRegisterUsername();
		
		User user = AbstractNortServiceProvider.getInstance().getUserService().register(username, hashPassword(password));
		
		if (user != null) {
			NortGameLoop.getInstance().getPlayer2().setUser(user);
			
			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
		else {
			compInteractor.setPlayerTwoRegisterInfoText("Username already taken!");
		}
	}

	/**
	 * Handles what to do when a user initiates the Player2 logout process
	 */
	public void handlePlayerTwoLogout() {
		NortGameLoop.getInstance().getPlayer2().setUser(null);

		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}

	/**
	 * Handles what to do when a user initiates the process that should navigate to the Login screen
	 */
	public void handleGoToLogin() {
		if (NortGameLoop.getInstance().getPlayer1().getUser() != null) {
			NortGameLoop.getInstance().getPlayer1().setUser(null);
		}
		if (NortGameLoop.getInstance().getPlayer2().getUser() != null) {
			NortGameLoop.getInstance().getPlayer2().setUser(null);
		}

		SwingNavigator.getInstance().navigate(NortWindow.LOGIN);
	}

	/**
	 * Handles what to do when a player readies up
	 * 
	 * @param playerNumber
	 *            The number of the player that got ready
	 */
	public void handlePlayerReady(int playerNumber) {
		if (NortGameLoop.getInstance().isGameRunning()) {
			if (playerNumber == 1) {
				if (NortGameLoop.getInstance().getPlayer1().isPlayerReady()) {
					Starter.getInstance().getComponentInteractor().setPlayer1ReadyText("NOT READY (UP)");
					NortGameLoop.getInstance().getPlayer1().setPlayerReady(false);
				} else {
					Starter.getInstance().getComponentInteractor().setPlayer1ReadyText("READY (UP)");
					NortGameLoop.getInstance().getPlayer1().setPlayerReady(true);
				}
			} else {
				if (NortGameLoop.getInstance().getPlayer2().isPlayerReady()) {
					Starter.getInstance().getComponentInteractor().setPlayer2ReadyText("(UP) NOT READY");
					NortGameLoop.getInstance().getPlayer2().setPlayerReady(false);
				} else {
					Starter.getInstance().getComponentInteractor().setPlayer2ReadyText("(UP) READY");
					NortGameLoop.getInstance().getPlayer2().setPlayerReady(true);
				}
			}

			if (NortGameLoop.getInstance().getPlayer1().isPlayerReady() && NortGameLoop.getInstance().getPlayer2().isPlayerReady()) {
				if (NortGameLoop.getInstance().getState().equals(State.NEW)) {
					NortGameLoop.getInstance().start();
				} else if (NortGameLoop.getInstance().getState().equals(State.TERMINATED)) {
					User player1User = NortGameLoop.getInstance().getPlayer1().getUser();
					User player2User = NortGameLoop.getInstance().getPlayer2().getUser();
					int roundsForWin = NortGameLoop.getInstance().getRoundsForWin();
					
					NortGameLoop.reset();
					
					NortGameLoop.getInstance().getPlayer1().setUser(player1User);
					NortGameLoop.getInstance().getPlayer2().setUser(player2User);
					NortGameLoop.getInstance().setRoundsForWin(roundsForWin);
					NortGameLoop.getInstance().setGameRunning(true);
					
					NortGameLoop.getInstance().start();
				} else {
					NortGameLoop.getInstance().setRoundRunning(true);
				}
			}
		}
	}

	/**
	 * Handles what to do when the user initiates the process that should quit a game of Nort
	 */
	public void handleQuitGame() {
		NortGameLoop game = NortGameLoop.getInstance();

		game.getPlayer1().getUser().setRoundWins(game.getPlayer1().getUser().getRoundWins() + game.getPlayer1().getCurrRoundWins());
		game.getPlayer2().getUser().setRoundWins(game.getPlayer2().getUser().getRoundWins() + game.getPlayer2().getCurrRoundWins());
		
		AbstractNortServiceProvider.getInstance().getUserService().updateDatasource();

		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}

	/**
	 * Handles what to do when the user initiates the process that should direct towards the Register screen
	 */
	public void handleGoToRegister() {
		SwingNavigator.getInstance().navigate(NortWindow.REGISTER);
	}

	/**
	 * Handles what to do when the user initiates the process that should navigate to the Player2 screen
	 */
	public void handleGoToPlayerTwo() {
		SwingNavigator.getInstance().navigate(NortWindow.PLAYERTWO);
	}

	/**
	 * Handles what to do when the user initiates the process that should lead to the Mainmenu screen
	 */
	public void handleGoToMainmenu() {
		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}

	/**
	 * Handles what to do when the user initiates the process that should navigate to the Leaderboards screen
	 */
	public void handleGoToLeaderboards() {
		SwingNavigator.getInstance().navigate(NortWindow.LEADERBOARDS);
	}

	/**
	 * Handles what to do when the user initiates the process that should navigate to the Game settings screen
	 */
	public void handleGoToGameSettings() {
		if (NortGameLoop.getInstance().getPlayer2().getUser() != null) {
			SwingNavigator.getInstance().navigate(NortWindow.GAMESETTINGS);
		}
	}

	/**
	 * Handles what to do when the user initiates the process that should direct him towards the game screen
	 */
	public void handleGoToGame() {
		NortGameLoop.getInstance().setRoundsForWin(Starter.getInstance().getComponentInteractor().getRoundsToWin());
		NortGameLoop.getInstance().setGameRunning(true);

		SwingNavigator.getInstance().navigate(NortWindow.GAME);
	}

	/**
	 * Hashes an entered password
	 * 
	 * @param enteredPassword The password that was entered by the user
	 * @return The hashed password
	 */
	private String hashPassword(String enteredPassword) {
		String hashedEnteredPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(enteredPassword.getBytes());

			byte[] digest = md.digest();

			hashedEnteredPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hashedEnteredPassword;
	}
	
	/**
	 * Returns the only instance of the UserInputHandler class and initiates it if it hasn't been yet
	 * @return The only instance of the UserInputHandler class
	 */
	public static UserInputHandler getInstance() {
		if (userInputHandler == null) {
			userInputHandler = new UserInputHandler();
		}
		 
		return userInputHandler;
	}
}
