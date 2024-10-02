package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Zentralrechner extends Thread{

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public Zentralrechner(int i) throws SocketException {
        socket = new DatagramSocket(8080);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received = new String(packet.getData(), 0, packet.getLength());
            //System.out.println(address + " " + port);
            System.out.println(received);

        }
        socket.close();
    }

    public static void main(String[] args) throws SocketException {

        Zentralrechner zentralrechner = new Zentralrechner(8080);
        zentralrechner.run();

    }

}
