package reti.src.it.unical.dimes.traccia3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Giudice {
	private static final int n = 10;

	public static void main(String args[]) {
		try {

			// acoetta Ia connessione de|I'ente richiedente

			ServerSocket server1 = new ServerSocket(2000);

			Socket ente = server1.accept();

			// riceve una Richiesta

			ObjectInputStream inputl = new ObjectInputStream(ente.getInputStream());

			Richiesta richiesta = (Richiesta) inputl.readObject();

			// lnvia la strlnga contenente la Richlesta ai Partecipanti

			String r = richiesta.getDescrizioneOpera() + " - " + richiesta.getImportoMassimo();

			MulticastSocket msocket = new MulticastSocket(3000);

			byte buf[] = new byte[512];

			buf = r.getBytes();

			InetAddress group = InetAddress.getByName("230.0.0.1");

			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 3000);

			msocket.send(packet);

			// aocetta n oonnessioni dai Paitecipanti, rlceve n Offerte
			// e seieziona l'Offerta mlgllore

			Offerta offertaMigliore = null;

			ServerSocket server2 = new ServerSocket(4000);

			for (int i = 0; i < n; i++) {

				Socket partecipante = server2.accept();
				// rioeve una Offerta
				ObjectInputStream input2 = new ObjectInputStream(partecipante.getInputStream());
				Offerta offerta = (Offerta) input2.readObject();
				// venﬂca se l'Offerta corrente e la migliore corrente
				if ((offertaMigliore == null)
						|| (offerta.getImportoRichiesto() < offertaMigliore.getImportoRichiesto())
						|| ((offerta.getImportoRichiesto() == offertaMigliore.getImportoRichiesto()) && (offerta.getId() < offertaMigliore
								.getId())))
					offertaMigliore = offerta;
				partecipante.close();
			}// for
				// Invia l'offerta migliore all'ente richiedente

			ObjectOutputStream outputl = new ObjectOutputStream(ente.getOutputStream());
			outputl.writeObject(offertaMigliore);
			ente.close();
			// invia Ia stringa contenente l'offerta Migllore ai Partecipanti
			String o = offertaMigliore.getId() + " - " + offertaMigliore.getImportoRichiesto();
			buf = o.getBytes();
			packet = new DatagramPacket(buf, buf.length, group, 3000);
			msocket.send(packet);
			msocket.close();

		} catch (Exception e)

		{

			System.out.println("Error: " + e);

		}
	}
}
