package reti.esercitazioni.esercitazione1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnessioneTimeServer {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("ntp1.inrim.it",13);
            System.out.println(s.toString());
            BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
//BufferedReader per leggere una riga alla volta---InputStreamReader converte i byte in caratteri
            String line = in.readLine();
            while (line != null){
                System.out.println(line);
                line=in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
