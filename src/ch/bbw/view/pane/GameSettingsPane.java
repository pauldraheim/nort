package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.bbw.controller.Game;
import ch.bbw.controller.NortListener;
import ch.bbw.view.NortComponentFactory;
import ch.bbw.view.NortFrame;

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
		Insets insets = NortFrame.getInstance().getInsets();
		
		int spaceBetween = (NortFrame.getInstance().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(5, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("gameSettingsPane");
		setSize(NortFrame.getInstance().getWidth() - insets.left - insets.right, NortFrame.getInstance().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = NortComponentFactory.getInstance();
		
		add(compFactory.createTitleLabel("mainMenuLabel", "Game settings"));
		
		JPanel gameSettingsPlayersPanel = new JPanel();
		gameSettingsPlayersPanel.setName("gameSettingsPlayersPanel");
		gameSettingsPlayersPanel.setBackground(Color.BLACK);
		gameSettingsPlayersPanel.setLayout(new GridLayout(3, 2, spaceBetween, spaceBetween));
		
		gameSettingsPlayersPanel.add(compFactory.createTitleLabel("mainMenuPlayer1InfoLabel", Game.getInstance().getPlayer1().getUsername()));
		gameSettingsPlayersPanel.add(compFactory.createTitleLabel("mainMenuPlayer2InfoLabel", Game.getInstance().getPlayer2().getUsername()));
		
		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("mainMenuPlayer1ControlsTitleLabel", "Controls:"));
		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("mainMenuPlayer2ControlsTitleLabel", "Controls:"));

		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("mainMenuPlayer1ControlsLabel", "← = left, ↑ = up, → = right, ↓ = down"));
		gameSettingsPlayersPanel.add(compFactory.createDescriptionLabel("mainMenuPlayer2ControlsLabel", "A = left, W = up, D = right, S = down"));
		
		add(gameSettingsPlayersPanel);
		
		add(compFactory.createTitleLabel("mainMenuRoundsLabel", "Rounds needed to win"));
		
		add(compFactory.createSlider("mainMenuRoundsSlider", 1, 11, 3, 1, 2, SwingConstants.HORIZONTAL));
		
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
