package ch.bbw.controller.interfaces;

/**
 * Interface that contains methods to interact with GUI components
 * @author 5ia16padraheim
 */
public interface ComponentInteractor {
	
	/**
	 * Returns the username entered in the Login UI
	 * @return The entered username
	 */
	public String getLoginUsername();

	/**
	 * Returns the password entered in the Login UI
	 * @return The entered password
	 */
	public String getLoginPassword();

	/**
	 * Returns the username entered in the Register UI
	 * @return The entered username
	 */
	public String getRegisterUsername();

	/**
	 * Returns the password entered in the Register UI
	 * @return The entered password
	 */
	public String getRegisterPassword();
	
	/**
	 * Returns the username entered in the PlayerTwo login field
	 * @return The entered username
	 */
	public String getPlayerTwoLoginUsername();

	/**
	 * Returns the password entered in the PlayerTwo login field
	 * @return The entered password
	 */
	public String getPlayerTwoLoginPassword();

	/**
	 * Returns the username entered in the PlayerTwo register field
	 * @return The entered username
	 */
	public String getPlayerTwoRegisterUsername();

	/**
	 * Returns the password entered in the PlayerTwo register field
	 * @return The entered password
	 */
	public String getPlayerTwoRegisterPassword();
	
	/**
	 * Sets the text of the LoginInfo
	 * @param text The new text resembling the LoginInfo
	 */
	public void setLoginInfoText(String text);
	
	/**
	 * Sets the text of the RegisterInfo
	 * @param text The new text resembling the RegisterInfo
	 */
	public void setRegisterInfoText(String text);
	
	/**
	 * Sets the text of the PlayerTwoLoginInfo
	 * @param text The new text resembling the PlayerTwoLoginInfo
	 */
	public void setPlayerTwoLoginInfoText(String text);
	
	/**
	 * Sets the text of the PlayerTwoRegisterInfo
	 * @param text The new text resembling the PlayerTwoRegisterInfo
	 */
	public void setPlayerTwoRegisterInfoText(String text);
	
	/**
	 * Sets the text of the Player1ReadyInfo
	 * @param text The new text resembling the Player1ReadyInfo
	 */
	public void setPlayer1ReadyText(String text);
	
	/**
	 * Sets the text of the Player2ReadyInfo
	 * @param text The new text resembling the Player2ReadyInfo
	 */
	public void setPlayer2ReadyText(String text);
	
	/**
	 * Sets the text of the Player1RoundsWonInfo
	 * @param roundsWon The amount of rounds the player has won
	 */
	public void setPlayer1RoundsWonText(int roundsWon);
	
	/**
	 * Sets the text of the Player2RoundsWonInfo
	 * @param roundsWon The amount of rounds the player has won
	 */
	public void setPlayer2RoundsWonText(int roundsWon);
	
	/**
	 * Returns the amount of rounds a player needs to win a game of Nort
	 * @return The amount of rounds a player needs to win
	 */
	public int getRoundsToWin();
}