package ch.bbw.controller;

import ch.bbw.controller.enums.Direction;
import ch.bbw.controller.enums.NortGameCell;
import ch.bbw.controller.enums.NortGameSituation;
import ch.bbw.model.Player;

public class NortGameLoop extends Thread {

	private static NortGameLoop gameLoop;

	private static final int MAX_FPS = 10;
	
	private Player player1;
	
	private Player player2;

	private int roundsForWin;

	private boolean isGameRunning;
	
	private boolean isRoundRunning;

	private NortGameCell[][] board;

	private NortGameLoop() {}

	@Override
	public void run() {
		isRoundRunning = true;

		player1.setCurrRoundWins(0);
		player2.setCurrRoundWins(0);

		long startTime;
		long targetTime;

		while (isGameRunning) {

			board = new NortGameCell[34][56];

			for (int y = 0; y < board.length; y++) {
				for (int x = 0; x < board[0].length; x++) {
					board[y][x] = NortGameCell.EMPTY;
				}
			}

			board[17][4] = NortGameCell.PLAYER1;
			board[17][51] = NortGameCell.PLAYER2;
			
			player1.setCharY(17);
			player1.setCharX(4);
			
			player2.setCharY(17);
			player2.setCharX(51);
			
			player1.setDirection(Direction.RIGHT);
			player2.setDirection(Direction.LEFT);
			
			NortGame game = new NortGame();
			
			while (isRoundRunning) {
				targetTime = 1000 / MAX_FPS;

				startTime = System.nanoTime();

				game.advancePlayer(player1);
				game.advancePlayer(player2);

				game.drawBoard();
				
				NortGameSituation situation = game.getSituation();
				
				if (!situation.equals(NortGameSituation.RUNNING)) {
					game.endRound(situation);
				}

				long timeMillis = (System.nanoTime() - startTime) / 1000000;
				long waitTime = targetTime - timeMillis;

				try {
					if (waitTime > 0) {
						Thread.sleep(waitTime);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static NortGameLoop getInstance() {
		if (gameLoop == null) {
			gameLoop = new NortGameLoop();
			
			gameLoop.setPlayer1(new Player(NortGameCell.PLAYER1, NortGameCell.PLAYER1_TRAIL));
			gameLoop.setPlayer2(new Player(NortGameCell.PLAYER2, NortGameCell.PLAYER2_TRAIL));
		}
		
		return gameLoop;
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public static void reset() {
		gameLoop = new NortGameLoop();
		
		gameLoop.setPlayer1(new Player(NortGameCell.PLAYER1, NortGameCell.PLAYER1_TRAIL));
		gameLoop.setPlayer2(new Player(NortGameCell.PLAYER2, NortGameCell.PLAYER2_TRAIL));
	}

	public int getRoundsForWin() {
		return roundsForWin;
	}

	public void setRoundsForWin(int roundsForWin) {
		this.roundsForWin = roundsForWin;
	}

	public boolean isGameRunning() {
		return isGameRunning;
	}

	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	public boolean isRoundRunning() {
		return isRoundRunning;
	}

	public void setRoundRunning(boolean isRoundRunning) {
		this.isRoundRunning = isRoundRunning;
	}

	public NortGameCell[][] getBoard() {
		return board;
	}

	public void setBoard(NortGameCell[][] board) {
		this.board = board;
	}
}
