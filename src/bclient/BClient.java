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
    
    private JFrame frame = new JFrame("Naval Battle");
    private JLabel messageLabel = new JLabel("...");

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Scanner scanner;
    
    public void addB(Scanner scanner){
        System.out.println("inserisci le coordinate della barca");
            
        System.out.println("In formato: X Y lunghezza v(verticale)/o(orizzontale)");
        out.println(scanner.nextLine());
    }
    
    public static void main(String[] args) throws IOException{
        
        //ip bonechi lai2-04 - "172.16.10.72"
        
        try (Socket socket = new Socket("172.16.10.72", 6012)) {
            
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            
            /*while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                System.out.println(in.nextLine());
            }*/
        }
    }
    
    
}
