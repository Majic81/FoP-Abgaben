package h2;

public abstract class Tier {
	public static boolean constructorCalled = false;
	protected String name;
	protected Gehege gehege;
	
	public Tier(String name, Gehege gehege) {
		constructorCalled = true;
		this.name = name;
		if(gehege != null && gehege.add(this)) this.gehege = gehege;
	}
	
	public void umsiedeln(Gehege neueGehege) {
		this.gehege = neueGehege;
	}
	
	public boolean kompatibel(Tier t) {
		return true;
	}
	
}
