package esercitazioniByAle;

import java.util.Random;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
	   /* System.out.println("test....");
        Scanner sc = new Scanner(System.in);
        String l = sc.nextLine();
        System.out.println(l);*/
        int i = 0;
        while(i < 10) {
            System.out.println(new Random().nextInt(11) + 1);
            i++;
        }
    }
}
