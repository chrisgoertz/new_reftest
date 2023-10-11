package de.ncdf;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessagePublisher extends Thread{

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName("230.0.0.0");
            for(int i = 0; i < 10; i++){
                String message = "message "+i;
                byte[] buf = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                Thread.sleep(500);
            }
            DatagramPacket endpacket = new DatagramPacket("end".getBytes(), "end".getBytes().length, group, 4446);
            socket.send(endpacket);
            socket.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
