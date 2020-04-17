package esercitazioniByAle.es2.tram_oggetti_serializzati;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendObject {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8189);
            Socket client = server.accept();
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.writeInt(3);
            out.writeObject("<ciao>");
            Studente s = new Studente(181306,"Ale","Moli","ing. info.");
            out.writeObject(s);
            out.writeObject("<arrivederci>");
            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
