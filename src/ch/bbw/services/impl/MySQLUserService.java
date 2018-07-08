package ch.bbw.services.impl;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ch.bbw.controller.NortGameLoop;
import ch.bbw.model.LeaderboardPlacement;
import ch.bbw.model.User;
import ch.bbw.services.UserService;

/**
 * The UserService used for MySQL operations
 * @author 5ia16padraheim
 */
public class MySQLUserService implements UserService {

	@Override
	public List<User> getAllFromDataSource() {
		List<User> users = new ArrayList<>();

		ResultSet rs = null;

		try {
			String query = "select * from user";

			rs = DatabaseConnector.getInstance().getSt().executeQuery(query);

			while (rs.next()) {
				User user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"),
						rs.getInt("gameWins"), rs.getInt("roundWins"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	@Override
	public void addToDataSource(User user) {
		PreparedStatement st = null;

		try {
			String query = "insert into user (username, password, gameWins, roundWins) values (?, ?, ?, ?)";

			st = DatabaseConnector.getInstance().getCon().prepareStatement(query);

			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setInt(3, user.getGameWins());
			st.setInt(4, user.getRoundWins());

			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User login(String username, String password) {
		User user = null;

		ResultSet rs = null;

		PreparedStatement st = null;

		try {
			String query = "select * from user where username = ? and password = ?";

			st = DatabaseConnector.getInstance().getCon().prepareStatement(query);

			st.setString(1, username);
			st.setString(2, password);

			rs = st.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"),
						rs.getInt("gameWins"), rs.getInt("roundWins"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (user != null) {
			if (NortGameLoop.getInstance().getPlayer1().getUser() == null 
					|| !user.getUsername().equals(NortGameLoop.getInstance().getPlayer1().getUser().getUsername())) {
				return user;
			} else {
				return null;
			}
		} else return user;
	}

	@Override
	public List<LeaderboardPlacement> getLeaderboards() {
		List<User> usersOrdered = new ArrayList<>();

		ResultSet rs = null;

		PreparedStatement st = null;

		try {
			String query = "select * from user order by roundWins desc";

			rs = DatabaseConnector.getInstance().getSt().executeQuery(query);

			while (rs.next()) {
				User user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"),
						rs.getInt("gameWins"), rs.getInt("roundWins"));
				usersOrdered.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<LeaderboardPlacement> leaderboards = new ArrayList<>();

		for (int i = 0; i < usersOrdered.size(); i++) {
			if (i < 3 || usersOrdered.get(i).getUsername().equals(NortGameLoop.getInstance().getPlayer1().getUser().getUsername())
					|| NortGameLoop.getInstance().getPlayer2().getUser() != null && usersOrdered.get(i).getUsername()
							.equals(NortGameLoop.getInstance().getPlayer2().getUser().getUsername())) {
				leaderboards.add(new LeaderboardPlacement(i + 1, usersOrdered.get(i)));
			}
		}

		return leaderboards;
	}

	@Override
	public void updateDatasource() {
		PreparedStatement st = null;

		try {
			User player1User = NortGameLoop.getInstance().getPlayer1().getUser();
			User player2User = NortGameLoop.getInstance().getPlayer2().getUser();

			String query = "update user set roundWins = ?, gameWins = ? where username = ?";

			st = DatabaseConnector.getInstance().getCon().prepareStatement(query);

			st.setInt(1, player1User.getRoundWins());
			st.setInt(2, player1User.getGameWins());
			st.setString(3, player1User.getUsername());

			st.executeUpdate();
			
			st.setInt(1, player2User.getRoundWins());
			st.setInt(2, player2User.getGameWins());
			st.setString(3, player2User.getUsername());

			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User register(String username, String hashedPassword) {
		boolean isUsernameTaken = false;
		
		for (User user : getAllFromDataSource()) {
			if (user.getUsername().equals(username)) {
				isUsernameTaken = true;
			}
		}

		if (!isUsernameTaken) {
			addToDataSource(new User(username, hashedPassword, 0, 0));

			for (User user : getAllFromDataSource()) {
				if (user.getUsername().equals(username)) {
					return user;
				}
			}
		}

		return null;
	}
}
