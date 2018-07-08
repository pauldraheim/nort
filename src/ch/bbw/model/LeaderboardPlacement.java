package ch.bbw.model;

/**
 * Includes every info needed for a leaderboard placement
 * @author 5ia16padraheim
 *
 */
public class LeaderboardPlacement {

	private int placement;
	
	private User user;
	
	public LeaderboardPlacement(int placement, User user) {
		this.placement = placement;
		this.user = user;
	}

	public int getPlacement() {
		return placement;
	}

	public void setPlacement(int placement) {
		this.placement = placement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
