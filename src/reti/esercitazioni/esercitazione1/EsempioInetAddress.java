package reti.esercitazioni.esercitazione1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsempioInetAddress {



    public static void printLocalAddress(){
        try {
            InetAddress myHost = InetAddress.getLocalHost();
            System.out.println("My name: "+ myHost.getHostName());
            System.out.println("My ip: "+ myHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void printRemoteAddress(String name){
        try {
            InetAddress genericHost = InetAddress.getByName(name);
            System.out.println("Host name: "+ genericHost.getHostName());
            System.out.println("Host ip: "+ genericHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /*tutti gli indirizzi ip associati alla macchina*/
    public static void printAllRemoteAddress(String name){
        try {
            InetAddress[] hosts = InetAddress.getAllByName(name);
            for (int i = 0; i < hosts.length; i++) {
                System.out.println("Host name("+i+"): " + hosts[i].getHostName());
                System.out.println("Host ip("+i+"): " + hosts[i].getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("host locale:");
        printLocalAddress();
        System.out.println("---------------------------------------");
        String remotHost = "8.8.8.8";
        printRemoteAddress(remotHost);
        System.out.println("---------------------------------------");
        String hostsInfo = "dns.google";
        printAllRemoteAddress(hostsInfo);
        System.out.println("---------------------------------------");
        //continuare esercitazione a 35:00
    }
}


