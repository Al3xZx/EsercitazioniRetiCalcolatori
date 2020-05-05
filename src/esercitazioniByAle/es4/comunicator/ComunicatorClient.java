package esercitazioniByAle.es4.comunicator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

/**il modulo Client consente all’utente di effettuare richieste per scoprire i Communicator attivi e
 per inviare messaggi ad altri Communicator mediante tcp.

 Un messaggio multicast di scoperta contiene l’indirizzo IP del Communicator mittente e la porta
 TCP usata dal modulo Server dello stesso mittente*/

public class ComunicatorClient extends Thread{

    private InetAddress myIp;
    private int myPort;
    private boolean senMulticast = true;

    MulticastSocket ms;

    public ComunicatorClient() {
        try {
            ms = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ComunicatorClient(InetAddress myIp, int myPort) {
        this.myIp = myIp;
        this.myPort = myPort;
    }

    @Override
    public void run() {
        try {
            while (senMulticast) {
                //ms.joinGroup(InetAddress.getByName("230.0.0.1"));per inviare non serve
                //preparo il messaggio
                String messaggio = myIp.getHostAddress() + " " + myPort;
                byte[] buf = messaggio.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("230.0.0.1"), 2000);
                ms.send(packet);
                ms.close();
                senMulticast = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean inviaMessaggio(String messaggio, Comunicator comunicator) {
        try {
            Socket socket = new Socket(comunicator.getIp().getHostAddress(), comunicator.getPorta());
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(messaggio);
            socket.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void nuovoMessaggioMulticast(){senMulticast = true;}
}
