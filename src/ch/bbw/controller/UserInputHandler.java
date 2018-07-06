package ch.bbw.controller;

import java.lang.Thread.State;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import ch.bbw.controller.interfaces.ComponentSetterGetter;
import ch.bbw.model.User;
import ch.bbw.services.AbstractNortServiceProvider;
import ch.bbw.services.UserService;
import ch.bbw.view.SwingNavigator;
import ch.bbw.view.enums.NortWindow;

/**
 * Handles all sorts of user input
 * 
 * @author 5ia16padraheim
 */
public class UserInputHandler {

	private UserService userService = AbstractNortServiceProvider.getInstance().getUserService();

	/**
	 * Handles what to do when the user clicks on a quit button
	 */
	public void handleQuit() {
		AbstractNortServiceProvider.getInstance().getUserService().updateDatasource();
		
		System.exit(0);
	}

	/**
	 * Handles what to do when the user clicks on the login button
	 */
	public void handleLogin() {
		ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();

		String password = valueSetterGetter.getLoginPassword();
		String username = valueSetterGetter.getLoginUsername();

		String hashedEnteredPassword = hashPassword(password);

		User user = userService.login(username, hashedEnteredPassword);

		if (user == null) {
			valueSetterGetter.setLoginInfoText("Incorrect combination of username and password");
		} else {
			Game.getInstance().setPlayer1(user);

			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
	}

	/**
	 * Handles what to do when the user clicks on the register button
	 */
	public void handleRegister() {

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
			userService.addToDataSource(new User(username, hashPassword(password), 0, 0));

			for (Object userObject : userService.getAllFromDataSource()) {
				User user = (User) userObject;

				if (user.getUsername().equals(username)) {
					Game.getInstance().setPlayer1(user);
				}
			}

			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		} else {
			valueSetterGetter.setRegisterInfoText("Username already taken!");
		}
	}

	/**
	 * Handles what to do when the user clicks on the login button
	 */
	public void handlePlayerTwoLogin() {

		ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();

		String password = valueSetterGetter.getPlayerTwoLoginPassword();
		String username = valueSetterGetter.getPlayerTwoLoginUsername();

		String hashedEnteredPassword = hashPassword(password);

		boolean hasMatchingUser = false;

		for (Object userObject : userService.getAllFromDataSource()) {
			User user = (User) userObject;

			if (user.getPassword().equals(hashedEnteredPassword) && user.getUsername().equals(username)
					&& !Game.getInstance().getPlayer1().getUsername().equals(username)) {
				hasMatchingUser = true;

				Game.getInstance().setPlayer2(user);
			}
		}

		if (!hasMatchingUser) {
			valueSetterGetter.setPlayerTwoLoginInfoText("Incorrect login details");
		} else {
			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		}
	}

	/**
	 * Handles what to do when the user clicks on the register button
	 */
	public void handlePlayerTwoRegister() {

		ComponentSetterGetter valueSetterGetter = new SwingComponentSetterGetter();

		String password = valueSetterGetter.getPlayerTwoRegisterPassword();
		String username = valueSetterGetter.getPlayerTwoRegisterUsername();

		boolean isUsernameTaken = false;

		for (Object userObject : userService.getAllFromDataSource()) {
			User user = (User) userObject;

			if (user.getUsername().equals(username)) {
				isUsernameTaken = true;
			}
		}

		if (!isUsernameTaken) {
			userService.addToDataSource(new User(username, hashPassword(password), 0, 0));

			for (Object userObject : userService.getAllFromDataSource()) {
				User user = (User) userObject;

				if (user.getUsername().equals(username)) {
					Game.getInstance().setPlayer2(user);
				}
			}

			SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
		} else {
			valueSetterGetter.setPlayerTwoRegisterInfoText("Username already taken!");
		}
	}

	/**
	 * Handles what to do when the user clicks the player two logout button
	 */
	public void handlePlayerTwoLogout() {
		AbstractNortServiceProvider.getInstance().getUserService().updateDatasource();

		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the login screen
	 */
	public void handleGoToLogin() {
		AbstractNortServiceProvider.getInstance().getUserService().updateDatasource();

		SwingNavigator.getInstance().navigate(NortWindow.LOGIN);
	}

	/**
	 * Handles what to do when a player readies up
	 * 
	 * @param playerNumber
	 *            The number of the player that got ready
	 */
	public void handlePlayerReady(int playerNumber) {
		if (Game.getInstance().isGameRunning()) {
			ComponentSetterGetter compSetterGetter = new SwingComponentSetterGetter();

			if (playerNumber == 1) {
				if (Game.getInstance().isPlayer1Ready()) {
					compSetterGetter.setPlayer1ReadyText("NOT READY (UP)");
					Game.getInstance().setPlayer1Ready(false);
				} else {
					compSetterGetter.setPlayer1ReadyText("READY (UP)");
					Game.getInstance().setPlayer1Ready(true);
				}
			} else {
				if (Game.getInstance().isPlayer2Ready()) {
					compSetterGetter.setPlayer2ReadyText("(UP) NOT READY");
					Game.getInstance().setPlayer2Ready(false);
				} else {
					compSetterGetter.setPlayer2ReadyText("(UP) READY");
					Game.getInstance().setPlayer2Ready(true);
				}
			}

			if (Game.getInstance().isPlayer1Ready() && Game.getInstance().isPlayer2Ready()) {
				if (Game.getInstance().getState().equals(State.NEW)) {
					Game.getInstance().start();
				} else if (Game.getInstance().getState().equals(State.TERMINATED)) {
					User player1 = Game.getInstance().getPlayer1();
					User player2 = Game.getInstance().getPlayer2();
					int roundsForWin = Game.getInstance().getRoundsForWin();
					
					Game.getInstance().reset();
					
					Game.getInstance().setPlayer1(player1);
					Game.getInstance().setPlayer2(player2);
					Game.getInstance().setRoundsForWin(roundsForWin);
					Game.getInstance().setGameRunning(true);
					
					Game.getInstance().start();
				} else {
					Game.getInstance().setRoundRunning(true);
				}
			}
		}
	}

	/**
	 * Handles what to do when the user clicks on a button that should quit the
	 * game
	 */
	public void handleQuitGame() {
		Game game = Game.getInstance();

		game.getPlayer1().setRoundWins(game.getPlayer1().getRoundWins() + game.getPlayer1RoundWins());
		game.getPlayer2().setRoundWins(game.getPlayer2().getRoundWins() + game.getPlayer2RoundWins());

		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the register screen
	 */
	public void handleGoToRegister() {
		SwingNavigator.getInstance().navigate(NortWindow.REGISTER);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the player two screen
	 */
	public void handleGoToPlayerTwo() {
		SwingNavigator.getInstance().navigate(NortWindow.PLAYERTWO);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the mainmenu screen
	 */
	public void handleGoToMainmenu() {
		SwingNavigator.getInstance().navigate(NortWindow.MAINMENU);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the leaderboard screen
	 */
	public void handleGoToLeaderboard() {
		SwingNavigator.getInstance().navigate(NortWindow.LEADERBOARD);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the game settings screen
	 */
	public void handleGoToGameSettings() {
		if (Game.getInstance().getPlayer2() != null)
			SwingNavigator.getInstance().navigate(NortWindow.GAMESETTINGS);
	}

	/**
	 * Handles what to do when the user clicks on a button that should direct
	 * towards the game screen
	 */
	public void handleGoToGame() {
		Game.getInstance().setRoundsForWin(new SwingComponentSetterGetter().getRoundsToWin());
		Game.getInstance().setGameRunning(true);

		SwingNavigator.getInstance().navigate(NortWindow.GAME);
	}

	/**
	 * Hashes an entered password
	 * 
	 * @param enteredPassword
	 *            The password that was entered by the user
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
}
