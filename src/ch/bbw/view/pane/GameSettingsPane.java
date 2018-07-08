package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.bbw.controller.NortGameLoop;
import ch.bbw.controller.NortListener;
import ch.bbw.controller.Starter;
import ch.bbw.view.NortComponentFactory;

/**
 * The JPanel that the Game settings can be changed in
 * @author 5ia16padraheim
 */
public class GameSettingsPane extends JPanel {
	
	/**
	 * Initializes all components of the GameSettingsPane and returns the instance of the initialized GameSettingsPane
	 * @return The initialized GameSettingsPane
	 */
	public GameSettingsPane initGui() {
		Insets insets = Starter.getInstance().getNortFrame().getInsets();
		
		int spaceBetween = (Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(5, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("gameSettingsPane");
		setSize(Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right, 
				Starter.getInstance().getNortFrame().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = Starter.getInstance().getNortComponentFactory();
		
		add(compFactory.createTitleLabel("gameSettingsLabel", "Game settings"));
		
		JPanel gameSettingsPlayersPanel = new JPanel();
		gameSettingsPlayersPanel.setName("gameSettingsPlayersPanel");
		gameSettingsPlayersPanel.setBackground(Color.BLACK);
		gameSettingsPlayersPanel.setLayout(new GridLayout(3, 2, spaceBetween, spaceBetween));
		
		gameSettingsPlayersPanel.add(compFactory.createTitleLabel("gameSettingsPlayer1InfoLabel", 
				NortGameLoop.getInstance().getPlayer1().getUser().getUsername()));
		gameSettingsPlayersPanel.add(compFactory.createTitleLabel("gameSettingsPlayer2InfoLabel", 
				NortGameLoop.getInstance().getPlayer2().getUser().getUsername()));
		
		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("gameSettingsPlayer1ControlsTitleLabel", "Controls:"));
		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("gameSettingsPlayer2ControlsTitleLabel", "Controls:"));

		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("gameSettingsPlayer1ControlsLabel", "A = left, W = up, D = right, S = down"));
		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("gameSettingsPlayer2ControlsLabel", "← = left, ↑ = up, → = right, ↓ = down"));
		
		add(gameSettingsPlayersPanel);
		
		add(compFactory.createTitleLabel("gameSettingsRoundsLabel", "Rounds needed to win"));
		
		add(compFactory.createSlider("gameSettingsRoundsSlider", 1, 11, 3, 1, 2, SwingConstants.HORIZONTAL));
		
		JPanel gameSettingsBtnPanel = new JPanel();
		gameSettingsBtnPanel.setName("gameSettingsBtnPanel");
		gameSettingsBtnPanel.setBackground(Color.BLACK);
		gameSettingsBtnPanel.setLayout(new GridLayout(1, 2, spaceBetween, spaceBetween));

		NortListener listener = new NortListener();
		
		JButton gameSettingsBackBtn = compFactory.createButton("gameSettingsBackBtn", "Back");
		gameSettingsBackBtn.setActionCommand("gameSettingsBackBtn");
		gameSettingsBackBtn.addActionListener(listener);
		
		JButton gameSettingsPlayBtn = compFactory.createButton("gameSettingsPlayBtn", "Play");
		gameSettingsPlayBtn.setActionCommand("gameSettingsPlayBtn");
		gameSettingsPlayBtn.addActionListener(listener);

		gameSettingsBtnPanel.add(gameSettingsBackBtn);
		gameSettingsBtnPanel.add(gameSettingsPlayBtn);
		
		add(gameSettingsBtnPanel);
		
		return this;
	}
}
