package robot;

import fopbot.Robot;
import misc.Vector;
import stone.Stone;

public class SquareRobot extends Robot {

	///////////////
	// EXERCISES //
	///////////////

	/**
	 * Returns an array containing all possible movements of this square robot.
	 * @return an array containing all possible movements of this square robot
	 */



	public Vector[] getPossibleMovements() {
		// TODO Exercise H1.1
		boolean test = true;
		int width = getRelatedStone().getRelatedGame().getWidth();
		int height = getRelatedStone().getRelatedGame().getHeight();
		Stone[] stoneArray = getRelatedStone().getRelatedGame().getStoneArray();
		int blockCount = 0;
		Vector[][] vectorsMatrix = new Vector[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				test = true;
				for (Stone testStone : stoneArray) {
					if (testStone != null) {
						for (SquareRobot testSquareRobot : testStone.getSquareRobots()) {
							if (testSquareRobot.getX() == i && testSquareRobot.getY() == j
									&& testSquareRobot.isTurnedOn()
									&& !testSquareRobot.getRelatedStone().equals(getRelatedStone())) {
								test = false;
								blockCount++;
								vectorsMatrix[i][j] = Vector.of(0, 0);
								break;
							}
						}
						if (test == false)
							break;
					}
				}
			}
		}
		Vector[] vectors = new Vector[width * height - blockCount];
		int z = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (vectorsMatrix[i][j] == null && !(i == getX() && j == getY())) {
					vectors[z] = Vector.of(i - getX(), j - getY());
					z++;
				}
			}
		}
		return vectors;
	}



	/**
	 * Returns if this square robot can be moved by the given vector.
	 * 
	 * @param vector the vector
	 * @return if this square robot can be move by the given vector
	 */
	public boolean canMove(Vector vector) {
		// TODO Exercise H1.2
		if (vector.getX() == 0 && vector.getY() == 0)
			return true;
		for (Vector vector_ : getPossibleMovements()) {
			if (vector_ != null) {
				if (vector_.getX() == vector.getX() && vector_.getY() == vector.getY())
					return true;
			}
		}
		return false;
	}

	/**
	 * Rotates this square robot to the left.
	 */
	public void rotateLeft() {
		// TODO Exercise H2.1
		setX(getX() + getRotationVectors()[getRelatedStone().getRotationCounter()].x);
		setY(getY() + getRotationVectors()[getRelatedStone().getRotationCounter()].y);
		turnLeft();
	}

	// ----------------------------------------------------------------------------------------------------
	// //

	private int turnOffIteration = -1;
	private final Stone relatedStone;
	private final Vector[] rotationVectors;

	/**
	 * Constructs a {@link SquareRobot}.
	 * 
	 * @param stone           the related stone
	 * @param x               the x coordinate
	 * @param y               the y coordinate
	 * @param color           the color
	 * @param rotationVectors the rotation vectors
	 */
	public SquareRobot(Stone stone, int x, int y, SquareColor color, Vector... rotationVectors) {
		super(x, y);
		this.relatedStone = stone;
		this.rotationVectors = rotationVectors;
		setImageId(color.toString());
	}

	/**
	 * Returns the related stone of this square robot.
	 * 
	 * @return the related stone of this square robot
	 */
	public Stone getRelatedStone() {
		return relatedStone;
	}

	/**
	 * Returns the rotation vector array of this square robot.
	 * 
	 * @return the rotation vector array of this square robot.
	 */
	public Vector[] getRotationVectors() {
		return rotationVectors;
	}

	/**
	 * Returns the iteration in which this robot was turned off.<br>
	 * If the robot was not turned off, {@code -1} will be returned.
	 * 
	 * @return the iteration in which this robot was turned off or {@code -1} if
	 *         this robot was not turned off
	 */
	public int getTurnOffIteration() {
		return turnOffIteration;
	}

	/**
	 * Moves this robot by the given vector.
	 * 
	 * @param vector the vector
	 */
	public void move(Vector vector) {
		setX(getX() + vector.x);
		setY(getY() + vector.y);
	}

	/**
	 * Moves this robot by the vector (0,-1).
	 */
	public void moveDown() {
		move(Vector.of(0, -1));
	}

	/**
	 * Moves this robot by the vector (-1,0).
	 */
	public void moveLeft() {
		move(Vector.of(-1, 0));
	}

	/**
	 * Moves this robot by the vector (1, 0).
	 */
	public void moveRight() {
		move(Vector.of(1, 0));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnOff() {
		if (isTurnedOff())
			return;
		super.turnOff();
		turnOffIteration = getRelatedStone().getRelatedGame().getIteration();
	}

}
