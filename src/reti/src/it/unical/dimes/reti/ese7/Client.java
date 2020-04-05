package reti.src.it.unical.dimes.reti.ese7;

import java.rmi.*;
import java.rmi.server.*;

public class Client {
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			TitoloAzionario titolo = (TitoloAzionario) Naming.lookup("rmi://localhost/soc2");
			// sostituire �localhost� con l�hostname del server remoto, se le
			// applicazioni client e server sono eseguite su macchine diverse
			System.out.println(titolo.getNome() + ": " + titolo.getQuotazione());
		} catch (Exception e) {
			System.out.println("Errore " + e);
		}
	}
}
