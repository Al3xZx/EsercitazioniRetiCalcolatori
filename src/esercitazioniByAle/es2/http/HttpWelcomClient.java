package esercitazioniByAle.es2.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpWelcomClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",3050);
            PrintWriter out = new PrintWriter(s.getOutputStream());//flush manuale
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String request = "GET 127.0.0.1 HTTP/1.1";
            out.println(request);
            out.println();//secondo ritorno a capo
            out.flush();//invio tutta la richiesta
            String response = in.readLine();
            while(response != null){
                System.out.println(response);
                response=in.readLine();
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
