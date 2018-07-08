package ch.bbw.model;

import ch.bbw.controller.enums.Direction;
import ch.bbw.controller.enums.NortGameCell;

/**
 * Object used for the Nortgame, a Player is always resembled by a User
 * @author 5ia16padraheim
 */
public class Player {

	private User user;
	
	private boolean isPlayerReady;
	
	private Direction direction;
	
	private int currRoundWins;
	
	private int charX;
	
	private int charY;
	
	private NortGameCell playerType;
	
	private NortGameCell playerTrail;
	
	/**
	 * Standard constructor that also sets playerType and playerTrail so the program can identify the player later
	 * @param playerType The type of player, either one or two
	 * @param playerTrail The trail the player will leave
	 */
	public Player(NortGameCell playerType, NortGameCell playerTrail) {
		this.playerType = playerType;
		this.playerTrail = playerTrail;
	}

	public NortGameCell getPlayerType() {
		return playerType;
	}

	public void setPlayerType(NortGameCell playerType) {
		this.playerType = playerType;
	}

	public NortGameCell getPlayerTrail() {
		return playerTrail;
	}

	public void setPlayerTrail(NortGameCell playerTrail) {
		this.playerTrail = playerTrail;
	}

	public int getCharX() {
		return charX;
	}

	public int getCharY() {
		return charY;
	}

	public void setCharX(int charX) {
		this.charX = charX;
	}

	public void setCharY(int charY) {
		this.charY = charY;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPlayerReady() {
		return isPlayerReady;
	}

	public void setPlayerReady(boolean isPlayerReady) {
		this.isPlayerReady = isPlayerReady;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getCurrRoundWins() {
		return currRoundWins;
	}

	public void setCurrRoundWins(int currRoundWins) {
		this.currRoundWins = currRoundWins;
	}
}