package esercitazioniByAle.es2.echo_client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(8189);
            System.out.println(s +"in attesa di connessione");
            Socket incoming = s.accept();//stop in attesa di connessione
            System.out.println("connesso a socket: " + incoming);
            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter(incoming.getOutputStream(),true);
            out.println("ciao, scrivi BYE per uscire");
            String line = in.readLine();
            while (line != null && !line.trim().toUpperCase().equals("BYE")){
                out.println("ECHO: "+line);
                line = in.readLine();
            }
            out.println("ciao a presto");
            incoming.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
