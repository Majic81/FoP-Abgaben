package life.automaton;

import life.automaton.neighborhood.Neighborhood;
import life.automaton.neighborhood.Padding;
import life.automaton.rules.UpdateRules;
import life.automaton.state.AutomatonState;

public class ModularAutomatonBuilder implements CellularAutomatonBuilder {
	
	private AutomatonState state = null;
	private UpdateRules rules = null;
	private Neighborhood neighborhood = null;
	private Padding padding = null;

	
	@Override
	public CellularAutomatonBuilder addState(AutomatonState state) {
		// TODO Auto-generated method stub
		this.state = state;
		return this;
	}

	@Override
	public CellularAutomatonBuilder addRules(UpdateRules rules) {
		// TODO Auto-generated method stub
		this.rules = rules;
		return this;
	}

	@Override
	public CellularAutomatonBuilder addNeighborhood(Neighborhood neighborhood) {
		// TODO Auto-generated method stub
		this.neighborhood = neighborhood;
		return this;
	}

	@Override
	public CellularAutomatonBuilder addPadding(Padding padding) {
		// TODO Auto-generated method stub
		this.padding = padding;
		return this;
	}

	@Override
	public CellularAutomaton buildAutomaton() {
		// TODO Auto-generated method stub
		return new ModularAutomaton(this.state, this.rules, this.neighborhood, this.padding);
	}

}
