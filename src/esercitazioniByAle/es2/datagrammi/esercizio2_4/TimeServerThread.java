package esercitazioniByAle.es2.datagrammi.esercizio2_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeServerThread extends Thread{
    private DatagramPacket packet;
    private DatagramSocket serverToClient;
    private int clientPort;
    private InetAddress clientAddr;

    public TimeServerThread(DatagramPacket p) {
        packet = p;
        clientPort = p.getPort();
        clientAddr = p.getAddress();
        try {
            serverToClient = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //ricevo la time zone da parte del client la leggo dal pacchetto ricevuto
            //mediante il costruttore
            String tz = new String(packet.getData());
            tz.trim();
            System.out.println("è ARRIVATO: " + tz+ " length "+tz.length());

            //produco la risposta sulla base della time zone
            Calendar c = new GregorianCalendar();
            c.setTimeZone(TimeZone.getTimeZone("US/Alaska"));
            System.out.println("time zone capito "+c.getTimeZone().getID());

            String dt;
            if (c != null){
                dt = c.getTime().toString();
            }else{
                dt = "time zone errata";
            }
            byte[] buf = dt.getBytes();
            packet = new DatagramPacket(buf,buf.length,clientAddr,clientPort);
            serverToClient.send(packet);
            serverToClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
