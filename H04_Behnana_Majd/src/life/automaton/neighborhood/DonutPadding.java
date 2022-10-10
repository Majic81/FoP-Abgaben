package life.automaton.neighborhood;

import life.automaton.state.AutomatonState;

public class DonutPadding implements Padding {

	@Override
	public boolean isAlive(AutomatonState state, int row, int col) {
		// TODO Auto-generated method stub
		return state.isAlive(Math.floorMod(row, state.getHeight()), Math.floorMod(col, state.getWidth()));
	}

}
