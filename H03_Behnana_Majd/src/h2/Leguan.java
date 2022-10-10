package h2;

public class Leguan extends Reptil {
	public Leguan(String name, Gehege gehege) {
		super(name, gehege);	
	}
	public String toString() {
		if (gehege == null) return "Ich wurde noch nicht zugeteilt";
		return "Ich bin" + getClass().getSimpleName() + name + ", wohne im Gehege" + gehege.name + "und bin ein" + super.tierart;
	}
	
	@Override
	public boolean kompatibel (Tier tier) {
		if (tier instanceof Fledermaus || tier instanceof Krokodil) return false;
		return true;
	}
	
}
