package ch.bbw.view.pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.bbw.controller.Game;
import ch.bbw.controller.NortListener;
import ch.bbw.view.NortComponentFactory;
import ch.bbw.view.NortFrame;

/**
 * The JPanel that the second player can Login and Register
 * @author 5ia16padraheim
 */
public class PlayerTwoPane extends JPanel {
	
	/**
	 * Initializes all components of the PlayerTwoPane and returns the instance of the initialized PlayerTwoPane
	 * @return The initialized PlayerTwoPane
	 */
	public PlayerTwoPane initGui() {
		Insets insets = NortFrame.getInstance().getInsets();
		
		int spaceBetween = (NortFrame.getInstance().getWidth() - insets.left - insets.right) / 15;
		
		setBackground(Color.BLACK);
		setName("playerTwoPane");
		setSize(NortFrame.getInstance().getWidth() - insets.left - insets.right, 
				NortFrame.getInstance().getHeight() - insets.top - insets.bottom);

		NortComponentFactory compFactory = NortComponentFactory.getInstance();

		NortListener listener = new NortListener();
		
		JButton playerTwoBackBtn = compFactory.createButton("playerTwoBackBtn", "Back");
		playerTwoBackBtn.setActionCommand("playerTwoBackBtn");
		playerTwoBackBtn.addActionListener(listener);
		
		if (Game.getInstance().getPlayer2() == null) {
			setLayout(new BorderLayout());
			
			JPanel playerTwoLoginRegisterPane = new JPanel();
			playerTwoLoginRegisterPane.setName("playerTwoLoginRegisterPane");
			playerTwoLoginRegisterPane.setBackground(Color.BLACK);
			playerTwoLoginRegisterPane.setLayout(new GridLayout(7, 2, spaceBetween, spaceBetween / 10));
			
			playerTwoLoginRegisterPane.add(compFactory.createTitleLabel("playerTwoLoginLabel", "Login"));
			playerTwoLoginRegisterPane.add(compFactory.createTitleLabel("playerTwoRegisterLabel", "Register"));
			
			playerTwoLoginRegisterPane.add(compFactory.createDescriptionLabel("playerTwoLoginInfoLabel", "Enter your login details"));
			playerTwoLoginRegisterPane.add(compFactory.createDescriptionLabel("playerTwoRegisterInfoLabel", "Enter your desired login details"));
			
			playerTwoLoginRegisterPane.add(compFactory.createDescriptionLabel("playerTwoLoginUsernameLabel", "Username:"));
			playerTwoLoginRegisterPane.add(compFactory.createDescriptionLabel("playerTwoRegisterUsernameLabel", "Username:"));
																		
			playerTwoLoginRegisterPane.add(compFactory.createTextField("playerTwoLoginUsernameTf"));
			playerTwoLoginRegisterPane.add(compFactory.createTextField("playerTwoRegisterUsernameTf"));
			
			playerTwoLoginRegisterPane.add(compFactory.createDescriptionLabel("playerTwoLoginPasswordLabel", "Password:"));
			playerTwoLoginRegisterPane.add(compFactory.createDescriptionLabel("playerTwoRegisterPasswordLabel", "Password:"));
			
			playerTwoLoginRegisterPane.add(compFactory.createPasswordField("playerTwoLoginPasswordPf"));
			playerTwoLoginRegisterPane.add(compFactory.createPasswordField("playerTwoRegisterPasswordPf"));
			
			JButton playerTwoLoginBtn = compFactory.createButton("playerTwoLoginBtn", "Login");
			playerTwoLoginBtn.setActionCommand("playerTwoLoginBtn");
			playerTwoLoginBtn.addActionListener(listener);
			playerTwoLoginRegisterPane.add(playerTwoLoginBtn);
			
			JButton playerTwoRegisterBtn = compFactory.createButton("playerTwoRegisterBtn", "Register");
			playerTwoRegisterBtn.setActionCommand("playerTwoRegisterBtn");
			playerTwoRegisterBtn.addActionListener(listener);
			playerTwoLoginRegisterPane.add(playerTwoRegisterBtn);
			
			add(playerTwoLoginRegisterPane, BorderLayout.CENTER);
			
			add(playerTwoBackBtn, BorderLayout.PAGE_END);
		}
		else {
			setLayout(new GridLayout(5, 1, spaceBetween, spaceBetween));
			
			add(Box.createVerticalGlue());
			
			add(compFactory.createDescriptionLabel("playerTwoLoggedInInfo", 
					"The user " + Game.getInstance().getPlayer2().getUsername() + " is logged in as player two."));

			JButton playerTwoLogoutBtn = compFactory.createButton("playerTwoLogoutBtn", "Logout");
			playerTwoLogoutBtn.setActionCommand("playerTwoLogoutBtn");
			playerTwoLogoutBtn.addActionListener(listener);
			add(playerTwoLogoutBtn);			
			
			add(Box.createVerticalGlue());
			
			add(playerTwoBackBtn);
		}
		
		return this;
	}
}
