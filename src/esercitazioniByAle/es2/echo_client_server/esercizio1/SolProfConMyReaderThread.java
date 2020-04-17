package esercitazioniByAle.es2.echo_client_server.esercizio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class SolProfConMyReaderThread {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",8189);
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader in =  new BufferedReader(new InputStreamReader(s.getInputStream()));

            /**spiegare a cosa serve usare un thread per leggere ciò che scrive il server:
             * un thread myreader si mette in ascolto sull'input stream lo abbiamo usato per
             * evitare che l'applicazione principale rimanga bloccata in attesa del messaggio in
             * arrivo*/
            new MyReader(in).start();
            /**usiamo uno scanner per scrivere ciò che vogliamo inviare al server*/
            Scanner sc = new Scanner(System.in);
            String linea = sc.nextLine();
            while(!linea.trim().toUpperCase().equals("EXIT")){
                out.println(linea);
                linea = sc.nextLine();
            }
        } catch (ConnectException e){
            System.out.println("connection exception: è scaduto il time out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MyReader extends Thread {
        private BufferedReader in;
        public MyReader(BufferedReader in) {
            this.in = in;
        }
        @Override
        public void run() {
            try {
                String line = in.readLine();
                while(line != null){
                    System.out.println(line);
                    line= in.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
