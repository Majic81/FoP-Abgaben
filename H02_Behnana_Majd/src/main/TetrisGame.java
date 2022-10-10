
package main;

import java.awt.Point;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import fopbot.Direction;
import fopbot.World;
import robot.SquareRobot;
import stone.I;
import stone.J;
import stone.L;
import stone.O;
import stone.S;
import stone.Stone;
import stone.T;
import stone.Z;

public class TetrisGame implements Runnable {

	///////////////
	// EXERCISES //
	///////////////

	public void deleteRow(int row) {

		for (Stone stone : this.getStoneArray()) {
			if (stone != null) {
				for (SquareRobot squareRobot : stone.getSquareRobots()) {
					if (squareRobot.getY() == row) {
						squareRobot.turnOff();
					}
				}
			}
		}
	}

	public boolean isUnderlineEmpty(int j) {
		if (j > 0) {
			if (count(j - 1) == 0) {
				return true;
			}
		}
		return false;
	}

	public void moveRowDown(int j) {
		while (isUnderlineEmpty(j)) {
			for (int i = 0; i < width; i++) {
				for (Stone stone : this.getStoneArray()) {
					if (stone != null) {
						for (SquareRobot squareRobot : stone.getSquareRobots()) {
							if (squareRobot.isTurnedOn() && squareRobot.getY() == j && squareRobot.getX() == i) {
								squareRobot.moveDown();
							}
						}
					}
				}
			}
			j--;
		}
	}

	public int count(int j) {
		int counter = 0;
		boolean r1 = true;
		for (int i = 0; i < width; i++) {
			r1 = true;
			for (Stone testStone : getStoneArray()) {
				r1 = true;
				if (testStone != null) {
					for (SquareRobot testSquareRobot : testStone.getSquareRobots()) {
						if (testSquareRobot.isTurnedOn() && testSquareRobot.getY() == j
								&& testSquareRobot.getX() == i) {
							counter++;
							r1 = false;
							break;
						}
					}
					if (r1 == false) {
						break;
					}
				}
			}
		}
		return counter;
	}


	/**
	 * Clears all completed rows and returns the count of rows cleared by this call.
	 * 
	 * @return the count of rows cleared by this call
	 */

	public int clearRows() {
		int count = 0;
		int deletedRows = 0;
		int[] deletedRowId = new int[height];
		int index = 0;
		for (int o = 0; o < height; o++) {
			deletedRowId[o] = -1;
		}
		for (int j = 0; j < height; j++) {
			count = 0;
			for (int i = 0; i < width; i++) {

				for (Stone testStone : getStoneArray()) {

					if (testStone != null) {
						for (SquareRobot testSquareRobot : testStone.getSquareRobots()) {
							if (testSquareRobot != null && testSquareRobot.isTurnedOn() && testSquareRobot.getY() == j
									&& testSquareRobot.getX() == i) {
								count++;
								if (count == width) {
									deletedRows++;
									deleteRow(j);
									deletedRowId[index] = j;
									index++;
								}
							}
						}
					}
				}
			}
		}
		int indexOfFirstDeletedRow = -1;
		for (int a = 0; a < height; a++) {
			if (deletedRowId[a] >= 0) {
				indexOfFirstDeletedRow = deletedRowId[a];
				break;
			}
		}
		if (indexOfFirstDeletedRow >= 0) {
			for (int u = indexOfFirstDeletedRow + 1; u < height; u++) {
				moveRowDown(u);
			}
		}
		return deletedRows;
	}

	public Direction direction(SquareRobot squareRobot) {
		if (squareRobot.isFacingDown())
			return Direction.DOWN;
		else if (squareRobot.isFacingRight())
			return Direction.RIGHT;
		else if (squareRobot.isFacingUp())
			return Direction.UP;
		else // if facing left
			return Direction.LEFT;
	}

	/**
	 * Returns the count of points scored in the given iteration.
	 * 
	 * @param iteration the iteration
	 * @return the count of points scored in the given iteration
	 */
	public int getPoints(int iteration) {
		// TODO Exercise H5

		int x = 0;
		int counter = 0;
		boolean up = true;
		boolean down = true;
		boolean right = true;
		boolean left = true;

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < height; i++) {
				for (Stone stone : getStoneArray()) {
					if (stone != null) {
						for (SquareRobot squareRobot : stone.getSquareRobots()) {
							if (squareRobot.getX() == i && squareRobot.getY() == j && squareRobot.isTurnedOff()
									&& squareRobot.getTurnOffIteration() == iteration) {
								counter++;
								if (direction(squareRobot) == Direction.UP) {
									up = up & true;
									left = false;
									right = false;
									down = false;
								}
								else if (direction(squareRobot) == Direction.LEFT) {
									left = left & true;
									right = false;
									down = false;
									up = false;
								} else if (direction(squareRobot) == Direction.DOWN) {
									down = down & true;
									up = false;
									left = false;
									right = false;
								} else // if (direction(squareRobot) == Direction.RIGHT)
								{
									right = right & true;
									left = false;
									up = false;
									down = false;
								}
							}
						}
					}
				}
			}
			if (counter == width) {
				x++;
				if (down || right || up || left) {
				}
			}
			down = true;
			right = true;
			up = true;
			left = true;
			counter = 0;
		}
		if (x > 0) {
			switch (x) {
			case 1:
				return 1000;
			default:
				return (int) (Math.pow(2, x - 1)) * 1000;
			}
		}
		return 0;
	}

	// ----------------------------------------------------------------------------------------------------
	// //

	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final Random random;

	private final int width, height;
	private final Stone[] stones;
	private int iterations = 0, rows = 0, points = 0;
	private boolean running = true;

	/**
	 * Constructs a {@link TetrisGame}.
	 * 
	 * @param width      the width of the world
	 * @param height     the height of the world
	 * @param stoneCount the count of stones
	 * @param seed       the seed of the game or {@code null} for random seed
	 */
	public TetrisGame(int width, int height, int stoneCount, Integer seed) {
		World.setSize(this.width = width, this.height = height);
		this.stones = new Stone[stoneCount];
		random = seed != null ? new Random(seed) : new Random();
	}

	/**
	 * Returns the width of the world.
	 * 
	 * @return the width of the world
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the world.
	 * 
	 * @return the height of the world
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the array containing all stones.
	 * 
	 * @return the stone array
	 */
	public Stone[] getStoneArray() {
		return stones;
	}

	/**
	 * Adds the specified number of points to the score of this game.
	 * 
	 * @param amount the amount of points
	 */
	public void addPoints(int amount) {
		this.points += amount;
	}

	/**
	 * Adds the specified amount of removed rows to the score of this game.
	 * 
	 * @param rowAmount the amount of removed rows
	 */
	public void addRows(int rowAmount) {
		this.rows += rowAmount;
	}

	/**
	 * Returns the total amount of points scored in this game.
	 * 
	 * @return the total amount of points
	 */
	public int getTotalPoints() {
		return points;
	}

	/**
	 * Returns the total amount of removed rows scored in this game.
	 * 
	 * @return the total amount of removed rows
	 */
	public int getTotalRows() {
		return rows;
	}

	/**
	 * Returns the number of the current iteration.
	 * 
	 * @return the number of the current iteration
	 */
	public int getIteration() {

		return iterations;
	}

	/**
	 * Handles the given key input.
	 * 
	 * @param key the key input
	 */
	public void handleInput(byte key) {
		executor.execute(() -> stones[iterations].handleKeyInput(key));
	}

	/**
	 * Returns whether this game is running.
	 * 
	 * @return {@code true} if this game is running or {@code false} if not
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets whether this game is running or not.
	 * 
	 * @param isRunning whether this game is running or not
	 */
	public void setRunning(boolean isRunning) {
		this.running = isRunning;
	}

	/**
	 * Returns the count of stones in this game.
	 * 
	 * @return the count of stones
	 */
	public int getStoneCount() {
		return iterations + 1;
	}

	/**
	 * Generates a stone of a randomly selected type and returns it.
	 * 
	 * @return the stone
	 */
	public Stone generateRandomStone() {
		int number = random.nextInt(7);
		switch (number) {
		case 0:
			return new I(this, getWidth() / 2, getHeight() - 1);
		case 1:
			return new J(this, getWidth() / 2, getHeight() - 1);
		case 2:
			return new L(this, getWidth() / 2, getHeight() - 1);
		case 3:
			return new O(this, getWidth() / 2, getHeight() - 1);
		case 4:
			return new S(this, getWidth() / 2, getHeight() - 1);
		case 5:
			return new T(this, getWidth() / 2, getHeight() - 1);
		default:
			return new Z(this, getWidth() / 2, getHeight() - 1);
		}
	}

	/** {@inheritDoc} **/
	@Override
	public void run() {
		while (isRunning()) {
			executor.submit(this::doStep);
			try {
				int time = 500 - getStoneCount() * 2;
				if (time < 250)
					time = 250;
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void doStep() {
		try {
			if (!isRunning())
				return;
			if (stones[iterations] == null) {
				stones[iterations] = generateRandomStone();
				return;
			}
			boolean movable = stones[iterations].moveAllDown();
			if (!movable) {
				addRows(clearRows());
				addPoints(getPoints(getIteration()));
				if (iterations + 1 >= stones.length) {
					setRunning(false);
					World.getGlobalWorld().getGuiPanel().repaint();
				} else {
					stones[++iterations] = generateRandomStone();
					if (checkCollisions()) {
						setRunning(false);
						for (SquareRobot square : stones[iterations].getSquareRobots())
							square.turnOff();
						iterations--;
					}
				}
			}
		} catch (RuntimeException exception) {
			exception.printStackTrace();
		}
	}

	private boolean checkCollisions() {
		long collisions = Arrays.stream(stones)
				// filter non-null objects
				.filter(Objects::nonNull)
				// map to square robots
				.flatMap(s -> Arrays.stream(s.getSquareRobots()))
				// filter turned on robots
				.filter(SquareRobot::isTurnedOn)
				// collect occurrences
				.collect(Collectors.groupingBy(r -> new Point(r.getX(), r.getY()), Collectors.counting()))
				// count collisions
				.values().stream().filter(c -> c > 1).count();
		if (collisions > 0)
			System.err.println("Collision -> Game Over");
		return collisions > 0;
	}

}
