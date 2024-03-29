package reti.src.it.unical.dimes.traccia3sol2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Giudice {

	private final static int rPort = 2000;
	
	private final static String gAddress = "230.0.0.1";
	private final static int gPort = 3000;
	
	private final static int oPort = 4000;
	
	private final static int n = 10;

	public static void main(String[] args) throws ClassNotFoundException {
		try {

			InetAddress group = InetAddress.getByName(gAddress);

			// Fase 1: ricevi richiesta
			ServerSocket server = new ServerSocket(rPort);
			Socket accettaRichiesta = server.accept();
			ObjectInputStream ois = new ObjectInputStream(accettaRichiesta.getInputStream());
			Richiesta richiesta = (Richiesta) ois.readObject();

			// Fase 2: invia richiesta ai partecipanti
			inviaRichiestaAiPartecipanti(richiesta, group);

			// Fase 3: ricevi offerte dai partecipanti
			Offerta oVincente = riceviOfferte();

			// Fase 4: invia esito gara
			ObjectOutputStream oos = new ObjectOutputStream(accettaRichiesta.getOutputStream());
			oos.writeObject(oVincente);
			inviaEsitoGara(oVincente, group);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void inviaRichiestaAiPartecipanti(Richiesta richiesta, InetAddress group) {
		try {
			MulticastSocket msocket = new MulticastSocket(3000);
			byte buf[] = new byte[512];
			String r = richiesta.getDescrizione() + " - " + richiesta.getImportoMassimo();
			buf = r.getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, gPort);

			msocket.send(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Offerta riceviOfferte() {
		Offerta oVincente = null;

		try {

			ServerSocket server = new ServerSocket(oPort);

			for (int i = 0; i < n; i++) {
				Socket partecipante = server.accept();

				ObjectInputStream ois = new ObjectInputStream(partecipante.getInputStream());

				Offerta offerta = (Offerta) ois.readObject();

				if (oVincente == null)
					oVincente = offerta;
				else if (offerta.getImportoRichiesto() > oVincente.getImportoRichiesto()
						|| (offerta.getImportoRichiesto() == oVincente.getImportoRichiesto() && offerta.getId() < oVincente.getId()))
					oVincente = offerta;

				partecipante.close();
			}// for

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return oVincente;

	}

	private static void inviaEsitoGara(Offerta oVincente, InetAddress group) {
		try {
			MulticastSocket mSocket = new MulticastSocket(gPort);

			String message = oVincente.getId() + " - " + oVincente.getImportoRichiesto();

			byte[] buf = message.getBytes();

			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, gPort);

			mSocket.send(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
