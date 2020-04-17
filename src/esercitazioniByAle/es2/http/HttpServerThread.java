package esercitazioniByAle.es2.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpServerThread extends Thread{
    private Socket client;

    public HttpServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());//flush manuale
            String request = in.readLine();
            StringTokenizer st = new StringTokenizer(request);
            if(st.countTokens()>=2 && st.nextToken().toUpperCase().equals("GET")){
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html");
                out.println();
                //invio il body creato nell'altra classe
                out.println(HttpWelcome.HtmlWelcomeMessage());
            }else{
                out.println("HTTP/1.0 400 Bad Request");
                out.println();
            }
            out.flush();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
