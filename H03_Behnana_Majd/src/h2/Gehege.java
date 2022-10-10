package h2;

public class Gehege {
	String name;
	Tier[] tiere;

	public Gehege(String name, int size) {
		this.name = name;
		tiere = new Tier[size];
	}

	/**
	 * this method there a parameter from the type tier, returns true and adds the animal (tier) to the animal array (tiere)
	 * in the fence (gehege, with which the method is called) after making sure the animal is compatible with the rest of the 
	 * animals inside the fence and the fence still has a free place for the animal. In case the animal is not compatible with the 
	 * rest of the animals inside the fence or the fence is full the method returns false.

	 * @param t the given animal which should be added to the fence if possible. 
	 * @return true in case the animal is added to the fence, false in case it was not possible to add the animal to the fence
	 */
	public boolean add(Tier t) {
		//TODO implement

		if (isArrayFull(this.tiere)) return false;

		for (Tier tier : tiere) {
			if (!t.kompatibel(tier)) {
				return false;
			}
		}
		for(int i = 0; i < this.tiere.length; i++) {
			if (this.tiere[i] == null) {
				this.tiere[i] = t;
				break;	
			}
		}
		t.umsiedeln(this);
		return true;
	}

	public boolean isArrayFull(Tier[] array) {
		if (array[array.length-1] != null) return false;
		return true;
	}
}
