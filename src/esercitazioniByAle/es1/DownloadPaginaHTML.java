package esercitazioniByAle.es1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DownloadPaginaHTML {
    public static void main(String[] args) {
        try {
            String URL ="stackoverflow.com";
            Socket s = new Socket(URL,80);
            //converte i caratteri in byte ...
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("GET /questions HTTP/1.1");
            out.println("Host: "+URL);
            out.println();//riga vuota per confermare
            String line = in.readLine();
            while(line != null){
                System.out.println(line);
                line = in.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
