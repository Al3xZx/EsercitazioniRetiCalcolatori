package reti.src.it.unical.dimes.traccia3sol2;

import java.io.Serializable;

public class Richiesta implements Serializable{
	
	private String descrizione;
	private int importoMassimo;
	
	public Richiesta(String descrizione, int importoMassimo) {
		super();
		this.descrizione = descrizione;
		this.importoMassimo = importoMassimo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getImportoMassimo() {
		return importoMassimo;
	}		

}
