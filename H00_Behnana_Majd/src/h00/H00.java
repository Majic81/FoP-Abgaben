package h00;

import static fopbot.Direction.UP;

import java.util.concurrent.ThreadLocalRandom;

import fopbot.Robot;
import fopbot.World;

public class H00 {

	/**
	 * @return a random odd integer between 3 and 9 (both inclusive)
	 */
	public static int getRandomOddWorldSize() {
		return 3 + ThreadLocalRandom.current().nextInt(4) * 2;
	}

	public static void main(String[] args) {
		int size = getRandomOddWorldSize();
		World.setSize(size, size);
		World.setDelay(200);
		World.setVisible(true);
		System.out.println("Size of world: " + size + "x" + size);
		rightRobot(size);
		leftRobot(size);
	}

	public static void rightRobot(int size) {
		// TODO implement H2.1
		Robot rightRobot = new Robot(size-1, size/2, UP, 1);
		for (int i=0; i<size/2; i++) {
			rightRobot.move();
			rightRobot.turnLeft();
			rightRobot.move();
			rightRobot.turnLeft();
			rightRobot.turnLeft();
			rightRobot.turnLeft();
		}
		rightRobot.putCoin();
		rightRobot.turnLeft();
		rightRobot.turnLeft();
		rightRobot.turnLeft();

		for (int i=0; i<size/2; i++) {
			rightRobot.move();
			rightRobot.turnLeft();
			rightRobot.turnLeft();
			rightRobot.turnLeft();
			rightRobot.move();
			rightRobot.turnLeft();
		}
		rightRobot.turnLeft();
	}

	public static void leftRobot(int size) {
		// TODO implement H2.2
			// TODO implement H2.1
		Robot leftRobot = new Robot(0, size / 2, UP, 1);
			for (int i=0; i<size/2; i++) {
			leftRobot.move();
			leftRobot.turnLeft();
			leftRobot.turnLeft();
			leftRobot.turnLeft();
			leftRobot.move();
			leftRobot.turnLeft();
			}
		leftRobot.pickCoin();
		leftRobot.turnLeft();

			for (int i=0; i<size/2; i++) {
			leftRobot.move();
			leftRobot.turnLeft();
			leftRobot.move();
			leftRobot.turnLeft();
			leftRobot.turnLeft();
			leftRobot.turnLeft();
			}
		leftRobot.putCoin();
		leftRobot.turnLeft();
		leftRobot.turnLeft();
		leftRobot.turnLeft();
		}
}
