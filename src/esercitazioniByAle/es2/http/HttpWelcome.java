package esercitazioniByAle.es2.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpWelcome {
    private static int port = 80;
    protected static String HtmlWelcomeMessage(){
        return "<html>\n"+
                " <head>\n"+
                " <title>UNICAL–Ingegneria Informatica</title>\n"+
                " </head>\n"+
                " <body>\n"+
                " <h2 align=\"center\">\n"+
                " <font color=\"#0000FF\">Benvenuti al Corso di"+
                " Reti di Calcolatori</font>\n"+
                " </h2>\n"+
                " </body>\n"+
                "</html>";
    }

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(port);
            System.out.println("in ascolto sulla porta: " + port);
            while(true){
                Socket client = s.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());//il flush è manuale
                String request = in.readLine();
                StringTokenizer st = new StringTokenizer(request);
                if(st.countTokens()>=2 && st.nextToken().equals("GET")){
                    String messageHtml = HtmlWelcomeMessage();
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Length: "+messageHtml.length());
                    out.println("Content-Type: text/html");
                    out.println();//secondo ritorno a capo
                    //invio il body
                    out.println(messageHtml);
                }else {
                    //out.println("400 Bad Request");senza il protocollo un browser non interpreta bene la risposta
                    out.println("HTTP/1.0 400 Bad Request");
                    out.println();//senza il senco ritorno a capo un browser non lo interpreta per bene
                }
                out.flush();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
