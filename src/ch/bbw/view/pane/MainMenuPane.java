package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bbw.controller.NortGameLoop;
import ch.bbw.controller.NortListener;
import ch.bbw.controller.Starter;
import ch.bbw.view.NortComponentFactory;

/**
 * The JPanel containing the MainMenu
 * @author 5ia16padraheim
 */
public class MainMenuPane extends JPanel {
	
	/**
	 * Initializes all components of the MainMenuPane and returns the instance of the initialized MainMenuPane
	 * @return The initialized MainMenuPane
	 */
	public MainMenuPane initGui() {
		Insets insets = Starter.getInstance().getNortFrame().getInsets();
		
		int spaceBetween = (Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right) / 500;
		
		setLayout(new GridLayout(2, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("mainMenuPane");
		setSize(Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right, 
				Starter.getInstance().getNortFrame().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = Starter.getInstance().getNortComponentFactory();
		
		JPanel mainMenuLabelPanel = new JPanel();
		mainMenuLabelPanel.setName("mainMenuLabelPanel");
		mainMenuLabelPanel.setBackground(Color.BLACK);
		mainMenuLabelPanel.setLayout(new BoxLayout(mainMenuLabelPanel, BoxLayout.Y_AXIS));
		
		try {
			JLabel logoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("resources/logo.png"))));
			logoLabel.setName("logoLabel");
			logoLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			mainMenuLabelPanel.add(logoLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String players= "";
		
		if (NortGameLoop.getInstance().getPlayer2().getUser() != null) {
			players = "Player 1: " + NortGameLoop.getInstance().getPlayer1().getUser().getUsername() + 
					", Player 2: " + NortGameLoop.getInstance().getPlayer2().getUser().getUsername();
		} 
		else {
			players = "Player 1: " + NortGameLoop.getInstance().getPlayer1().getUser().getUsername() + ", Player 2: Not logged in";
		}
		
		mainMenuLabelPanel.add(compFactory.createDescriptionLabel("mainMenuPlayersLabel", players));
		
		add(mainMenuLabelPanel);
		
		JPanel mainMenuBtnPanel = new JPanel();
		mainMenuBtnPanel.setName("mainMenuBtnPanel");
		mainMenuBtnPanel.setBackground(Color.BLACK);
		mainMenuBtnPanel.setLayout(new BoxLayout(mainMenuBtnPanel, BoxLayout.Y_AXIS));
		
		NortListener listener = new NortListener();
		
		JButton mainMenuPlayBtn = compFactory.createButton("mainMenuPlayBtn", "Play");
		mainMenuPlayBtn.setActionCommand("mainMenuPlayBtn");
		mainMenuPlayBtn.addActionListener(listener);
		
		JButton mainMenuLeaderboardsBtn = compFactory.createButton("mainMenuLeaderboardsBtn", "Leaderboards");
		mainMenuLeaderboardsBtn.setActionCommand("mainMenuLeaderboardsBtn");
		mainMenuLeaderboardsBtn.addActionListener(listener);
		
		JButton mainMenuPlayerTwoBtn = compFactory.createButton("mainMenuPlayerTwoBtn", "Player 2");
		mainMenuPlayerTwoBtn.setActionCommand("mainMenuPlayerTwoBtn");
		mainMenuPlayerTwoBtn.addActionListener(listener);
		
		JButton mainMenuLogoutBtn = compFactory.createButton("mainMenuLogoutBtn", "Logout");
		mainMenuLogoutBtn.setActionCommand("mainMenuLogoutBtn");
		mainMenuLogoutBtn.addActionListener(listener);
		
		JButton mainMenuQuitBtn = compFactory.createButton("mainMenuQuitBtn", "Quit");
		mainMenuQuitBtn.setActionCommand("mainMenuQuitBtn");
		mainMenuQuitBtn.addActionListener(listener);

		mainMenuBtnPanel.add(Box.createVerticalGlue());
		mainMenuBtnPanel.add(mainMenuPlayBtn);
		mainMenuBtnPanel.add(Box.createVerticalGlue());
		mainMenuBtnPanel.add(mainMenuLeaderboardsBtn);
		mainMenuBtnPanel.add(Box.createVerticalGlue());
		mainMenuBtnPanel.add(mainMenuPlayerTwoBtn);
		mainMenuBtnPanel.add(Box.createVerticalGlue());
		mainMenuBtnPanel.add(mainMenuLogoutBtn);
		mainMenuBtnPanel.add(Box.createVerticalGlue());
		mainMenuBtnPanel.add(mainMenuQuitBtn);
		
		add(mainMenuBtnPanel);
		
		return this;
	}
}
