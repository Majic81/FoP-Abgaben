package stone;

import main.TetrisGame;
import misc.Vector;
import robot.SquareRobot;

public abstract class Stone {

	private int rotationCounter = 0;

	public int getRotationCounter() {
		return rotationCounter;
	}

	public void incrementRotationCounter() {
		rotationCounter = (rotationCounter + 1) % 4;
	}

	///////////////
	// EXERCISES //
	///////////////

	/**
	 * Tries to rotate all square robots of this stone to the left.
	 * @return if the rotation was successful
	 */
	public boolean rotateAllLeft() {
		// TODO Exercise H2.2
		for (SquareRobot squareRobot : getSquareRobots()) {
			if (!squareRobot.canMove(squareRobot.getRotationVectors()[getRotationCounter()])) {
				return false;
			}
		}
		for (SquareRobot squareRobot : getSquareRobots()) {
			squareRobot.rotateLeft();
		}

		incrementRotationCounter();

		System.out.println(getRotationCounter());

		return true;
	}

	/**
	 * Handles the given key input.
	 * 
	 * @param key the key input
	 */
	public void handleKeyInput(byte key) {
		// TODO Exercise H3

		switch (key) {
		case 0:
			rotateAllLeft();
			break;
		case 1:
			moveAllLeft();
			break;
		case 2:
			moveAllDown();
			break;
		case 3:
			moveAllRight();
			break;
		case 4:
			while (moveAllDown()) {
				moveAllDown();
			}
			break;
		default:
			;
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// //


	private final TetrisGame game;

	private SquareRobot[] robots;

	/**
	 * Constructs a {@link Stone}.
	 * 
	 * @param game the game using this stone
	 */

	public Stone(TetrisGame game) {
		this.game = game;
	}


	/**
	 * Returns the game using this stone.
	 * 
	 * @return the game using this stone
	 */


	public TetrisGame getRelatedGame() {
		return game;
	}




	/**
	 * Returns the square robot array of this stone.
	 * 
	 * @return the square robot array of this stone
	 */
	public SquareRobot[] getSquareRobots() {
		return robots;
	}

	/**
	 * Tries to move all square robots of this stone by the given vector.
	 * 
	 * @param vector the vector
	 * @return if the movement was successful
	 */
	public boolean moveAll(Vector vector) {
		for (SquareRobot square : robots)
			if (!square.canMove(vector))
				return false;
		for (SquareRobot square : robots)
			square.move(vector);
		return true;
	}

	/**
	 * Tries to move down all square robots of this stone.
	 * 
	 * @return if the movement was successful
	 */
	public boolean moveAllDown() {
		return moveAll(Vector.of(0, -1));
	}

	/**
	 * Tries to move left all square robots of this stone.
	 * 
	 * @return if the movement was successful
	 */
	public boolean moveAllLeft() {
		return moveAll(Vector.of(-1, 0));
	}

	/**
	 * Tries to move right all square robots of this stone.
	 * 
	 * @return if the movement was successful
	 */
	public boolean moveAllRight() {
		return moveAll(Vector.of(1, 0));
	}

	/**
	 * Sets the array of square robots for this stone.
	 * 
	 * @param if the array of square robots
	 */
	public void setSquareRobots(SquareRobot... robots) {
		this.robots = robots;
	}

}
