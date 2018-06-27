package ch.bbw.view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import ch.bbw.controller.NortComponentResizedListener;
import ch.bbw.view.pane.LoginPane;

/**
 * The JFrame that is used by the program
 * @author 5ia16padraheim
 */
public class NortFrame extends JFrame {

	/**
	 * The standard constructor that also sets rules and the content pane for the JFrame
	 */
	public void initGui() {
		setTitle("Nort");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponentListener(new NortComponentResizedListener());
		
		int width = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() * 0.6);
		int height = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() * 0.8);
		
		setSize(width, height);
		setMinimumSize(new Dimension((int) (width / 1.5), (int) (height / 1.5)));

		setContentPane(new LoginPane());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
