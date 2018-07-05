package ch.bbw.view;

import javax.swing.JPanel;

import ch.bbw.view.enums.NortWindow;
import ch.bbw.view.interfaces.Navigator;
import ch.bbw.view.pane.GamePane;
import ch.bbw.view.pane.GameSettingsPane;
import ch.bbw.view.pane.LeaderboardPane;
import ch.bbw.view.pane.LoginPane;
import ch.bbw.view.pane.MainMenuPane;
import ch.bbw.view.pane.PlayerTwoPane;
import ch.bbw.view.pane.RegisterPane;

/**
 * Implements the Navigator pattern and contains logic for Swing technology
 * @author 5ia16padraheim
 */
public class SwingNavigator implements Navigator {

	private static final Navigator navigator = new SwingNavigator();
	
	private SwingNavigator() {}
	
	@Override
	public void navigate(NortWindow window) {
		JPanel newContentPane = null;
		
		switch(window) {
			case GAME:
				newContentPane = new GamePane().initGui();
				
				break;
			case GAMEMENU:
				break;
			case GAMESETTINGS:
				newContentPane = new GameSettingsPane().initGui();
				
				break;
			case LEADERBOARD:
				newContentPane = new LeaderboardPane().initGui();
				
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
		
		NortFrame.getInstance().setContentPane(newContentPane);
	}
	
	public static Navigator getInstance() {
		return navigator;
	}
}
