package esercitazioniByAle;

import java.io.IOException;
import java.net.ServerSocket;
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

        try {
            ServerSocket serverSocket = new ServerSocket(0);//porta scelta in automatico
            int port = serverSocket.getLocalPort();
            System.out.println(port);
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(new Date());

    }
}
