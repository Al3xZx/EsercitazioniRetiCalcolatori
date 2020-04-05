package reti.src.it.unical.dimes.traccia3;

import java.io.Serializable;

public class Richiesta implements Serializable {
	private String descrizioneOpera;
	private int importoMassimo;

	public Richiesta(String descrizioneOpera, int importoMassimo) {
		this.descrizioneOpera = descrizioneOpera;
		this.importoMassimo = importoMassimo;

	}

	public String getDescrizioneOpera() {

		return descrizioneOpera;

	}

	public int getImportoMassimo() {

		return importoMassimo;

	}
}// class Richiesta
