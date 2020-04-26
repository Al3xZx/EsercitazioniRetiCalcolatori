package esercitazioniByAle.es4.scommesse_cavalli;

import java.net.InetAddress;

public class Scommessa {
    private int numCavallo;
    private double puntata;
    private InetAddress ipScommettitore;
    private static int ID_SCOMMESSA = 0;

    public Scommessa(int numCavallo, double puntata, InetAddress ipScommettitore) {
        this.numCavallo = numCavallo;
        this.puntata = puntata;
        this.ipScommettitore = ipScommettitore;
        ID_SCOMMESSA++;
    }

    public int getNumCavallo() {
        return numCavallo;
    }

    public double getPuntata() {
        return puntata;
    }

    public InetAddress getIpScommettitore() {
        return ipScommettitore;
    }

    public static int getIdScommessa() {
        return ID_SCOMMESSA;
    }
}
