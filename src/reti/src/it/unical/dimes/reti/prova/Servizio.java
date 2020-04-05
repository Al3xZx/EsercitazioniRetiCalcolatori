package reti.src.it.unical.dimes.reti.prova;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servizio {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(4444);
			while(true){
			Socket s= ss.accept();
			System.out.println(s);
			
			Thread.sleep(10000);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject("OK");
			oos.close();
			s.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
