package esercitazioniByAle.es2.echo_client_server.gestione_con_multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8189);
            while (true){
                System.out.println("in attesa di connessione: "+server+ server.getInetAddress().getHostName());
                Socket client = server.accept();
                System.out.println("connesso: " + client+ client.getInetAddress().getHostAddress());
                new ThreadedEchoHandler(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ThreadedEchoHandler extends Thread{
        private Socket client;
        public ThreadedEchoHandler(Socket client) {
            this.client=client;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("ciao, digita BYE per uscire");
                String linea = in.readLine();
                while(linea != null && !linea.trim().toUpperCase().equals("BYE")){
                    out.println("ECHO: "+linea);
                    linea=in.readLine();
                }
                out.println("ciao....");
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
