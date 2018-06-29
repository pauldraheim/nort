package ch.bbw.model;

/**
 * The user model class containing id, username and password of the user
 * @author 5ia16padraheim
 */
public class User {
	
	private int id;
	
	private String username;
	
	private String password;
	
	/**
	 * Constructor that sets all values but the ID of the user
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Constructor that sets all values of a user
	 * @param id
	 * @param username
	 * @param password
	 */
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
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
}
