package ch.bbw.view;

import javax.swing.JPanel;

import ch.bbw.controller.Starter;
import ch.bbw.view.enums.NortWindow;
import ch.bbw.view.interfaces.Navigator;
import ch.bbw.view.pane.LoginPane;
import ch.bbw.view.pane.MainMenuPane;
import ch.bbw.view.pane.PlayerTwoPane;
import ch.bbw.view.pane.RegisterPane;

/**
 * Implements the Navigator pattern and contains logic for Swing technology
 * @author 5
 *
 */
public class SwingNavigator implements Navigator {

	@Override
	public void navigate(NortWindow window) {
		JPanel newContentPane = null;
		
		switch(window) {
			case GAME:
				break;
			case GAMEMENU:
				break;
			case GAMEOPTIONS:
				break;
			case LEADERBOARDS:
				break;
			case LOGIN:
				newContentPane = new LoginPane();
				
				break;
			case MAINMENU:
				newContentPane = new MainMenuPane();
				
				break;
			case PLAYERTWO:
				newContentPane = new PlayerTwoPane();
				
				break;
			case REGISTER:
				newContentPane = new RegisterPane();
				
				break;
		}
		
		Starter.getFrame().setContentPane(newContentPane);
	}
}
