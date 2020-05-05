package esercitazioniByAle.es4.comunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;

public class ComunicatorServer extends Thread{

    private InetAddress myIp;
    private int myPort;

    private ServerSocket serverSocket;

    private ComunicatorApp comunicatorApp;

    public ComunicatorServer(ComunicatorApp comunicatorApp) {
        try {
            serverSocket = new ServerSocket(0);//porta scelta in automatico
            myPort = serverSocket.getLocalPort();
            myIp=serverSocket.getInetAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new GestoreMulticast().start();
        this.comunicatorApp = comunicatorApp;
    }


    /**
     * resto in ascolto di ricezione di messaggi tcp e li aggiungo alla lista messaggi di comunicatorApp
     * */
    @Override
    public void run() {

        try {
            while (true){
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String s = in.readLine();
                while(s != null){
                    sb.append(s);
                    s = in.readLine();
                }
                String messaggio = sb.toString();
                Messaggio m = new Messaggio(messaggio, new Date());
                System.out.println("nuovo messaggio arrivato>>> "+messaggio);
                comunicatorApp.getMessaggi().add(m);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getMyPort(){
        return myPort;
    }

    public InetAddress getMyIp() {
        return myIp;
    }

    private class GestoreMulticast extends  Thread{
        MulticastSocket multicastSocket;

        public GestoreMulticast() {
            try {
                multicastSocket = new MulticastSocket(2000);
                multicastSocket.joinGroup(InetAddress.getByName("230.0.0.1"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                Comunicator mittente = riceviMessaggioMulticast();
                if (mittente != null)
                    inviaRispostaTCP(mittente);
                else
                    throw new RuntimeException("utente non esistente");
            }
        }

        /**
         * un messaggio arriva nella seguente forma "ip_mittente porta[][][][][][][][][][][][][][][][][][]"
         * [] equivale a carattere non specificato
         * */
        private Comunicator riceviMessaggioMulticast() {
            byte[] buf = new byte[64];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                multicastSocket.receive(packet);
                String s = new String(packet.getData());
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (Character.getType(s.charAt(i)) != Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE)
                        sb.append(s.charAt(i));
                }
                String welcomeMessage = sb.toString();
                String[] dati = welcomeMessage.split(" ");
                //String ipMittente = dati[0];
                InetAddress ipMittente = InetAddress.getByName(dati[0]);
                int port = Integer.parseInt(dati[1]);
                return new Comunicator(ipMittente, port);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }


        private void inviaRispostaTCP(Comunicator mittente) {
            try {
                Socket socket = new Socket(mittente.getIp().getHostAddress(),mittente.getPorta());
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                String s = myIp.getHostAddress()+" "+ myPort;
                out.println(s);
                socket.close();
                if(!comunicatorApp.getComunicators().contains(mittente))
                    comunicatorApp.getComunicators().add(mittente);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
