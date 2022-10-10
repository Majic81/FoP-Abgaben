package life.automaton;

import life.automaton.neighborhood.DonutPadding;
import life.automaton.neighborhood.MooreNeighborhood;
import life.automaton.neighborhood.Neighborhood;
import life.automaton.neighborhood.Padding;
import life.automaton.neighborhood.SamePadding;
import life.automaton.neighborhood.VonNeumannNeighborhood;
import life.automaton.neighborhood.ZeroPadding;
import life.automaton.rules.GameOfLifeRules;
import life.automaton.rules.HighlifeRules;
import life.automaton.rules.UpdateRules;
import life.automaton.state.AutomatonState;

public class ModularAutomaton implements CellularAutomaton {

	private AutomatonState initialState;
	private AutomatonState currentState;
	private UpdateRules rules;
	private Neighborhood neighborhood;
	private Padding padding;

	public ModularAutomaton (AutomatonState initialState, UpdateRules rules, Neighborhood neighborhood, Padding padding) {
		this.initialState = initialState;
		this.rules = rules;
		this.neighborhood = neighborhood;
		this.padding = padding;
		this.currentState = this.initialState.copy();
	}

	@Override
	public boolean isAlive(int row, int col) {
		// TODO Auto-generated method stub	
		return this.currentState.isAlive(row,col);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.currentState.getHeight();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.currentState.getWidth();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		AutomatonState newAutomatonState = this.currentState.copy();
		for (int i = 0; i < newAutomatonState.getHeight(); i++) {
			for (int j = 0; j < newAutomatonState.getWidth(); j++) {
				boolean isAlive = this.rules.getNextCellState(this.padding.isAlive(this.currentState, i, j), this.neighborhood.getNumberOfAliveNeighbors(this.currentState, i, j, this.padding));
				if (isAlive) newAutomatonState.giveBirth(i, j);
				else newAutomatonState.kill(i, j);
			}
		}
		this.currentState = newAutomatonState;
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.currentState = this.initialState.copy();
	}

}
