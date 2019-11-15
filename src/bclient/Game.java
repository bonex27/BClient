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
public class Game {
    
    Scanner in; 
    PrintWriter out; 
    public Scanner scanner = new Scanner(System.in);
    private String log;
    
    public Game(Socket socket) throws IOException{
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        log = scanner.nextLine();
    }    
    public void addB(){
        String a;
        visual();
        //System.out.println("Inserisci le coordinate della barca");   
        //System.out.println("X:");
        
        
        //System.out.println(log);
        //Y lunghezza v(verticale)/o(orizzontale)");
        
        //out.println(log);
        //System.out.println(in.nextLine());
    }
    
    /*private void attack(Scanner in, Scanner scanner, Scanner out){
        System.out.println("Inserisci le coordinate dove vuoi attaccare");
        
        System.out.println("In formato: X Y");
        out.println(scanner.nextLine());
        
        System.out.println(in.nextLine());
    }*/
    
    public void visual(){
        //System.out.println("_____________________________________");
        while(true){
            
            out.println(scanner.nextLine());
            System.out.println(in.nextLine().replaceAll("#", "\n"));
        }
        //System.out.println("_____________________________________");
    }

}
