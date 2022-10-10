package h2;

public class Krokodil extends Reptil implements Schwimmen{
	public Krokodil(String name, Gehege gehege) {
		super(name, gehege);	
	}
	public String toString() {
		if (gehege == null) return "Ich wurde noch nicht zugeteilt";
		return "Ich bin" + getClass().getSimpleName() + name + ", wohne im Gehege" + gehege.name + "und bin ein" + super.tierart;
	}
	@Override
	public String schwimmen() {
		return"paddeln";
	}
	
	@Override
	public boolean kompatibel (Tier tier) {
		if (!(tier instanceof Fliegen) && !(tier instanceof Krokodil)) return false;
		return true;
	}
}
