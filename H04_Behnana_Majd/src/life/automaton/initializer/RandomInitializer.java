package life.automaton.initializer;
import java.util.Random;

import life.automaton.state.ArrayAutomatonState;
import life.automaton.state.AutomatonState;
import java.util.Random;

public class RandomInitializer implements StateInitializer {

	private int height;
	private int width; 
	private double rate;

	public RandomInitializer(int height , int width , double rate) {
		this.height = height;
		this.width = width;
		this.rate = rate;
	}

	@Override
	public AutomatonState createState() {
		// TODO Auto-generated method stub
		var state = new ArrayAutomatonState(this.height, this.width);
		Random random;
		int counter = 0;
		double a = 0;
		int i1 = 0;
		int j1 = 0;
		int countLiveCells = (int) (this.width * this.height * this.rate);
		boolean continueLoop = true;

		if (this.rate == 0.0) {
			return state;
		}
		else if (this.rate == 1.0) {
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					state.giveBirth(i, j);
				}
			}
			return state;
		}
		else {	
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					if (counter < countLiveCells) {
						random = new Random();
						i1 = random.nextInt(this.height);
						j1 = random.nextInt(this.width);
						while (state.isAlive(i1, j1)) {
							random = new Random();
							i1 = random.nextInt(this.height);
							j1 = random.nextInt(this.width);
						}
						state.giveBirth(i1, j1);
						counter++;
					}	
					else {
						continueLoop = false;				
						break;
					}
				}
				if (!continueLoop) {
					break;
				}
			}
		}
		return state;
	}
}
