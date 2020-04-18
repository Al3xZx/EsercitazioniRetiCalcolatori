package esercitazioniByAle.es2.datagrammi;

import javax.jws.Oneway;
import java.io.IOException;
import java.net.*;

public class TimeClient {
    static int port = 3037;

    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            //preparo buffer sul quale ricevere la data
            byte[] buf = new byte[256];

            InetAddress indirizzoServer = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buf,buf.length,indirizzoServer,port);
            client.send(packet);

            //mi preparo a ricevere una risposta
            client.receive(packet);
            buf = packet.getData();
            String date = new String(buf);

            System.out.println(date);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
