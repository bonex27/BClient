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
    private String ArrOfStr[];
    
    public Match(Socket socket) throws IOException{
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }    
     
    public void place(){
        log = in.nextLine();
        ArrOfStr = log.split("@",2);
        
        System.out.println("La barca "+ArrOfStr[1]+ " da posizionare è lunga "+ ArrOfStr[0] );
        
        System.out.println("Inserisci la x(0-20):");
        log = scanner.nextLine()+"@";
        System.out.println("Inserisci la y(0-20):");
        log += scanner.nextLine()+"@";
        System.out.println("In verticale(v) o in orizzontale(o):");
        log += scanner.nextLine();
        
        out.println(log);
    }
    private void attack(){
        System.out.println("Inserisci le cordinate per l'attacco");
        
        System.out.println("Inserisci la x(0-20):");
        log = scanner.nextLine()+"@";
        System.out.println("Inserisci la y(0-20):");
        log += scanner.nextLine();
        
        out.println(log);
        log="";//reset dopo invio
    }
    
    public void run(){
        while(true)
        {
            log=in.nextLine();
            ArrOfStr=log.split("@",2);
            
            switch(ArrOfStr[1]){
                case "p":                               //Place
                    do{
                        place();
                        log=in.nextLine();
                        if(log.equals("NEAR"))
                            System.out.println("Dati inseriti non accettabili, reinserire");
                    }while(log.equals("NEAR"));
                    
                    System.out.println("Barca posizionata correttamente");
                    //visual();
                    
                    break;
                    
                case "w":                               //wait
                    System.out.println("Turno dell'avversario");
                    break;
                    
                case "a"
                        
                        :                               //attack
                    do{
                        attack();
                        log=in.nextLine();
                        if(log=="c")
                            System.out.println("Barca colpita");
                        else if(log=="m")
                            System.out.println("Barca mancata");
                        else if(log=="f")
                            System.out.println("Dati inseriti non accettabili, reinserire");
                    }while(log!="f");
                                      
                    break;
            }
        }
    }
    
    public void visual(){
        //System.out.println("_____________________________________");
            
            System.out.println(in.nextLine());//lettura giocatore
            System.out.println(in.nextLine().replaceAll("#", "\n"));
            //out.println(scanner.nextLine());       
        //System.out.println("_____________________________________");
    }

}
