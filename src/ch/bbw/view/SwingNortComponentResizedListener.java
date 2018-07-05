package ch.bbw.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SwingNortComponentResizedListener implements ComponentListener {

	@Override
	public void componentResized(ComponentEvent e) {
		
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c instanceof JPanel) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					resize(c2);
				}
			} else if (c instanceof JScrollPane) {
				resize(((JScrollPane) c).getViewport().getView());
			} else {
				resize(c);
			}
		}
	}
	
	public void resize(Component c) {
		int dividend = 0;
		
		if (c instanceof JTable) {
			JTable table = (JTable) c;
			
			Font font = new Font("Century Gothic", 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 40);
			
			table.setFont(font);
			table.getTableHeader().setFont(font);
			table.setRowHeight((NortFrame.getInstance().getHeight() - NortFrame.getInstance().getInsets().top -
					NortFrame.getInstance().getInsets().bottom) / (3 * (table.getRowCount() + 1)));
			
			return;
		}
		else if (c.getFont().getSize() > 
			(NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 30) {
			dividend = 20;
		}
		else {
			dividend = 40;
		}

		c.setFont(new Font("Century Gothic", 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / dividend));
	}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}
}
