package esercitazioniByAle;

import java.util.Calendar;
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
        for (int i = 0; i < 5; i++) {
            Calendar c = Calendar.getInstance();
            System.out.println(c.getTime());
            System.out.println(c.getTimeInMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
