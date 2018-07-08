package ch.bbw.view;

import javax.swing.JPanel;

import ch.bbw.controller.Starter;
import ch.bbw.view.enums.NortWindow;
import ch.bbw.view.interfaces.Navigator;
import ch.bbw.view.pane.GamePane;
import ch.bbw.view.pane.GameSettingsPane;
import ch.bbw.view.pane.LeaderboardsPane;
import ch.bbw.view.pane.LoginPane;
import ch.bbw.view.pane.MainMenuPane;
import ch.bbw.view.pane.PlayerTwoPane;
import ch.bbw.view.pane.RegisterPane;

/**
 * Implements the Navigator pattern and contains logic for Swing technology
 * @author 5ia16padraheim
 */
public class SwingNavigator implements Navigator {

	private static Navigator navigator;
	
	private SwingNavigator() {}
	
	@Override
	public void navigate(NortWindow window) {
		JPanel newContentPane = null;
		
		switch(window) {
			case GAME:
				newContentPane = new GamePane().initGui();
				
				break;
			case GAMESETTINGS:
				newContentPane = new GameSettingsPane().initGui();
				
				break;
			case LEADERBOARDS:
				newContentPane = new LeaderboardsPane().initGui();
				
				break;
			case LOGIN:
				newContentPane = new LoginPane().initGui();
				
				break;
			case MAINMENU:
				newContentPane = new MainMenuPane().initGui();
				
				break;
			case PLAYERTWO:
				newContentPane = new PlayerTwoPane().initGui();
				
				break;
			case REGISTER:
				newContentPane = new RegisterPane().initGui();
				
				break;
		}
		
		Starter.getInstance().getNortFrame().setContentPane(newContentPane);
		Starter.getInstance().getNortFrame().getContentPane().requestFocusInWindow();
	}
	
	public static Navigator getInstance() {
		if (navigator == null) {
			navigator = new SwingNavigator();
		}
		
		return navigator;
	}
}
