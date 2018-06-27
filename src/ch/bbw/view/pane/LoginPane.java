package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.bbw.controller.NortListener;
import ch.bbw.controller.Starter;
import ch.bbw.view.NortComponentFactory;

/**
 * The JPanel that the Login process will occur in
 * @author 5ia16padraheim
 */
public class LoginPane extends JPanel {

	/**
	 * The standard constructor with the exception of it setting all components and rules of the JPanel
	 */
	public LoginPane() {
		Insets insets = Starter.getFrame().getInsets();
		
		int spaceBetween = (Starter.getFrame().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(7, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("loginPane");
		setSize(Starter.getFrame().getWidth() - insets.left - insets.right, Starter.getFrame().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = new NortComponentFactory();
		
		add(compFactory.createTitleLabel("loginWelcomeLabel", "Welcome to Nort! - Login"));
		
		add(compFactory.createDescriptionLabel("loginInfoLabel", "Please enter your username and password"));
		
		add (compFactory.createDescriptionLabel("loginUsernameLabel", "Username:"));
		add (compFactory.createTextField("loginUsernameTf"));
		
		add (compFactory.createDescriptionLabel("loginPasswordLabel", "Password:"));
		add (compFactory.createPasswordField("loginPasswordPf"));
		
		JPanel loginBtnPanel = new JPanel();
		loginBtnPanel.setName("loginBtnPanel");
		loginBtnPanel.setBackground(Color.BLACK);
		loginBtnPanel.setLayout(new GridLayout(1, 3, spaceBetween *  10, spaceBetween * 10));
		
		NortListener listener = new NortListener();
		
		JButton loginQuitBtn = compFactory.createButton("loginQuitBtn", "Quit");
		loginQuitBtn.setActionCommand("loginQuitBtn");
		loginQuitBtn.addActionListener(listener);
		
		JButton goToRegisterBtn = compFactory.createButton("goToRegisterBtn", "Register");
		goToRegisterBtn.setActionCommand("goToRegisterBtn");
		goToRegisterBtn.addActionListener(listener);
		
		JButton loginBtn = compFactory.createButton("loginBtn", "Login");
		loginBtn.setActionCommand("loginBtn");
		loginBtn.addActionListener(listener);
		
		loginBtnPanel.add(loginQuitBtn);
		loginBtnPanel.add(goToRegisterBtn);
		loginBtnPanel.add(loginBtn);
		
		add(loginBtnPanel);
	}
}
