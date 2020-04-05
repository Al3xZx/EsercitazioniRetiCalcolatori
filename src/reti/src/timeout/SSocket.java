package reti.src.timeout;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class SSocket extends Thread{
	private ServerSocket serverSocket;

	public SSocket() throws IOException {
		serverSocket = new ServerSocket(3456);
		System.out.println("Start "+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
	}
	
	public void restart() throws SocketException {
		serverSocket.setSoTimeout(20000);
		System.out.println("restart "+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
	}


	public void run() {
		try {
			serverSocket.setSoTimeout(20000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();

				System.out.println("Just connected to " + client.getRemoteSocketAddress()+" "+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
				client.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out! "+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) {
		SSocket ss;
		try {
			ss = new SSocket();
			ss.start();
			Thread.sleep(60000);
			ss.restart();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}