package esercitazioniByAle.es2.datagrammi.esercizio2_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.TimeZone;

public class TimeClient {


    public static void main(String[] args) {
        //chiedo la time sone all'utente
        System.out.println(avaibleTimeZones());
        System.out.println("inserisci TimeZone > ");
        String tz = new Scanner(System.in).nextLine().trim();

        System.out.println("hai scelto: " + tz);
        //il client invia la time zone
        try {
            DatagramSocket socket = new DatagramSocket();//la porta è casuale;
            byte[] buf = tz.getBytes();
            DatagramPacket p = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 5056);
            socket.send(p);

            //ricevo la data della rispettiva time zone
            buf = new byte[256];
            p = new DatagramPacket(buf,buf.length);
            socket.receive(p);
            String date = new String(p.getData());
            System.out.println(date);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String avaibleTimeZones() {
        StringBuilder sb = new StringBuilder();
        sb.append("i time zona disponibili sono i seguenti scrivi quali desideri usare");
        for(String s : TimeZone.getAvailableIDs()){
            sb.append(s+"\n");
        }
        return sb.toString();
    }
}
