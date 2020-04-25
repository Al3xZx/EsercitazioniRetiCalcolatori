package esercitazioniByAle.es3.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastTimeClient {
    public static void main(String[] args) {
        //creo una socket
        int groupPort = 3575;
        MulticastSocket multicastSocket = null;
        try {
            multicastSocket = new MulticastSocket(groupPort);
            InetAddress group = InetAddress.getByName("230.0.0.1");
            multicastSocket.joinGroup(group);
            //mi preparo a ricevere i pacchetti
            DatagramPacket p;
            byte[] buf;
            for (int i = 0; i < 10; i++) {
                buf = new byte[256];
                p = new DatagramPacket(buf,buf.length);
                multicastSocket.receive(p);
                String data = new String(p.getData());
                System.out.println("Time: " + data);
            }
            multicastSocket.leaveGroup(group);
            multicastSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
