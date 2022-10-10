package h2;

public class Zoo {
	public static Tier[] tiere;
	public static Gehege[] gehege;
	
	public static String vorstellen() {
		//TODO implement
		String vorstellung = "";
		for (Tier tier : tiere) {
			vorstellung += tier.toString() + "\n";
		}
		return vorstellung;
	}
	
	public static String flugshow() {
		//TODO implement
		String flugshow = "";
		for (Tier tier : tiere) {
			if (tier instanceof Fliegen)
			flugshow += ((Fliegen) tier).fliegen() + "\n";
		}
		return flugshow;
	}
	
	public static String schwimmtraining() {
		//TODO implement
		String schwimmtraining = "";
		for (Tier tier : tiere) {
			if (tier instanceof Schwimmen)
			schwimmtraining += ((Schwimmen) tier).schwimmen() + "\n";
		}
		return schwimmtraining;
	}
}
	 