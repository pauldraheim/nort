package ch.bbw.controller;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import ch.bbw.controller.interfaces.ComponentSetterGetter;
import ch.bbw.view.NortFrame;

/**
 * Implements ComponentValueGetter and contains functionality for Swing components
 * @author 5ia16padraheim
 */
public class SwingComponentSetterGetter implements ComponentSetterGetter {

	@Override
	public String getLoginUsername() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("loginUsernameTf")) {
				return ((JTextField) c).getText();
			}
		}
		
		return null;
	}

	@Override
	public String getLoginPassword() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("loginPasswordPf")) {
				return new String(((JPasswordField) c).getPassword());
			}
		}
		
		return null;
	}

	@Override
	public String getRegisterUsername() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("registerUsernameTf")) {
				return ((JTextField) c).getText();
			}
		}
		
		return null;
	}

	@Override
	public String getRegisterPassword() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("registerPasswordPf")) {
				return new String(((JPasswordField) c).getPassword());
			}
		}
		
		return null;
	}

	@Override
	public void setLoginInfoText(String text) {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("loginInfoLabel")) {
				((JLabel) c).setText(text);
			}
		}
	}

	@Override
	public void setRegisterInfoText(String text) {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("registerInfoLabel")) {
				((JLabel) c).setText(text);
			}
		}
	}
}
