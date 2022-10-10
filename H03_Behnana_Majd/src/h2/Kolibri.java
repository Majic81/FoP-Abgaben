package h2;

public class Kolibri extends Vogel implements Fliegen{
	public Kolibri(String name, Gehege gehege) {
		super(name, gehege);	
	}
	public String toString() {
		if (gehege == null) return "Ich wurde noch nicht zugeteilt";
		return "Ich bin" + getClass().getSimpleName() + name + ", wohne im Gehege" + gehege.name + "und bin ein" + super.tierart;
	}
	@Override
	public String fliegen() {
		return "schnelles flattern";
	}
	
	@Override
	public boolean kompatibel (Tier tier) {
		if (tier instanceof Emu) return false;
		return true;
	}
}
