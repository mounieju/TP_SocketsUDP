/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurconcurrent;

public class Main {

   public static void main(String[] args) {
    
      String host = "localhost";
      int port = 666;
      
      //Create server
      Serveur ts = new Serveur(host, port);
      ts.open();
      
      System.out.println("Serveur initialisé.");
      
      // Create 5 clients)
      for(int i = 0; i < 5; i++){
         Thread t = new Thread(new Client(host, port));
         t.start();
      }
   }
}