package esercitazioniByAle.es4.comunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ComunicatorServer extends Thread{

    private InetAddress myIp;
    private int port;

    private ServerSocket serverSocket;

    private ComunicatorApp comunicatorApp;

    public ComunicatorServer(ComunicatorApp comunicatorApp) {
        try {
            serverSocket = new ServerSocket(0);//porta scelta in automatico
            port = serverSocket.getLocalPort();
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
                Comunicator mittente = new Comunicator(socket.getInetAddress(), socket.getPort());
                Messaggio m = new Messaggio(messaggio, mittente, new Date());
                System.out.println("nuovo messaggio arrivato>>> "+messaggio);
                comunicatorApp.getMessaggi().add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getPort(){
        return port;
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
            riceviMessaggio();
            inviaRisposta();
        }

        private void riceviMessaggio() {
            byte[] buf = new byte[255];
            // TODO: 03/05/20 gestire il ricevimento del messaggio 
        }

        private void inviaRisposta() {
        }


    }
}
