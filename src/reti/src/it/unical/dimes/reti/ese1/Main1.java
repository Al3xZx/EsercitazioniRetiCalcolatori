package reti.src.it.unical.dimes.reti.ese1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main1 {

	public static void main(String[] args) {
		printLocalAddress();
		System.out.println("--------");
		printRemoteAddress("www.dimes.unical.it");
		
		System.out.println("--------");
		printRemoteAddress("8.8.8.8");

	}
	
	static void printLocalAddress () {
	    try {
	      InetAddress myself = InetAddress.getLocalHost ();
	      System.out.println ("My name : " + myself.getHostName ());
	      System.out.println ("My IP : " + myself.getHostAddress ());
	    } catch (UnknownHostException ex) {
	      System.out.println ("Failed to find myself:");
	    }
	}
	static void printRemoteAddress (String name) {
	    try {
	      InetAddress machine = InetAddress.getByName (name);
	      System.out.println ("Host name : " + machine.getHostName ());
	      System.out.println ("Host IP : " + machine.getHostAddress ());
	    } catch (UnknownHostException ex) {
	      System.out.println ("Failed to lookup " + name);
	    }
	}


}
