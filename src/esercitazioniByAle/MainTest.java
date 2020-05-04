package esercitazioniByAle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
	   /* System.out.println("test....");
        Scanner sc = new Scanner(System.in);
        String l = sc.nextLine();
        System.out.println(l);*/
        /*int i = 0;
        while(i < 10) {
            System.out.println(new Random().nextInt(11) + 1);
            i++;
        }*/
//        for (int i = 0; i < 5; i++) {
//            Calendar c = Calendar.getInstance();
//            System.out.println(c.getTime());
//            System.out.println(c.getTimeInMillis());
//            c.add(Calendar.MINUTE,10);
//            System.out.println(c.getTime());
//            System.out.println(c.getTimeInMillis());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        String s = "ciao a tutti";
//        String[] ss = s.split(" ");
//        System.out.println(ss.length);
//        for (String sss : ss) {
//            System.out.println(sss);
//        }

//        try {
//            ServerSocket serverSocket = new ServerSocket(0);//porta scelta in automatico
//            int port = serverSocket.getLocalPort();
//            System.out.println(port);
//            serverSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println(new Date());

//        byte[] data = new byte[255];
//        System.out.println(data.length);
//        String s = "ciao mondo 1.2.3.4 8080";
//        byte[] sByte = s.getBytes();
//        for (int i = 0; i < sByte.length; i++) {
//                data[i] = sByte[i];
//        }
//        System.out.println(data.length);
//        String dataString = new String(data);
//        System.out.println(dataString.length());
//        dataString+="test";
//        System.out.println(dataString.length());
//        System.out.println(dataString);
//        for (int i = 0; i < dataString.length(); i++) {
//            System.out.println(dataString.charAt(i)+" "+Character.getType(dataString.charAt(i)));
//        }
//        //while.....
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < dataString.length(); i++) {
//            if(Character.getType(dataString.charAt(i)) != Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE)
//                sb.append(dataString.charAt(i));
//        }
//        String newDataString = sb.toString();
//        System.out.println(newDataString.length());
//
//        for (int i = 0; i < newDataString.length(); i++) {
//            System.out.println(newDataString.charAt(i));
//        }

        try {
            InetAddress ad = InetAddress.getLocalHost();
            System.out.println(ad.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
