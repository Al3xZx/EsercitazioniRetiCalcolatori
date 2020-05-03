package esercitazioniByAle.es4.comunicator;

import java.net.InetAddress;

public class Comunicator {

    private InetAddress ip;
    private int porta;

    public Comunicator(InetAddress ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPorta() {
        return porta;
    }

    @Override
    public String toString() {
        return "Comunicator{" +
                "ip=" + ip +
                ", porta=" + porta +
                '}';
    }
}
