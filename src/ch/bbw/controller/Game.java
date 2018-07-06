package ch.bbw.controller;

import ch.bbw.controller.enums.Direction;
import ch.bbw.controller.enums.GameCell;
import ch.bbw.controller.enums.GameSituation;
import ch.bbw.controller.interfaces.ComponentSetterGetter;
import ch.bbw.model.User;
import ch.bbw.view.SwingNortGamePainter;
import ch.bbw.view.interfaces.NortGamePainter;

public class Game extends Thread {

	private static Game game;

	private static final int MAX_FPS = 7;

	private User player1;

	private User player2;

	private boolean isPlayer1Ready;

	private boolean isPlayer2Ready;

	private Direction player1Direction;

	private Direction player2Direction;

	private int player1RoundWins;

	private int player2RoundWins;

	private int roundsForWin;

	private boolean isGameRunning;
	
	private boolean isRoundRunning;

	private GameCell[][] board;

	private Game() {
	}

	@Override
	public void run() {
		isRoundRunning = true;

		player1RoundWins = 0;
		player2RoundWins = 0;

		long startTime;
		long targetTime;

		while (isGameRunning) {

			board = new GameCell[34][56];

			for (int y = 0; y < board.length; y++) {
				for (int x = 0; x < board[0].length; x++) {
					board[y][x] = GameCell.EMPTY;
				}
			}

			board[17][4] = GameCell.PLAYER1;
			board[17][51] = GameCell.PLAYER2;
			
			player1Direction = Direction.RIGHT;
			player2Direction = Direction.LEFT;
			
			while (isRoundRunning) {
				targetTime = 1000 / MAX_FPS;

				startTime = System.nanoTime();

				advancePlayer(player1Direction, GameCell.PLAYER1, GameCell.PLAYER1_TRAIL);
				advancePlayer(player2Direction, GameCell.PLAYER2, GameCell.PLAYER2_TRAIL);

				NortGamePainter painter = new SwingNortGamePainter();
				painter.drawBoard();

				long timeMillis = (System.nanoTime() - startTime) / 1000000;
				long waitTime = targetTime - timeMillis;

				try {
					if (waitTime > 0) {
						Thread.sleep(waitTime);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				GameSituation situation = isRoundOver();
				
				if (!situation.equals(GameSituation.RUNNING)) {
					isPlayer1Ready = false;
					isPlayer2Ready = false;

					isRoundRunning = false;
					
					if (situation.equals(GameSituation.PLAYER1_WIN)) {
						player1RoundWins++;
					} else if (situation.equals(GameSituation.PLAYER2_WIN)) {
						player2RoundWins++;
					}
					
					ComponentSetterGetter compSetterGetter = new SwingComponentSetterGetter();
					
					if (roundsForWin == player1RoundWins) {
						isGameRunning = false;
						
						compSetterGetter.setPlayer1RoundsWonText(player1RoundWins);
						
						compSetterGetter.setPlayer1ReadyText("WON");
						compSetterGetter.setPlayer2ReadyText("LOST");
						
						getPlayer1().setGameWins(getPlayer1().getGameWins() + 1);
					}
					else if (roundsForWin == player2RoundWins) {
						isGameRunning = false;
						
						compSetterGetter.setPlayer2RoundsWonText(player2RoundWins);
						
						compSetterGetter.setPlayer1ReadyText("LOST");
						compSetterGetter.setPlayer2ReadyText("WON");
						
						getPlayer2().setGameWins(getPlayer2().getGameWins() + 1);
					}
					else {
						compSetterGetter.setPlayer1RoundsWonText(player1RoundWins);
						compSetterGetter.setPlayer2RoundsWonText(player2RoundWins);
						
						compSetterGetter.setPlayer1ReadyText("NOT READY (UP)");
						compSetterGetter.setPlayer2ReadyText("(UP) NOT READY");
					}
				}
			}
		}
	}

	private GameSituation isRoundOver() {
		GameCell player1NextCell = getNextCell(player1Direction, GameCell.PLAYER1);
		GameCell player2NextCell = getNextCell(player2Direction, GameCell.PLAYER2);

		boolean hasPlayer1Lost = false;
		boolean hasPlayer2Lost = false;
		
		if (player1NextCell == null || player1NextCell.equals(GameCell.PLAYER1_TRAIL) || player1NextCell.equals(GameCell.PLAYER2_TRAIL)
				 || player1NextCell.equals(GameCell.PLAYER2)) {
			hasPlayer1Lost = true;
		}
		if (player2NextCell == null || player2NextCell.equals(GameCell.PLAYER1_TRAIL) || player2NextCell.equals(GameCell.PLAYER2_TRAIL)
				 || player2NextCell.equals(GameCell.PLAYER1)) {
			hasPlayer2Lost = true;
		}
		
		if (hasPlayer1Lost && hasPlayer2Lost) {
			return GameSituation.DRAW;
		} else if (hasPlayer1Lost) {
			return GameSituation.PLAYER2_WIN;
		} else if (hasPlayer2Lost) {
			return GameSituation.PLAYER1_WIN;
		} else {
			return GameSituation.RUNNING;
		}
	}

	private GameCell getNextCell(Direction playerDirection, GameCell player) {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[y][x].equals(player)) {
					switch (playerDirection) {
					case LEFT:
						return board[y][x - 1];
					case RIGHT:
						return board[y][x + 1];
					case UP:
						return board[y - 1][x];
					case DOWN:
						return board[y + 1][x];
					}
				}
			}
		}

		return null;
	}

	private void advancePlayer(Direction playerDirection, GameCell player, GameCell playerTrail) {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[y][x].equals(player)) {
					board[y][x] = playerTrail;

					if (y + 1 < board.length && y - 1 > -1 && x + 1 < board[0].length && x - 1 > -1) {
						switch (playerDirection) {
						case LEFT:
							board[y][x - 1] = player;

							break;
						case RIGHT:
							board[y][x + 1] = player;

							break;
						case UP:
							board[y - 1][x] = player;

							break;
						case DOWN:
							board[y + 1][x] = player;

							break;
						}

						return;
					}
				}
			}
		}
	}

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
		if (game == null) {
			game = new Game();
		}
		
		return game;
	}
	
	public void reset() {
		game = new Game();
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

	public boolean isPlayer1Ready() {
		return isPlayer1Ready;
	}

	public void setPlayer1Ready(boolean isPlayer1Ready) {
		this.isPlayer1Ready = isPlayer1Ready;
	}

	public boolean isPlayer2Ready() {
		return isPlayer2Ready;
	}

	public void setPlayer2Ready(boolean isPlayer2Ready) {
		this.isPlayer2Ready = isPlayer2Ready;
	}

	public Direction getPlayer1Direction() {
		return player1Direction;
	}

	public void setPlayer1Direction(Direction player1Direction) {
		this.player1Direction = player1Direction;
	}

	public Direction getPlayer2Direction() {
		return player2Direction;
	}

	public void setPlayer2Direction(Direction player2Direction) {
		this.player2Direction = player2Direction;
	}

	public int getPlayer1RoundWins() {
		return player1RoundWins;
	}

	public void setPlayer1RoundWins(int player1RoundWins) {
		this.player1RoundWins = player1RoundWins;
	}

	public int getPlayer2RoundWins() {
		return player2RoundWins;
	}

	public void setPlayer2RoundWins(int player2RoundWins) {
		this.player2RoundWins = player2RoundWins;
	}

	public GameCell[][] getBoard() {
		return board;
	}

	public void setBoard(GameCell[][] board) {
		this.board = board;
	}
}
