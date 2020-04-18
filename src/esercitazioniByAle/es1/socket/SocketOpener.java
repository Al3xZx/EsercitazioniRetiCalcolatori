package esercitazioniByAle.es1.socket;

import java.io.IOException;
import java.net.Socket;

public class SocketOpener extends Thread{
    private String host;
    private int port;
    private Socket socket;

    public SocketOpener(String host, int port){
        this.host=host;
        this.port=port;
        socket=null;
    }

    public Socket getSocket() {
        return this.socket;
    }

    @Override
    public void run() {
        try {
            socket=new Socket(this.host,this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Socket openSocket(String host, int port, int timeout) {
        SocketOpener opener = new SocketOpener(host, port);
        opener.start();
        try {
            opener.join(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return opener.getSocket();
    }
}
