package Sensoren;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ThreadLocalRandom;

public class Sensor extends Thread{

    DatagramSocket udpSocket;
    private InetAddress inetAddress;
    private int port;
    private String name;


    public Sensor(){}

    public Sensor(InetAddress inetAddress, int port, String name) throws SocketException {
        this.inetAddress = inetAddress;
        this.port = port;
        this.name = name;

        this.udpSocket = new DatagramSocket();
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public int getPort() {
        return port;
    }

    public String getSensorName() {
        return name;
    }

    @Override
    public void run(){
        try {
            sendData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void sendData() throws IOException, InterruptedException {

        while(true) {
            System.out.println("sending sockets..." + getSensorName());
            byte[] message = getDataAsString().getBytes();
            DatagramPacket packet = new DatagramPacket(message,message.length, inetAddress,port);
            udpSocket.send(packet);
            Thread.sleep(3000);
        }
    }

    public String getDataAsString(){

        String result = getInetAddress().toString() + " : " + getPort() + " : " + getSensorName() + " : " +"x"+ getRandomData()+"x";

        return result;

    }

    public int getRandomData(){

        int min = 0, max = 0;


        switch (getSensorName()){

            case "Temperatur":   //grad celsius
                min = -50;
                max = 50;
                break;
            case "Lautstärke":     //dezibell
                min = 30;
                max = 130;
                break;
            case "Helligkeit":      //lumen
                min = 0;
                max = 100000;
                break;
            default:
                return -999;    //error
        }


        int randomNum = ThreadLocalRandom.current().nextInt(min,max +1);

        return randomNum;
    }

}
