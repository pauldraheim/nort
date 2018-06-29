package ch.bbw.controller;

import ch.bbw.model.User;

public class Game {
	
	private static Game game = new Game();
	
	private User player1;
	
	private User player2;

	private Game() {}
	
	public User getPlayer1() {
		return player1;
	}

	public void setPlayer1(User player1) {
		this.player1 = player1;
	}

	public User getPlayer2() {
		return player2;
	}

	public void setPlayer2(User player2) {
		this.player2 = player2;
	}
	
	public static Game getInstance() {
		return game;
	}
}
