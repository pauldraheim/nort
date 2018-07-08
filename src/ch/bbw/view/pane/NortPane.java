package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JPanel;

import ch.bbw.controller.NortGameLoop;
import ch.bbw.controller.Starter;
import ch.bbw.controller.enums.NortGameCell;

/**
 * The JPanel which the game is drawn in
 * @author 5ia16padraheim
 */
public class NortPane extends JPanel {
	
	/**
	 * Initializes the NortPane
	 * @return The initialized NortPane
	 */
	public NortPane initGui() {
		Insets insets = Starter.getInstance().getNortFrame().getInsets();
		
		setBackground(Color.DARK_GRAY);
		setName("nortPane");
		setSize(Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right, 
				Starter.getInstance().getNortFrame().getHeight() - insets.top - insets.bottom);
		
		return this;
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
		
		Insets insets = Starter.getInstance().getNortFrame().getInsets();
		
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getX() + insets.left, getY() + insets.top, getWidth(), getHeight());

        for (int y = 0; y < board.length; y++) {
        	for (int x = 0; x < board[0].length; x++) {
        		switch(board[y][x]) {
        			case PLAYER1:
        			case PLAYER1_TRAIL:
        		        g.setColor(Color.CYAN);
        		        
        		        break;
        			case PLAYER2:
        			case PLAYER2_TRAIL:
        		        g.setColor(Color.RED);
        		        
        		        break;
    		        default:
    		        	break;
        		}
        		
                g.fillRect(x * (getWidth() / board[0].length) + getX() + insets.left, y * (getHeight() / board.length) + getY() + insets.top, 
                		getWidth() / board[0].length, getHeight() / board.length);
                g.setColor(Color.DARK_GRAY);
        	}
        }
	}
}
