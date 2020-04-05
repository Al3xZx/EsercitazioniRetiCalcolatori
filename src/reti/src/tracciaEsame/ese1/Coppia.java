package reti.src.tracciaEsame.ese1;

public class Coppia {
	String idNegozio;
	int quantita;
	
	public Coppia(){
		
	}
	
	
	public Coppia(String idNegozio, int quantita) {
		super();
		this.idNegozio = idNegozio;
		this.quantita = quantita;
	}


	public String getIdNegozio() {
		return idNegozio;
	}
	public void setIdNegozio(String idNegozio) {
		this.idNegozio = idNegozio;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return idNegozio + " " + quantita;
	}
	

}
