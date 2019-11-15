/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;



/**
 *
 * @author informatica
 */
public class BClient {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
  
    public static void main(String[] args) throws IOException{
        
        //ip bonechi lai2-04 - "172.16.10.72"
        
        try (Socket socket = new Socket("127.0.0.1" , 6012)) {
            
            /*Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            
            while(true){
                
            }*/
            
            Match g = new Match(socket);
            g.run();
        }
    }
    
    
}
