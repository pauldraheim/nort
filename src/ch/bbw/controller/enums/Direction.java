package ch.bbw.controller.enums;

/**
 * Enum containing all directions
 * @author 5ia16padraheim
 */
public enum Direction {
	LEFT,
	UP,
	RIGHT,
	DOWN;
	
	/**
	 * Returns the opposite direction
	 * @return The opposite direction
	 */
	public Direction getOppositeDirection() {
		switch(this) {
			case DOWN:
				return UP;
			case UP:
				return DOWN;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
		}
		
		return null;
	}
}
