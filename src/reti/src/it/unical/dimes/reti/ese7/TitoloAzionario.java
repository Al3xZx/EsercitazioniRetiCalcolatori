package reti.src.it.unical.dimes.reti.ese7;

import java.rmi.*;

public interface TitoloAzionario extends Remote {
  String getNome() throws RemoteException;
  double getQuotazione() throws RemoteException;
}

