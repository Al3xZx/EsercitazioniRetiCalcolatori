package esercitazioniByAle.es1;

import java.net.Socket;

public class TestSocketOpener {
    public static void main(String[] args) {
        String URL = "www.unical.it";
        int port = 80;
        int timeout = 10000;//in millisecondi
        Socket s = SocketOpener.openSocket(URL,port,timeout);
        if (s == null) {
            System.out.println("non è stato possibile connettersi");
        }else {
            System.out.println(s);
        }
    }


}
