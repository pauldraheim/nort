package ch.bbw.controller;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bbw.controller.interfaces.ComponentSetterGetter;
import ch.bbw.view.NortFrame;

/**
 * Implements ComponentValueGetter and contains functionality for Swing
 * components
 * 
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
	public String getPlayerTwoLoginUsername() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("playerTwoLoginRegisterPane")) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2.getName().equals("playerTwoLoginUsernameTf")) {
						return ((JTextField) c2).getText();
					}
				}
			}
		}

		return null;
	}

	@Override
	public String getPlayerTwoLoginPassword() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("playerTwoLoginRegisterPane")) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2.getName().equals("playerTwoLoginPasswordPf")) {
						return new String(((JPasswordField) c2).getPassword());
					}
				}
			}
		}

		return null;
	}

	@Override
	public String getPlayerTwoRegisterUsername() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("playerTwoLoginRegisterPane")) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2.getName().equals("playerTwoRegisterUsernameTf")) {
						return ((JTextField) c2).getText();
					}
				}
			}
		}

		return null;
	}

	@Override
	public String getPlayerTwoRegisterPassword() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("playerTwoLoginRegisterPane")) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2.getName().equals("playerTwoRegisterPasswordPf")) {
						return new String(((JPasswordField) c2).getPassword());
					}
				}
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

	@Override
	public void setPlayerTwoLoginInfoText(String text) {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("playerTwoLoginRegisterPane")) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2.getName().equals("playerTwoLoginInfoLabel")) {
						((JLabel) c2).setText(text);
					}
				}
			}
		}
	}

	@Override
	public void setPlayerTwoRegisterInfoText(String text) {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("playerTwoLoginRegisterPane")) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2.getName().equals("playerTwoRegisterInfoLabel")) {
						((JLabel) c2).setText(text);
					}
				}
			}
		}
	}
}
