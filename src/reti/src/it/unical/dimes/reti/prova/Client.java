package reti.src.it.unical.dimes.reti.prova;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		try {
			Socket s= new Socket("localhost", 4444);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			String messaggio = (String) ois.readObject();
			System.out.println(messaggio);
			ois.close();
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
