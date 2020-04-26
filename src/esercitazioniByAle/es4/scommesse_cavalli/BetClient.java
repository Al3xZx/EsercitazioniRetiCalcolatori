package esercitazioniByAle.es4.scommesse_cavalli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Random;

public class BetClient {
    private int serverPort;
    private String server;
    private String groupAddr;
    private int groupPort;

    public BetClient(int serverPort, String server, String groupAddr, int groupPort) {
        this.serverPort = serverPort;
        this.server = server;
        this.groupAddr = groupAddr;
        this.groupPort = groupPort;
    }

    private boolean effettuaScommessa(int numCavallo, double puntata){
        String esito = null;
        Socket s = null;
        try {
            s = new Socket(server,serverPort);
            String scommessa = numCavallo+" "+puntata;
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println(scommessa);
            System.out.println("hai scommesso: " + String.format("%.2f", puntata) +"€ su "+numCavallo);
            esito = in.readLine();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return esito.equals("scommessa effettuata");
    }

    private void visualizzaVincitori(){
        try {
            MulticastSocket multicastSocket = new MulticastSocket(this.groupPort);
            multicastSocket.joinGroup(InetAddress.getByName(this.groupAddr));
            byte[] buf = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
            multicastSocket.receive(datagramPacket);
            String vincitori = new String(datagramPacket.getData());
            System.out.println("la lista dei vincitori è");
            System.out.println(vincitori);
            multicastSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BetClient bc = new BetClient(8001, "localhost", "230.0.0.1", 8002);
        int scegliCavallo = new Random().nextInt(11)+1;
        double puntata = (new Random().nextDouble()) + new Random().nextInt(30)+1;
        if (bc.effettuaScommessa(scegliCavallo,puntata)) {
            System.out.println("scommessa accettata");
            bc.visualizzaVincitori();
        }else
            System.out.println("scommesse chiuse!!");
    }
}
