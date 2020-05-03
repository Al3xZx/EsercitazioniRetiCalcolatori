package esercitazioniByAle.es4.comunicator;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ComunicatorApp {

    private int port;

    private List<Comunicator> comunicators = new LinkedList<>();
    private List<Messaggio> messaggi = new LinkedList<>();
    private ComunicatorClient client;
    private ComunicatorServer server;

    public ComunicatorApp() {
        server = new ComunicatorServer(this);
        server.start();
        port = server.getPort();
        //client = new ComunicatorClient(port);
        client.start();
    }

    public List<Comunicator> getComunicators() {
        return comunicators;
    }

    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    public static void main(String[] args) throws UnknownHostException {
        //la porta per comunicare in tcp con i singoli host sarà
        //scelta utomaticamente dal costruttore del ServerSocket
        ComunicatorApp c = new ComunicatorApp();

        //c.comunicators.add(new Comunicator(InetAddress.getLocalHost(), 8080));test
        //decidere con quale comunicator disponibile si vuole parlare
        //visualizza lista comunicator online
        c.visualizzaComunicatoriOnLine();

        c.visualizzaMessaggi();

        //decidere a quale comunicator rispondere
        System.out.print("con quale utente vuoi comunicare (inserire posizione vista nella lista dei" +
                "comunicator online) > ");
        Scanner sc = new Scanner(System.in);
        String pos = sc.nextLine();
        System.out.println("hai scelto il comunicator: " + pos);
        Comunicator comunicator = c.getComunicators().get(Integer.parseInt(pos));
        System.out.println("scrivere il messaggio:");
        String messaggio = sc.nextLine();
        c.inviaMessaggio(messaggio, comunicator);

    }

    private void inviaMessaggio(String messaggio, Comunicator comunicator) {
        if (client.inviaMessaggio(messaggio,comunicator))
            System.out.println("messaggio inviato correttamente");
        else
            System.out.println("impossibile inviare il messaggio");
    }

    private void visualizzaMessaggi() {
        System.out.println("messaggi ricevuti:");
        for (int i = 0; i < messaggi.size(); i++) {
            System.out.println(messaggi.get(i));
        }
    }

    private void visualizzaComunicatoriOnLine() {
        System.out.println("comunicator online:");
        for (int i = 0; i < comunicators.size(); i++) {
            System.out.println("[" + i + "] " + comunicators.get(i));
        }
    }




}
