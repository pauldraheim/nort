package ch.bbw.controller;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

public class NortComponentResizedListener implements ComponentListener {

	@Override
	public void componentResized(ComponentEvent e) {
		
		for (Component c : Starter.getFrame().getContentPane().getComponents()) {
			if (c instanceof JPanel) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					resize(c2);
				}
			} else {
				resize(c);
			}
		}
	}
	
	public void resize(Component c) {
		int dividend = 0;
		
		if (c.getFont().getSize() > 
			(Starter.getFrame().getWidth() - Starter.getFrame().getInsets().left * 2) / 30) {
			dividend = 20;
		}
		else {
			dividend = 40;
		}

		c.setFont(new Font("Century Gothic", 0, (Starter.getFrame().getWidth() - Starter.getFrame().getInsets().left * 2) / dividend));
	}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}
}
