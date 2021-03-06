package ch.bbw.view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import ch.bbw.view.pane.LoginPane;

/**
 * The JFrame that is used by the program
 * @author 5ia16padraheim
 */
public class NortFrame extends JFrame {
	
	private static NortFrame nortFrame;

	private NortFrame() {}
	
	/**
	 * The standard constructor that also sets rules and the content pane for the JFrame
	 */
	public void initGui() {
		setTitle("Nort");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addComponentListener(new SwingNortComponentResizedListener());
		
		int width = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() * 0.6);
		int height = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() * 0.8);
		
		setSize(width, height);
		setMinimumSize(new Dimension((int) (width / 1.5), (int) (height / 1.5)));

		setContentPane(new LoginPane().initGui());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Returns the only instance of the NortFrame class and initiates it if it hasn't been yet
	 * @return The only instance of the NortFrame class
	 */
	public static NortFrame getInstance() {
		if (nortFrame == null) {
			nortFrame = new NortFrame();
		}
		
		return nortFrame;
	}
}
