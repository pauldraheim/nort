package ch.bbw.view.interfaces;

import ch.bbw.view.enums.NortWindow;

/**
 * Is able to navigate the Game to a different window
 * @author 5ia16padraheim
 */
public interface Navigator {
	
	/**
	 * Navigates the game to a different window
	 * @param window The window that the method should navigate the game to
	 */
	public void navigate(NortWindow window);
}
