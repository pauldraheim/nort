package ch.bbw.controller.interfaces;

/**
 * Interface that contains methods to get every value from GUI Components needed
 * @author 5ia16padraheim
 */
public interface ComponentSetterGetter {
	
	/**
	 * Returns the username entered in the LoginPane
	 * @return The entered username
	 */
	public String getLoginUsername();

	/**
	 * Returns the password entered in the LoginPane
	 * @return The entered password
	 */
	public String getLoginPassword();

	/**
	 * Returns the username entered in the RegisterPane
	 * @return The entered username
	 */
	public String getRegisterUsername();

	/**
	 * Returns the password entered in the RegisterPane
	 * @return The entered password
	 */
	public String getRegisterPassword();
	
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
}