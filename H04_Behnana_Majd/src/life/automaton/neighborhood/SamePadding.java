package life.automaton.neighborhood;

import life.automaton.state.AutomatonState;

public class SamePadding implements Padding {

	@Override
	public boolean isAlive(AutomatonState state, int row, int col) {
		// TODO Auto-generated method stub

		if ( 0 <= row && row <= state.getHeight()-1 && 0 <= col && col <= state.getWidth()-1) {
			return state.isAlive(row,col);
		}		
		else {
			if (0 <= row && row <= state.getHeight()-1) {
				if (col < 0 ) {
					return state.isAlive(row, 0);
				}
				else if (col > state.getWidth()-1) {
					return state.isAlive(row, state.getWidth()-1);
				}
			}
			
			if ( (0 <= col && col <= state.getWidth()-1)) {
				if (row < 0 ) {
					return state.isAlive(0, col);
				}
				else if (row > state.getHeight()-1) {
					return state.isAlive(state.getHeight()-1, col);
				}
			}
			
			if (state.getHeight()-1 < row && col < 0) {
				return state.isAlive(state.getHeight()-1, 0);
			}
			if (state.getHeight()-1 < row && col > state.getWidth()-1) {
				return state.isAlive(state.getHeight()-1, state.getWidth()-1 );
			}
			
			if (row < 0 && col > state.getWidth()-1) {
				return state.isAlive(0, state.getWidth()-1 );
			}
			
			if (row < 0 && col < 0) {
				return state.isAlive(0, 0);
			}					
		}
		return false;
	}
}
