package Sensoren;

import java.io.IOException;
import java.net.InetAddress;

public class runClients {


    public static <Sensor> void main(String[] args) throws IOException, InterruptedException {

        InetAddress inetAddress = InetAddress.getLocalHost();
        Sensor Temperatur = new Sensor(inetAddress, 8080, "Temperatur");
        Sensor Helligkeit = new Sensor(inetAddress, 8080, "Helligkeit");
        Sensor Lautstärke = new Sensor(inetAddress, 8080, "Lautstärke");

        Temperatur.start();
        Helligkeit.start();
        Lautstärke.start();
    }

}
