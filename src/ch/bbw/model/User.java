package ch.bbw.model;

/**
 * The user model class containing id, username and password of the user
 * @author 5ia16padraheim
 */
public class User {
	
	private int id;
	
	private String username;
	
	private String password;
	
	private int gameWins;
	
	private int roundWins;
	
	/**
	 * Constructor that sets all values but the ID of the user
	 * @param username The username of the user
	 * @param password The hashed password of the user
	 */
	public User(String username, String password, int gameWins, int roundWins) {
		this.username = username;
		this.password = password;
		this.gameWins = gameWins;
		this.roundWins = roundWins;
	}
	
	/**
	 * Constructor that sets all values of a user
	 * @param id The ID of the user
	 * @param username The username of the user
	 * @param password The hashed password of the user
	 */
	public User(int id, String username, String password, int gameWins, int roundWins) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.gameWins = gameWins;
		this.roundWins = roundWins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGameWins() {
		return gameWins;
	}

	public void setGameWins(int gameWins) {
		this.gameWins = gameWins;
	}

	public int getRoundWins() {
		return roundWins;
	}

	public void setRoundWins(int roundWins) {
		this.roundWins = roundWins;
	}
}
