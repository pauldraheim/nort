package ch.bbw.view.pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bbw.controller.Game;
import ch.bbw.controller.NortListener;
import ch.bbw.view.NortComponentFactory;
import ch.bbw.view.NortFrame;

/**
 * The JPanel containing the Game
 * @author 5ia16padraheim
 */
public class GamePane extends JPanel {
	
	/**
	 * Initializes all components of the GamePane and returns the instance of the initialized GamePane
	 * @return The initialized GamePane
	 */
	public GamePane initGui() {
		Insets insets = NortFrame.getInstance().getInsets();
		
		int spaceBetween = (NortFrame.getInstance().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setName("gamePane");
		setSize(NortFrame.getInstance().getWidth() - insets.left - insets.right, 
				NortFrame.getInstance().getHeight() - insets.top - insets.bottom);
		setFocusable(true);
		
		NortListener listener = new NortListener();
		addKeyListener(listener);
		
		NortComponentFactory compFactory = NortComponentFactory.getInstance();
		
		JPanel gamePlayerNamePane = new JPanel();
		gamePlayerNamePane.setName("gamePlayerNamePane");
		gamePlayerNamePane.setBackground(Color.BLACK);
		gamePlayerNamePane.setLayout(new GridLayout(1, 3, spaceBetween, spaceBetween));
		
		JLabel gamePlayer1NameLabel = compFactory.createTitleLabel("gamePlayer1NameLabel", Game.getInstance().getPlayer1().getUsername());
		gamePlayer1NameLabel.setForeground(Color.CYAN);
		gamePlayerNamePane.add(gamePlayer1NameLabel);
		
		gamePlayerNamePane.add(compFactory.createTitleLabel("gameVsLabel", "VS"));
		
		JLabel gamePlayer2NameLabel = compFactory.createTitleLabel("gamePlayer2NameLabel", Game.getInstance().getPlayer2().getUsername());
		gamePlayer2NameLabel.setForeground(Color.RED);
		gamePlayerNamePane.add(gamePlayer2NameLabel);
		
		add(gamePlayerNamePane, BorderLayout.PAGE_START);
		
		add(new NortPane().initGui(), BorderLayout.CENTER);
		
		JPanel gameRoundInfoMenuPane = new JPanel();
		gameRoundInfoMenuPane.setName("gameRoundInfoMenuPane");
		gameRoundInfoMenuPane.setBackground(Color.BLACK);
		gameRoundInfoMenuPane.setLayout(new GridLayout(1, 5, spaceBetween, spaceBetween));
		
		JLabel gamePlayer1RoundsWonLabel = compFactory.createDescriptionLabel("gamePlayer1RoundsWonLabel", "Rounds won: 0");
		gamePlayer1RoundsWonLabel.setForeground(Color.CYAN);
		gameRoundInfoMenuPane.add(gamePlayer1RoundsWonLabel);
		
		JLabel gamePlayer1ReadyLabel = compFactory.createDescriptionLabel("gamePlayer1ReadyLabel", "NOT READY (UP)");
		gamePlayer1ReadyLabel.setForeground(Color.CYAN);
		gameRoundInfoMenuPane.add(gamePlayer1ReadyLabel);

		JButton gameQuitBtn = compFactory.createButton("gameQuitBtn", "Quit");
		gameQuitBtn.setActionCommand("gameQuitBtn");
		gameQuitBtn.addActionListener(listener);
		gameRoundInfoMenuPane.add(gameQuitBtn);
		
		JLabel gamePlayer2ReadyLabel = compFactory.createDescriptionLabel("gamePlayer2ReadyLabel", "(UP) NOT READY");
		gamePlayer2ReadyLabel.setForeground(Color.RED);
		gameRoundInfoMenuPane.add(gamePlayer2ReadyLabel);
		
		JLabel gamePlayer2RoundsWonLabel = compFactory.createDescriptionLabel("gamePlayer2RoundsWonLabel", "Rounds won: 0");
		gamePlayer2RoundsWonLabel.setForeground(Color.RED);
		gameRoundInfoMenuPane.add(gamePlayer2RoundsWonLabel);
		
		add(gameRoundInfoMenuPane, BorderLayout.PAGE_END);
		
		return this;
	}
}
