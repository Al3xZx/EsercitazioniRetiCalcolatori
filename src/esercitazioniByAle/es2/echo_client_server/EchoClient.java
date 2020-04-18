package esercitazioniByAle.es2.echo_client_server;

import esercitazioniByAle.es1.socket.SocketOpener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) throws IOException {
        Socket s = SocketOpener.openSocket("localhost",8189,10000);
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out.println("test 1");
        out.println("test 2");
        out.println("test 3");
        out.println("BYE");
        String lineIn = in.readLine();
        while (lineIn != null) {
            System.out.println(lineIn);
            lineIn = in.readLine();
        }
    }
}
