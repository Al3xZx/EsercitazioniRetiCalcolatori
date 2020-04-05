package reti.src.it.unical.dimes.reti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
  public static void main(String[] args ) {
    try {
       int port = 10000;
       System.out.println("Server in ascolto sulla porta"+port);
       ServerSocket s = new ServerSocket(port);
       Socket incoming = s.accept( );
       BufferedReader in = new BufferedReader
         (new InputStreamReader(incoming.getInputStream()));
       PrintWriter out = new PrintWriter
         (incoming.getOutputStream(), true /* autoFlush */);
       out.println( "Hello! Enter BYE to exit." );

       boolean done = false;
       while (!done) {
         String line = in.readLine();
         if (line == null)
           done = true;
         else {
            out.println("Echo: " + line);
            if (line.trim().equals("BYE"))
               done = true;
         }
       } // while
       incoming.close();
    } catch (Exception e) { System.err.println(e); }
  } // main
} // class
