package esercitazioniByAle.es4.scommesse_cavalli;

import java.net.InetAddress;

public class Scommessa {
    private int numCavallo;
    private double puntata;
    private InetAddress ipScommettitore;
    private static int ID_SCOMMESSA;

    public Scommessa(int numCavallo, double puntata, InetAddress ipScommettitore) {
        this.numCavallo = numCavallo;
        this.puntata = puntata;
        this.ipScommettitore = ipScommettitore;
    }

    public static int getIdScommessa() {
        return ID_SCOMMESSA;
    }
}
