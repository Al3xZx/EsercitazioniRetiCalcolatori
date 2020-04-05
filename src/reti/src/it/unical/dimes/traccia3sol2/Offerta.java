package reti.src.it.unical.dimes.traccia3sol2;

import java.io.Serializable;

public class Offerta implements Serializable{
	
	private int id;
	private int importoRichiesto;
	public Offerta(int id, int importoRichiesto) {
		super();
		this.id = id;
		this.importoRichiesto = importoRichiesto;
	}
	public int getId() {
		return id;
	}
	public int getImportoRichiesto() {
		return importoRichiesto;
	}
}
