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
 * The JPanel that the registering process occurs in
 * @author 5ia16padraheim
 */
public class RegisterPane extends JPanel {

	/**
	 * Initializes all components of the RegisterPane and returns the instance of the initialized RegisterPane
	 * @return The initialized RegisterPane
	 */
	public RegisterPane initGui() {
		Insets insets = Starter.getInstance().getNortFrame().getInsets();
		
		int spaceBetween = (Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(7, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("registerPane");
		setSize(Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right,
				Starter.getInstance().getNortFrame().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = Starter.getInstance().getNortComponentFactory();
		
		add(compFactory.createTitleLabel("registerWelcomeLabel", "Welcome to Nort! - Register"));
		
		add(compFactory.createDescriptionLabel("registerInfoLabel", "Please enter your desired username and password"));
		
		add(compFactory.createDescriptionLabel("registerUsernameLabel", "Username:"));
		add(compFactory.createTextField("registerUsernameTf"));
		
		add(compFactory.createDescriptionLabel("registerPasswordLabel", "Password:"));
		add(compFactory.createPasswordField("registerPasswordPf"));
		
		JPanel registerBtnPanel = new JPanel();
		registerBtnPanel.setName("registerBtnPanel");
		registerBtnPanel.setBackground(Color.BLACK);
		registerBtnPanel.setLayout(new GridLayout(1, 3, spaceBetween * 10, spaceBetween * 10));
		
		NortListener listener = new NortListener();
		
		JButton registerQuitBtn = compFactory.createButton("registerQuitBtn", "Quit");
		registerQuitBtn.setActionCommand("registerQuitBtn");
		registerQuitBtn.addActionListener(listener);
		
		JButton goToLoginBtn = compFactory.createButton("goToLoginBtn", "Login");
		goToLoginBtn.setActionCommand("goToLoginBtn");
		goToLoginBtn.addActionListener(listener);
		
		JButton registerBtn = compFactory.createButton("registerBtn", "Register");
		registerBtn.setActionCommand("registerBtn");
		registerBtn.addActionListener(listener);
		
		registerBtnPanel.add(registerQuitBtn);
		registerBtnPanel.add(goToLoginBtn);
		registerBtnPanel.add(registerBtn);
		
		add(registerBtnPanel);
		
		return this;
	}
}
