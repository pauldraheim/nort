package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.bbw.controller.NortListener;
import ch.bbw.controller.Starter;
import ch.bbw.model.LeaderboardPlacement;
import ch.bbw.services.AbstractNortServiceProvider;
import ch.bbw.view.NortComponentFactory;
import ch.bbw.view.NortFrame;

/**
 * The JPanel containing the Leaderboards
 * @author 5ia16padraheim
 */
public class LeaderboardsPane extends JPanel {
	
	/**
	 * Initializes all components of the LeaderboardsPane and returns the instance of the initialized LeaderboardsPane
	 * @return The initialized LeaderboardsPane
	 */
	public LeaderboardsPane initGui() {
		Insets insets = Starter.getInstance().getNortFrame().getInsets();
		
		int spaceBetween = (Starter.getInstance().getNortFrame().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(3, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("leaderboardPane");
		setSize(NortFrame.getInstance().getWidth() - insets.left - insets.right, 
				NortFrame.getInstance().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = Starter.getInstance().getNortComponentFactory();
		
		add(compFactory.createTitleLabel("leaderboardTitleLabel", "Leaderboard"));
		
		String[] columnNames = {"Place", "User", "Game wins", "Round wins"};
		
		List<LeaderboardPlacement> leaderboards = AbstractNortServiceProvider.getInstance().getUserService().getLeaderboards();
		
		Object[][] rowData = new Object[leaderboards.size()][columnNames.length];
		
		int row = 0;
		
		for (LeaderboardPlacement leaderboardPlacement : leaderboards) {
			rowData[row][0] = leaderboardPlacement.getPlacement() + ".";
			rowData[row][1] = leaderboardPlacement.getUser().getUsername();
			rowData[row][2] = leaderboardPlacement.getUser().getGameWins();
			rowData[row][3] = leaderboardPlacement.getUser().getRoundWins();
					
			row++;
		}
		
		JTable leaderboardTable = compFactory.createTable(rowData, columnNames, "leaderboardTable");
		
		add(new JScrollPane(leaderboardTable));
		
		JPanel leaderboardsBtnPanel = new JPanel();
		leaderboardsBtnPanel.setName("leaderboardsBtnPanel");
		leaderboardsBtnPanel.setBackground(Color.BLACK);
		leaderboardsBtnPanel.setLayout(new GridLayout(2, 1, spaceBetween, spaceBetween));

		leaderboardsBtnPanel.add(Box.createVerticalGlue());
		
		JButton leaderboardBackBtn = compFactory.createButton("leaderboardBackBtn", "Back");
		leaderboardBackBtn.setActionCommand("leaderboardBackBtn");
		leaderboardBackBtn.addActionListener(new NortListener());
		leaderboardsBtnPanel.add(leaderboardBackBtn);
		
		add(leaderboardsBtnPanel);
		
		return this;
	}
}
