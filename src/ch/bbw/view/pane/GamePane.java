package ch.bbw.view.pane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.bbw.controller.NortListener;
import ch.bbw.model.User;
import ch.bbw.services.AbstractNortServiceProvider;
import ch.bbw.view.NortComponentFactory;
import ch.bbw.view.NortFrame;

/**
 * The JPanel containing the Leaderboard
 * @author 5ia16padraheim
 */
public class GamePane extends JPanel {
	
	/**
	 * Initializes all components of the LeaderboardPane and returns the instance of the initialized LeaderboardPane
	 * @return The initialized LeaderboardPane
	 */
	public GamePane initGui() {
		Insets insets = NortFrame.getInstance().getInsets();
		
		int spaceBetween = (NortFrame.getInstance().getWidth() - insets.left - insets.right) / 200;
		
		setLayout(new GridLayout(3, 1, spaceBetween, spaceBetween));
		setBackground(Color.BLACK);
		setName("leaderboardPane");
		setSize(NortFrame.getInstance().getWidth() - insets.left - insets.right, 
				NortFrame.getInstance().getHeight() - insets.top - insets.bottom);
		
		NortComponentFactory compFactory = NortComponentFactory.getInstance();
		
		add(compFactory.createTitleLabel("leaderboardTitleLabel", "Leaderboard"));
		
		String[] columnNames = {"Place", "User", "Game wins", "Round wins"};
		
		Map<Integer, User> leaderboards = AbstractNortServiceProvider.getInstance().getUserService().getLeaderboards();
		
		Object[][] rowData = new Object[leaderboards.size()][columnNames.length];
		
		int row = 0;
		
		for (Entry<Integer, User> entry : leaderboards.entrySet()) {
			rowData[row][0] = entry.getKey() + ".";
			rowData[row][1] = entry.getValue().getUsername();
			rowData[row][2] = entry.getValue().getGameWins();
			rowData[row][3] = entry.getValue().getRoundWins();
					
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
