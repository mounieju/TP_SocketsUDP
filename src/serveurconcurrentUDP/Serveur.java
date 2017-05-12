package serveurconcurrentUDP;

import java.net.InetAddress;
import java.net.*;
import java.lang.*;

public class Serveur implements Runnable {

    private int serverPort;
    private InetAddress serverIP;
    private DatagramSocket serverSocket;
    private DatagramPacket serverPacket;
    private byte[] buffer;
    private boolean running = false;
    
    public Serveur(String address, int port){
        try{
            this.serverIP = InetAddress.getByName(address);
            this.serverPort = port; 
            this.serverSocket = new DatagramSocket(serverPort);  
            this.buffer = new byte[512];
            this.serverPacket = new DatagramPacket(buffer,buffer.length);
            this.running = true;
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        this.run();
        
    }
    
    @Override
    public void run(){
        while(isRunning()){
            try{
                this.serverSocket.receive(this.serverPacket);
                System.out.println("Connexion d'un client");
                Thread t = new Thread(new Communication(this.serverPacket.getPort(),this.serverPacket.getAddress()));
                t.start();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        
        try{
            serverSocket.close();
        }catch(Exception e) {
            e.printStackTrace();
            serverSocket = null;
        }
        
    }
    
    
    public boolean isRunning(){
        return this.running;
    }
}
