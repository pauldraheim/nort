package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.bbw.controller.NortListener;
import ch.bbw.controller.Starter;
import ch.bbw.view.NortComponentFactory;

public class PlayerTwoPane extends JPanel {
	
	/**
	 * The standard constructor with the exception of it setting all components and rules of the JPanel
	 */
	public PlayerTwoPane() {
		Insets insets = Starter.getFrame().getInsets();
		
		int spaceBetween = (Starter.getFrame().getWidth() - insets.left - insets.right) / 20;
		
		setLayout(new GridLayout(7, 2, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("playerTwoPane");
		setSize(Starter.getFrame().getWidth() - insets.left - insets.right, 
				Starter.getFrame().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = new NortComponentFactory();
		
		add(compFactory.createTitleLabel("playerTwoLoginLabel", "Login"));
		add(compFactory.createTitleLabel("playerTwoRegisterLabel", "Register"));
		
		add (compFactory.createDescriptionLabel("playerTwoLoginUsernameLabel", "Username:"));
		add (compFactory.createDescriptionLabel("playerTwoRegisterUsernameLabel", "Username:"));
		
		add (compFactory.createTextField("playerTwoLoginUsernameTf"));
		add (compFactory.createTextField("playerTwoRegisterUsernameTf"));
		
		add (compFactory.createDescriptionLabel("playerTwoLoginPasswordLabel", "Password:"));
		add (compFactory.createDescriptionLabel("playerTwoRegisterPasswordLabel", "Password:"));
		
		add (compFactory.createPasswordField("playerTwoLoginPasswordPf"));
		add (compFactory.createPasswordField("playerTwoRegisterPasswordPf"));
		
		NortListener listener = new NortListener();
		
		JButton playerTwoLoginBtn = compFactory.createButton("playerTwoLoginBtn", "Login");
		playerTwoLoginBtn.addActionListener(listener);
		add(playerTwoLoginBtn);
		
		JButton playerTwoRegisterBtn = compFactory.createButton("playerTwoRegisterBtn", "Register");
		playerTwoRegisterBtn.addActionListener(listener);
		add(playerTwoRegisterBtn);
		
		JButton playerTwoBackBtn = compFactory.createButton("playerTwoBackBtn", "Back");
		playerTwoBackBtn.addActionListener(listener);
		add(playerTwoBackBtn);
	}
}
