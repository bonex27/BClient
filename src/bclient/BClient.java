/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bclient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;



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
        String ip;
        //ip bonechi lai2-04 - "172.16.10.72"
        do
        {
        System.out.println("Inserisci l'indirizzo ip o il dominio del server");
        Scanner input = new Scanner(System.in);
        ip =input.nextLine();
        BN_GUI a=new BN_GUI(21,21);
        
         try (Socket socket = new Socket("127.0.0.1" , 6012)) {
            Match g = new Match(socket,a);
            g.runi();
         }
         catch(Exception e)
         {
             System.out.println("Ip errato!");
         }
         finally
         {
             System.out.println("Gioco finito!");
         }
        }while(true);
        
        
        
       
        
    }
}