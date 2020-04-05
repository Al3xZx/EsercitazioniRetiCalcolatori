package reti.src.tracciaEsame.ese1;

import java.util.HashMap;
import java.util.Map.Entry;

public class Negozi {
	public HashMap<String, HashMap<String, Integer>> prodotti = new HashMap<String, HashMap<String, Integer>>();

	public void aggiorna(String idNegozio, String idProdotto, int quantita) {
		if(!prodotti.containsKey(idNegozio))
			prodotti.put(idNegozio, new HashMap<String, Integer>());
		prodotti.get(idNegozio).put(idProdotto, quantita);
	}

	public Coppia maggioreDisp(String idProdotto) {
		int max = -1;
		String idNegozio = "";
		for (Entry<String, HashMap<String, Integer>> entry : prodotti.entrySet()) {
			if (entry.getValue().containsKey(idProdotto))
				if (max < entry.getValue().get(idProdotto)) {
					max = entry.getValue().get(idProdotto);
					idNegozio = entry.getKey();
				}
		}
		return new Coppia(idNegozio, max);
	}
	public static void main(String[] args) {
		Negozi negozi = new Negozi();
		negozi.aggiorna("neg1", "orologio", 31);
		negozi.aggiorna("neg1", "braccialetto", 8);
		negozi.aggiorna("neg2", "orologio", 25);
		System.out.println(negozi.maggioreDisp("orologio"));
	}
}
