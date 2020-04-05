package esercitazioniByAle.es2.echo_client_server.esercizio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoServerClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8189);
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println(in.readLine());
            Scanner sc = new Scanner(System.in);
            String userLine = sc.nextLine();
            String serverLine;
            while (!userLine.trim().toUpperCase().equals("EXIT")){
                out.println(userLine);
                /*serverLine=in.readLine();
                if (serverLine!=null)*/
                    System.out.println(in.readLine());
                userLine = sc.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
