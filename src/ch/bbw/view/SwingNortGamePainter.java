package ch.bbw.view;

import java.awt.Component;

import javax.swing.JPanel;

import ch.bbw.view.interfaces.NortGamePainter;

/**
 * Responsible for drawing the board in a swing application
 * @author 5ia16padraheim
 */
public class SwingNortGamePainter implements NortGamePainter {

	@Override
	public void drawBoard() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("nortPane")) {
				((JPanel) c).paintComponents(NortFrame.getInstance().getGraphics());
			}
		}
	}
}