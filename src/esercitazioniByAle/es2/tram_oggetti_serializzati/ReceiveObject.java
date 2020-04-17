package esercitazioniByAle.es2.tram_oggetti_serializzati;

import org.omg.CORBA.WStringSeqHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveObject {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",8189);
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            int i = in.readInt();
            System.out.println(i);
            String begin = (String) in.readObject();
            System.out.println(begin);
            Studente studente = (Studente) in.readObject();
            System.out.println(studente);
            String end =(String) in.readObject();
            System.out.println(end);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
