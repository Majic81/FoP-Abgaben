package life.automaton.rules;

public class HighlifeRules implements UpdateRules {

	@Override
	public boolean getNextCellState(boolean isAlive, int aliveNeighbors) {
		// TODO Auto-generated method stub				
		return ((isAlive && (aliveNeighbors == 2 || aliveNeighbors == 3)) || (!isAlive && (aliveNeighbors == 3 || aliveNeighbors == 6 ))) ? true : false;
	}

}
