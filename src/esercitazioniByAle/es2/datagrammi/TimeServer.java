package esercitazioniByAle.es2.datagrammi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class TimeServer {
    private static int port = 3037;
    public static void main(String[] args) {
        try {
            DatagramSocket server;
            while(true){
                System.out.println("in attesa di connessione sulla porta " + port);;
                server = new DatagramSocket(port);
                //ricevo la richiesta
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);//perchè lo devo ricevere
                                                                //non mi serve l'InetAdress e la porta

                //aspetto che mi arriva un pacchetto di richiesta
                server.receive(packet);

                System.out.println("nuova conessione con "+packet.getAddress().toString());

                //preparo la data da inviare;
                String date = new Date().toString();
                //inserisco la data nel buffer e la invio a chi me l'ha richiesta
                buf = date.getBytes();
                //prelevo le ino d chi mi ha mandato la richiesta
                InetAddress clientAddr = packet.getAddress();
                int clientPort = packet.getPort();
                System.out.println(clientPort);
                //creo il pacchetto da inviare
                packet = new DatagramPacket(buf,buf.length,clientAddr,clientPort);

                //invio il pacchetto(datagramma) e chiudo la connessione
                server.send(packet);
                server.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
