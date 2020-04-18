package esercitazioniByAle.es2.datagrammi.esercizio2_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TimeServer {
    private static int port =5056;

    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(port);
            while(true){
                byte[] buf = new byte[256];
                DatagramPacket p = new DatagramPacket(buf,buf.length);
                s.receive(p);
                new TimeServerThread(p).start();
            }
            //s.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
