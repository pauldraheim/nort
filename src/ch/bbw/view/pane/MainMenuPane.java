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
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bbw.controller.NortListener;
import ch.bbw.controller.Starter;
import ch.bbw.view.NortComponentFactory;

public class MainMenuPane extends JPanel {

	/**
	 * The standard constructor with the exception of it setting all components and rules of the JPanel
	 */
	public MainMenuPane() {
		Insets insets = Starter.getFrame().getInsets();
		
		int spaceBetween = (Starter.getFrame().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(2, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("mainMenuPane");
		setSize(Starter.getFrame().getWidth() - insets.left - insets.right, 
				Starter.getFrame().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = new NortComponentFactory();
		
		try {
			JLabel logoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("resources/logo.png"))));
			logoLabel.setName("logoLabel");
			add(logoLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel mainMenuBtnPanel = new JPanel();
		mainMenuBtnPanel.setName("mainMenuBtnPanel");
		mainMenuBtnPanel.setBackground(Color.BLACK);
		mainMenuBtnPanel.setAlignmentY(CENTER_ALIGNMENT);
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
	}
}
