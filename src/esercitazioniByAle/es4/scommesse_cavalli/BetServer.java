package esercitazioniByAle.es4.scommesse_cavalli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class BetServer {
    private int myPort;
    private int groupPort;
    private String groupAddr;
    private AccettatoreScommesse accettatoreScommesse;
    private RifiutatoreScommesse rifiutatoreScommesse;
    private long milliTimeLimit;
    private HashMap<Integer,Scommessa> scommesse = new HashMap<>();

    public BetServer(int myPort, int groupPort, String groupAddr, long milliTimeLimit) {
        this.myPort = myPort;
        this.groupPort = groupPort;
        this.groupAddr = groupAddr;
        this.milliTimeLimit = milliTimeLimit;
        accettatoreScommesse = new AccettatoreScommesse();
        accettatoreScommesse.start();
    }

    public void iniziaSessioneScommesse(){
        try {
            accettatoreScommesse.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void rifiutaScommesse(){
        rifiutatoreScommesse = new RifiutatoreScommesse();
        rifiutatoreScommesse.start();
    }

    public String scommesseVincenti(int cavalloVincente){
        StringBuilder sb = new StringBuilder();
        for (Scommessa scommessa : scommesse.values()) {
            if(scommessa.getNumCavallo()==cavalloVincente){
                double vincita = scommessa.getPuntata()*12;
                String ipVincitore = scommessa.getIpScommettitore().getHostAddress();
                sb.append(ipVincitore+" ha vinto: "+String.format("%.2f", vincita)+"€\n");
            }
        }
        return sb.toString();
    }

    private void comunicaVincitori(String vincitori) {
        try {
            MulticastSocket ms = new MulticastSocket(this.groupPort);
            byte[] buf = vincitori.getBytes();
            //DatagramPacket p = new DatagramPacket(buf, buf.length);//noooo DEVI USARE IL 4 PARAMETRI
            DatagramPacket p = new DatagramPacket(buf, buf.length, InetAddress.getByName(this.groupAddr), this.groupPort);
            ms.send(p);
            ms.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetServer() {
        this.rifiutatoreScommesse.reset();
    }

    private class AccettatoreScommesse extends Thread{
        private ServerSocket serverSocket;
        boolean possoAccettare;

        public AccettatoreScommesse(){
            possoAccettare=true;
            try {
                serverSocket = new ServerSocket(myPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while(possoAccettare) {
                    //Socket scommettitore = serverSocket.accept();//NON VA QUI
                    long milliTimeAttuale = Calendar.getInstance().getTimeInMillis();
                    try {
                        serverSocket.setSoTimeout((int)(milliTimeLimit-milliTimeAttuale));
                        Socket scommettitore = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(scommettitore.getInputStream()));
                        PrintWriter out = new PrintWriter(scommettitore.getOutputStream(),true);
                        String scommessa = in.readLine();
                        String[] cavalloPuntata = scommessa.split(" ");
                        int cavallo = Integer.parseInt(cavalloPuntata[0]);
                        double puntata = Double.parseDouble(cavalloPuntata[1]);
                        Scommessa s = new Scommessa(cavallo, puntata, scommettitore.getInetAddress());
                        int ID = Scommessa.getIdScommessa();
                        scommesse.put(ID, s);
                        out.println("scommessa effettuata");
                        scommettitore.close();
                    }
                        catch (SocketTimeoutException se){
                        possoAccettare = false;
                        serverSocket.close();
                        //System.out.println("Tempoadisposizioneperlescommesseterminato");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class RifiutatoreScommesse extends  Thread{
        private ServerSocket server;
        private boolean scommesseAperte;

        public RifiutatoreScommesse(){
            scommesseAperte = false;//false perchè se sono stato chiamato allora sono chiuse tutte le scommesse
            try {
                server = new ServerSocket(myPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void reset(){
            scommesseAperte = true;
        }

        @Override
        public void run() {
            while (!scommesseAperte){
                try{
                    Socket client = server.accept();
                    PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                    out.println("scommesse chiuse");
                    System.out.println("scommessa da " + client.getInetAddress().getHostName() + " rifiutata");
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Calendar c = Calendar.getInstance();
        System.out.println("apro le scommesse, giorno "+ c.getTime());

        //tempo per le scommesse 1 minuto;
        c.add(Calendar.MINUTE,1);
        System.out.println("le scommesse termineranno il " + c.getTime());
        BetServer b = new BetServer(8001, 8002, "230.0.0.1", c.getTimeInMillis());
        b.iniziaSessioneScommesse();
        System.out.println("scommesse chiuse");
        b.rifiutaScommesse();

        //determino il cavallo vincente
        int cavallo = new Random().nextInt(11)+1;
        System.out.println("il cavallo vincente è: " + cavallo);
        String vincitori = b.scommesseVincenti(cavallo);
        //invio in multicast il risultato
        b.comunicaVincitori(vincitori);
        Thread.sleep(90000);
        b.resetServer();
    }//main
}
