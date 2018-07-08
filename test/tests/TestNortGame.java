package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.bbw.controller.NortGame;
import ch.bbw.controller.NortGameLoop;
import ch.bbw.controller.enums.Direction;
import ch.bbw.controller.enums.NortGameCell;
import ch.bbw.controller.enums.NortGameSituation;
import ch.bbw.model.User;

/**
 * Tests the NortGame methods
 * @author 5ia16padraheim
 */
public class TestNortGame {

	private NortGame game;
	
	@Before
	public void initialize() {
		game = new NortGame();

    	NortGameLoop.getInstance().setBoard(new NortGameCell[10][10]);
	}
	
    /**
     * Tests, if GameSituation is a draw when it should be
     */
    @Test
    public void isSituationDraw() {
    	NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
        
        for (int y = 0; y < board.length; y++) {
        	for (int x = 0; x < board[0].length; x++) {
        		board[y][x] = NortGameCell.EMPTY;
        	}
        }
        
        board[4][5] = NortGameCell.PLAYER1;
        board[4][6] = NortGameCell.PLAYER2;

        NortGameLoop.getInstance().getPlayer1().setCharY(4);
        NortGameLoop.getInstance().getPlayer1().setCharX(5);
        NortGameLoop.getInstance().getPlayer2().setCharY(4);
        NortGameLoop.getInstance().getPlayer2().setCharX(6);
        
        NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
        NortGameLoop.getInstance().getPlayer2().setDirection(Direction.LEFT);
        
        NortGameSituation situation = game.getSituation();
        
        Assert.assertEquals(NortGameSituation.DRAW, situation);
    }
	
    /**
     * Tests, if GameSituation is still running when it should be
     */
    @Test
    public void isSituationRunning() {
    	NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
        
        for (int y = 0; y < board.length; y++) {
        	for (int x = 0; x < board[0].length; x++) {
        		board[y][x] = NortGameCell.EMPTY;
        	}
        }
        
        board[3][4] = NortGameCell.PLAYER1;
        board[4][6] = NortGameCell.PLAYER2;

        NortGameLoop.getInstance().getPlayer1().setCharY(3);
        NortGameLoop.getInstance().getPlayer1().setCharX(4);
        NortGameLoop.getInstance().getPlayer2().setCharY(4);
        NortGameLoop.getInstance().getPlayer2().setCharX(6);
        
        NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
        NortGameLoop.getInstance().getPlayer2().setDirection(Direction.LEFT);
        
        NortGameSituation situation = game.getSituation();
        
        Assert.assertEquals(NortGameSituation.RUNNING, situation);
    }
	
    /**
     * Tests, if GameSituation is a win for player 1 when it should be
     */
    @Test
    public void isSituationPlayer1Win() {
    	NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
        
        for (int y = 0; y < board.length; y++) {
        	for (int x = 0; x < board[0].length; x++) {
        		board[y][x] = NortGameCell.EMPTY;
        	}
        }
        
        board[3][5] = NortGameCell.PLAYER1;
        board[4][6] = NortGameCell.PLAYER2;
        board[4][5] = NortGameCell.PLAYER1_TRAIL;

        NortGameLoop.getInstance().getPlayer1().setCharY(3);
        NortGameLoop.getInstance().getPlayer1().setCharX(5);
        NortGameLoop.getInstance().getPlayer2().setCharY(4);
        NortGameLoop.getInstance().getPlayer2().setCharX(6);
        
        NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
        NortGameLoop.getInstance().getPlayer2().setDirection(Direction.LEFT);
        
        NortGameSituation situation = game.getSituation();
        
        Assert.assertEquals(NortGameSituation.PLAYER1_WIN, situation);
    }
	
    /**
     * Tests, if GameSituation is a win for player 2 when it should be
     */
    @Test
    public void isSituationPlayer2Win() {
    	NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
        
        for (int y = 0; y < board.length; y++) {
        	for (int x = 0; x < board[0].length; x++) {
        		board[y][x] = NortGameCell.EMPTY;
        	}
        }
        
        board[4][5] = NortGameCell.PLAYER1;
        board[3][6] = NortGameCell.PLAYER2;
        board[4][6] = NortGameCell.PLAYER2_TRAIL;

        NortGameLoop.getInstance().getPlayer1().setCharY(4);
        NortGameLoop.getInstance().getPlayer1().setCharX(5);
        NortGameLoop.getInstance().getPlayer2().setCharY(3);
        NortGameLoop.getInstance().getPlayer2().setCharX(6);
        
        NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
        NortGameLoop.getInstance().getPlayer2().setDirection(Direction.LEFT);
        
        NortGameSituation situation = game.getSituation();
        
        Assert.assertEquals(NortGameSituation.PLAYER2_WIN, situation);
    }
    
    /**
     * Tests, if a player has moved after executing the advancePlayer method
     */
    @Test
    public void hasPlayerMoved() {
    	NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
    	
    	for (int y = 0; y < board.length; y++) {
    		for (int x = 0; x < board[0].length; x++) {
    			board[y][x] = NortGameCell.EMPTY;
    		}
    	}
    	
    	board[1][1] = NortGameCell.PLAYER1;
    	
    	NortGameLoop.getInstance().getPlayer1().setCharX(1);
    	NortGameLoop.getInstance().getPlayer1().setCharY(1);
    	
    	NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
    	
    	game.advancePlayer(NortGameLoop.getInstance().getPlayer1());
    	
    	Assert.assertEquals(NortGameCell.PLAYER1, board[1][2]);
    }
    
    /**
     * Tests, if the players aren't ready after the round supposedly ended
     */
    @Test
    public void arePlayersNotReadyAfterEndRound() {
    	User user1 = new User("peter", "password", 3, 3);
    	User user2 = new User("hans", "password", 3, 3);
    	
    	NortGameLoop.getInstance().getPlayer1().setUser(user1);
    	NortGameLoop.getInstance().getPlayer2().setUser(user2);
    	
    	game.endRound(NortGameSituation.DRAW);
    	
    	Assert.assertEquals(false, NortGameLoop.getInstance().getPlayer1().isPlayerReady());
    	Assert.assertEquals(false, NortGameLoop.getInstance().getPlayer2().isPlayerReady());
    }
    
    /**
     * Tests, if the nextCell method returns a trail when it is
     */
    @Test
    public void isNextCellTrail() {
    	NortGameCell[][] board = NortGameLoop.getInstance().getBoard();
        
        for (int y = 0; y < board.length; y++) {
        	for (int x = 0; x < board[0].length; x++) {
        		board[y][x] = NortGameCell.EMPTY;
        	}
        }
        
        board[3][4] = NortGameCell.PLAYER1;
        board[3][5] = NortGameCell.PLAYER1_TRAIL;

        NortGameLoop.getInstance().getPlayer1().setCharY(3);
        NortGameLoop.getInstance().getPlayer1().setCharX(4);
        
        NortGameLoop.getInstance().getPlayer1().setDirection(Direction.RIGHT);
        
        NortGameCell nextCell = game.getNextCell(NortGameLoop.getInstance().getPlayer1());
        
        Assert.assertEquals(NortGameCell.PLAYER1_TRAIL, nextCell);
    }
}
