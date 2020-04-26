package esercitazioniByAle.es4.scommesse_cavalli;

import java.util.Calendar;

public class BetServer {
    private int myPort;
    private int groupPort;
    private String groupAddr;
    private AccettatoreScommesse accettatoreScommesse;
    private RifiutatoreScommesse rifiutatoreScommesse;
    private Calendar timeLimit;

    public BetServer(int myPort, int groupPort, String groupAddr, Calendar timeLimit) {
        this.myPort = myPort;
        this.groupPort = groupPort;
        this.groupAddr = groupAddr;
        this.timeLimit = timeLimit;
        accettatoreScommesse = new AccettatoreScommesse();
        accettatoreScommesse.start();
    }

    private class AccettatoreScommesse extends Thread{

    }

    private class RifiutatoreScommesse {

    }
}
