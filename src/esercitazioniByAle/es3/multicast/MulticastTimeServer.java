package esercitazioniByAle.es3.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class MulticastTimeServer {

    public static void main(String[] args) {
        //creo un MulticastSocket
        MulticastSocket multicastSocket = null;
        int groupPort = 3575;

        try {
            multicastSocket = new MulticastSocket(groupPort);
            InetAddress groupAddress = InetAddress.getByName("230.0.0.1");
            for (int i = 0; i < 30; i++) {
                //preparo i dati da inviare
                String data = new Date().toString();
                byte[] buf = data.getBytes();
                //non aspetto una richiesta ma invio direttamente
                DatagramPacket packet = new DatagramPacket(buf,buf.length,groupAddress,groupPort);
                multicastSocket.send(packet);
                System.out.println("Broadcasting "+data);
                Thread.sleep(1000);
            }
            multicastSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            if(multicastSocket != null)
                multicastSocket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
