package ch.bbw.controller;

import java.awt.Component;

import javax.swing.JPanel;

import ch.bbw.controller.enums.NortGameCell;
import ch.bbw.controller.enums.NortGameSituation;
import ch.bbw.controller.interfaces.ComponentInteractor;
import ch.bbw.model.Player;
import ch.bbw.view.NortFrame;

/**
 * Contains all methods executed in a game of Nort
 * @author 5ia16padraheim
 */
public class NortGame {
	
	/**
	 * Checks the current situation of the board and returns said NortGameSituation
	 * @return the current NortGameSituation of the game
	 */
	public NortGameSituation getSituation() {
		NortGameCell player1NextCell = getNextCell(NortGameLoop.getInstance().getPlayer1());
		NortGameCell player2NextCell = getNextCell(NortGameLoop.getInstance().getPlayer2());

		boolean hasPlayer1Lost = false;
		boolean hasPlayer2Lost = false;

		if (player1NextCell == null || player1NextCell.equals(NortGameCell.PLAYER1_TRAIL)
				|| player1NextCell.equals(NortGameCell.PLAYER2_TRAIL) || player1NextCell.equals(NortGameCell.PLAYER2)) {
			hasPlayer1Lost = true;
		}
		if (player2NextCell == null || player2NextCell.equals(NortGameCell.PLAYER1_TRAIL)
				|| player2NextCell.equals(NortGameCell.PLAYER2_TRAIL) || player2NextCell.equals(NortGameCell.PLAYER1)) {
			hasPlayer2Lost = true;
		}

		if (hasPlayer1Lost && hasPlayer2Lost) {
			return NortGameSituation.DRAW;
		} else if (hasPlayer1Lost) {
			return NortGameSituation.PLAYER2_WIN;
		} else if (hasPlayer2Lost) {
			return NortGameSituation.PLAYER1_WIN;
		} else {
			return NortGameSituation.RUNNING;
		}
	}

	/**
	 * Advances a player to the next cell in the direction hes looking
	 * @param player The player that is being advanced
	 */
	public void advancePlayer(Player player) {
		NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
		
		int y = player.getCharY();
		int x = player.getCharX();
		
		board[y][x] = player.getPlayerTrail();

		if (y + 1 < board.length && y - 1 > -1 && x + 1 < board[0].length && x - 1 > -1) {
			switch (player.getDirection()) {
				case LEFT:
					board[y][x - 1] = player.getPlayerType();
					
					player.setCharX(x - 1);
	
					break;
				case RIGHT:
					board[y][x + 1] = player.getPlayerType();
					
					player.setCharX(x + 1);
	
					break;
				case UP:
					board[y - 1][x] = player.getPlayerType();
					
					player.setCharY(y - 1);
	
					break;
				case DOWN:
					board[y + 1][x] = player.getPlayerType();
					
					player.setCharY(y + 1);
	
					break;
			}
		}
	}
	
	/**
	 * Draws the current state of the Nort game
	 */
	public void drawBoard() {
		for (Component c : NortFrame.getInstance().getContentPane().getComponents()) {
			if (c.getName().equals("nortPane")) {
				((JPanel) c).paintComponents(NortFrame.getInstance().getGraphics());
			}
		}
	}
	
	/**
	 * Executes everything that has to happen a round ends
	 */
	public void endRound(NortGameSituation situation) {
		Player player1 = NortGameLoop.getInstance().getPlayer1();
		Player player2 = NortGameLoop.getInstance().getPlayer2();
		
		player1.setPlayerReady(false);
		player2.setPlayerReady(false);

		NortGameLoop.getInstance().setRoundRunning(false);

		if (situation.equals(NortGameSituation.PLAYER1_WIN)) {
			player1.setCurrRoundWins(player1.getCurrRoundWins() + 1);
		} else if (situation.equals(NortGameSituation.PLAYER2_WIN)) {
			player2.setCurrRoundWins(player2.getCurrRoundWins() + 1);
		}
		
		ComponentInteractor compInteractor = Starter.getInstance().getComponentInteractor();
		
		if (NortGameLoop.getInstance().getRoundsForWin() == player1.getCurrRoundWins()) {
			NortGameLoop.getInstance().setGameRunning(false);
			
			compInteractor.setPlayer1RoundsWonText(player1.getCurrRoundWins());
			
			compInteractor.setPlayer1ReadyText("WON");
			compInteractor.setPlayer2ReadyText("LOST");
			
			player1.getUser().setGameWins(player1.getUser().getGameWins() + 1);
		}
		else if (NortGameLoop.getInstance().getRoundsForWin() == player2.getCurrRoundWins()) {
			NortGameLoop.getInstance().setGameRunning(false);

			compInteractor.setPlayer2RoundsWonText(player2.getCurrRoundWins());
			
			compInteractor.setPlayer1ReadyText("LOST");
			compInteractor.setPlayer2ReadyText("WON");

			player2.getUser().setGameWins(player2.getUser().getGameWins() + 1);
		}
		else {
			compInteractor.setPlayer1RoundsWonText(player1.getCurrRoundWins());
			compInteractor.setPlayer2RoundsWonText(player2.getCurrRoundWins());
			
			compInteractor.setPlayer1ReadyText("NOT READY (UP)");
			compInteractor.setPlayer2ReadyText("(UP) NOT READY");
		}
	}

	private NortGameCell getNextCell(Player player) {
		if (player.getCharY() + 1 < NortGameLoop.getInstance().getBoard().length && player.getCharY() - 1 > -1 
				&& player.getCharX() + 1 < NortGameLoop.getInstance().getBoard()[0].length && player.getCharX() - 1 > -1) {
			switch (player.getDirection()) {
				case LEFT:
					return NortGameLoop.getInstance().getBoard()[player.getCharY()][player.getCharX() - 1];
				case RIGHT:
					return NortGameLoop.getInstance().getBoard()[player.getCharY()][player.getCharX() + 1];
				case UP:
					return NortGameLoop.getInstance().getBoard()[player.getCharY() - 1][player.getCharX()];
				case DOWN:
					return NortGameLoop.getInstance().getBoard()[player.getCharY() + 1][player.getCharX()];
			}
		}

		return null;
	}
}