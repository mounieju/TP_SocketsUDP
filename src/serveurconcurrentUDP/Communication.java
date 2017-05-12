
package serveurconcurrentUDP;


import java.net.InetAddress;
import java.net.*;
import java.lang.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Communication implements Runnable {
    
    private DatagramSocket communicationSocket;
    private DatagramPacket communicationPacket;
    private int clientPort;
    private InetAddress clientAddress;
    private byte[] sendingBuffer;
    private byte[] receptionBuffer;
    private BufferedInputStream reader;
    private PrintWriter writer ;
    
    
    public Communication(int port, InetAddress address){
        try{
            this.clientPort = port;
            this.clientAddress = address;
            this.sendingBuffer = new byte[512];
            this.receptionBuffer = new byte[512];
            this.communicationSocket = new DatagramSocket(clientPort,clientAddress);
            this.communicationPacket = new DatagramPacket(sendingBuffer,sendingBuffer.length);
            
            System.out.println("Ouverture d'une communication");
            
            this.run();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void run(){
        boolean communicationRunnig = true;
        System.out.println("Initialisation de la communication");
        sendingBuffer = "La connexion est établie".getBytes();
        
        while(!communicationSocket.isClosed()){
            try{
                
            }
        }
        
    }
    
}
