
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
        communicationPacket = new DatagramPacket(sendingBuffer,sendingBuffer.length);
        try{
            communicationSocket.send(communicationPacket);
            System.out.println("La connexion est établie");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        while(!communicationSocket.isClosed()){
            try{
                // On met en place se qu'il faut pour la récéption, puis on attends de recevoir des données
                receptionBuffer = new byte [512];
                communicationPacket = new DatagramPacket(receptionBuffer, receptionBuffer.length);
                System.out.println("En attente de récéption");
                communicationSocket.receive(communicationPacket);
                
                // Une fois reçu :
                System.out.println("Thread : "+Thread.currentThread().getName());
                System.out.println("Adresse du client : "+communicationPacket.getAddress()+ " | Port : "+communicationPacket.getPort() );
                System.out.println("Message : "+byteToString(communicationPacket.getData()));
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    public static String byteToString(byte[] bytes) {
        String file_string = "";
        for(int i = 0; i < bytes.length; i++) {
            file_string += (char)bytes[i];
        }
        return file_string;
    }
    
}
