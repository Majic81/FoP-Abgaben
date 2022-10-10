package h2;

public class Delfin extends Saeugetier implements Schwimmen {
	
	public Delfin(String name, Gehege gehege) {
		super(name, gehege);
	}
	
	public String toString() {
		if (gehege == null) return "Ich wurde noch nicht zugeteilt";
		return "Ich bin" + getClass().getSimpleName() + name + ", wohne im Gehege" + gehege.name + "und bin ein" + super.tierart;
	}
	
	@Override
	public String schwimmen() {
		return"elegantes schwimmen";
	}
	
	@Override
	public boolean kompatibel (Tier tier) {
		if (tier instanceof Krokodil) return false;
		return true;
	}
}
