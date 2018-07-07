package ch.bbw.services.impl;

import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import ch.bbw.controller.Game;
import ch.bbw.model.User;
import ch.bbw.services.UserService;

public class MySQLUserService implements UserService {

	@Override
	public List<Object> getAllFromDataSource() {
		List<Object> users = new ArrayList<>();

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
	public void addToDataSource(Object objectToAdd) {
		PreparedStatement st = null;

		try {
			User user = (User) objectToAdd;

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

		return user;
	}

	@Override
	public Map<Integer, User> getLeaderboards() {
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

		Map<Integer, User> leaderboards = new HashMap<>();

		for (int i = 0; i < usersOrdered.size(); i++) {
			if (i < 3 || usersOrdered.get(i).getUsername().equals(Game.getInstance().getPlayer1().getUsername())
					|| Game.getInstance().getPlayer2() != null && usersOrdered.get(i).getUsername()
							.equals(Game.getInstance().getPlayer2().getUsername())) {
				leaderboards.put(i + 1, usersOrdered.get(i));
			}
		}

		return leaderboards;
	}

	@Override
	public void updateDatasource() {
		PreparedStatement st = null;

		try {
			User user1 = Game.getInstance().getPlayer1();
			User user2 = Game.getInstance().getPlayer2();

			String query = "update user set roundWins = ?, gameWins = ? where username = ?";

			st = DatabaseConnector.getInstance().getCon().prepareStatement(query);

			st.setInt(1, user1.getRoundWins());
			st.setInt(2, user1.getGameWins());
			st.setString(3, user1.getUsername());

			st.executeUpdate();
			
			st.setInt(1, user2.getRoundWins());
			st.setInt(2, user2.getGameWins());
			st.setString(3, user2.getUsername());

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
}
