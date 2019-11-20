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
            try{
                log=in.nextLine();
                ArrOfStr=log.split("@",2);

                System.out.println("Sei il giocatore " + ArrOfStr[0]);

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

                    case "a":                               //attack
                        do{
                            attack();
                            log=in.nextLine();

                            switch (log) {
                                case "c":
                                    System.out.println("Barca colpita");
                                    break;
                                case "d":
                                    System.out.println("Barca distrutta");
                                    break;
                                case "m":
                                    System.out.println("Barca mancata");
                                    break;
                                case "gc":
                                    System.out.println("Barca già colpita");
                                    break;
                                case "f":
                                    System.out.println("Dati inseriti non accettabili, reinserire");
                                    break;
                                case "win":
                                    System.out.println("Hai vinto");
                                    break;
                                case "lose":
                                    System.out.println("Hai perso");
                                    break;
                            }

                            //out.println(ArrOfStr[0]+"@finito");

                        }while(log.equals("f"));
                        break;

                    case "v":
                        visual();
                        break;
                }
            }
            catch(Exception e){
                System.out.println("err" + e);
                break;
            }
        }
        System.out.println("Gioco finito");
    }
    
    public void visual(){
        out.println("stampa");
        System.out.println(in.nextLine().replaceAll("#", "\n"));
    }

}
