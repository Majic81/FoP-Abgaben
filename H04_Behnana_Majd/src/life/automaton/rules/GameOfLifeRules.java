package life.automaton.rules;

public class GameOfLifeRules implements UpdateRules {

	@Override
	public boolean getNextCellState(boolean isAlive, int aliveNeighbors) {
		// TODO Auto-generated method stub
		
		return ((isAlive && (aliveNeighbors == 2 || aliveNeighbors == 3)) || (!isAlive && aliveNeighbors == 3)) ? true : false;
	}

}
