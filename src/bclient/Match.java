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



/**
 *
 * @author informatica
 */
public class Match {
    
    Scanner in; 
    PrintWriter out; 
    public Scanner scanner = new Scanner(System.in);
    private String log;
    private String ArrofStr[];
    
    public Match(Socket socket) throws IOException{
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }    
    
    /*private void attack(Scanner in, Scanner scanner, Scanner out){
        System.out.println("Inserisci le coordinate dove vuoi attaccare");
        
        System.out.println("In formato: X Y");
        out.println(scanner.nextLine());
        
        System.out.println(in.nextLine());
    }*/
    
    public void run(){
        while(true)
        {
            log=in.nextLine();
            //System.out.println(log);
            ArrofStr=log.split("@",2);
            switch(ArrofStr[1]){
                case "p":
                    log=in.nextLine();
                    //System.out.println(log);
                    ArrofStr=log.split("@");
                    System.out.println("Barca "+ArrofStr[1]+ "lunga "+ ArrofStr[0] );
                    
                    break;
            }
        }
    }
    
    public void visual(){
        //System.out.println("_____________________________________");
        while(true){
            
            System.out.println(in.nextLine());//lettura giocatore
            System.out.println(in.nextLine().replaceAll("#", "\n"));
            out.println(scanner.nextLine());
        }
        //System.out.println("_____________________________________");
    }

}
