package reti.src.it.unical.dimes.reti.ese7;

import java.rmi.*;
import java.rmi.server.*;
public class TitoloAzionarioImpl 
                    extends UnicastRemoteObject implements TitoloAzionario {
  private String nome;
  private double quotazione;
  public TitoloAzionarioImpl(String nome, double quotazioneApertura) 
             throws RemoteException {
    this.nome = nome;
    quotazione = quotazioneApertura;
  }
  public String getNome() throws RemoteException {
    return nome;
  }
  public void setQuotazione(double nuovaQuotazione) throws RemoteException {
    quotazione = nuovaQuotazione;
  }
  public double getQuotazione() throws RemoteException {
    return quotazione;
  }
}
