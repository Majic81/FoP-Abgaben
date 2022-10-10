package life.automaton.neighborhood;

import life.automaton.state.AutomatonState;

public class MooreNeighborhood implements Neighborhood {

	@Override
	public int getNumberOfAliveNeighbors(AutomatonState state, int row, int col, Padding padding) {
		// TODO Auto-generated method stub
		var result = 0;
		for (int i = -1 ; i <= 1; i++)
			for (int j = -1 ; j <= 1; j++) {
				result += padding.isAlive(state, row+i, col+j) && ((i != 0) || (j != 0)) ? 1 : 0;
			}
		return result;
	}
}
