package esercitazioniByAle.es2.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServer {

    private static int port = 3050;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println(server + " "+ server.getInetAddress() + " in ascolto sulla porta " + port);
            while(true){
                Socket client = server.accept();
                System.out.println("connesso con il client " + client);
                new HttpServerThread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
