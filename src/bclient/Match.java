/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bclient;




import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




/**
 *
 * @author informatica
 */
public class Match implements Runnable{
    
    Scanner in; 
    PrintWriter out; 
    public Scanner scanner = new Scanner(System.in);
    private String log, ArrOfStr[], init[];
    Socket socket;
    prova3 Jmatrice;
    int x,y;
    String verso;
    int iLung=0;
    public Match(Socket socket,prova3 a) throws IOException{
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        this.socket = socket;
        Jmatrice=a;
        //Jmatrice.buttonEnable("a");
    }    
     
    public void place(){
        log = in.nextLine();
        ArrOfStr = log.split("@",2);
        
        System.out.println("La barca "+ArrOfStr[1]+ " da posizionare è lunga "+ ArrOfStr[0] );
        iLung=Integer.parseInt(ArrOfStr[0]);
        verso=ArrOfStr[1];
       do{
           
           Jmatrice.label.setText("inserisci la barca di lunghezza "+iLung);
           //System.out.println();
           
        }while(Jmatrice.l!=true);
           if(Jmatrice.l==true){
       
        log += "@"+Jmatrice.y+"@";
         log +=Jmatrice.x;
          
        Jmatrice.l=false;
            }
    
       
        out.println(log);
       
       
    }
    
    
    private void attack(){
       
        
         do{
           
          Jmatrice.label.setText("inserisci la barca  che vuoi attaccare ");
          Jmatrice.labelError.setText("");
        }while(Jmatrice.l!=true);
         
          if(Jmatrice.l==true){
        
        log += "@"+Jmatrice.y+"@";
        y=Jmatrice.y;
        log += Jmatrice.x;
        x=Jmatrice.x;
        out.println(log);
          }
        Jmatrice.l=false;
       log="";//reset dopo invio
    } 
    
    /**
     *
     * @throws IOException
     */
    
    public void runi() throws IOException{
        do{
            try{
                log=in.nextLine();
                ArrOfStr=log.split("@",2);

               //Jmatrice.label.setText("Sei il giocatore " + ArrOfStr[0]);

                switch(ArrOfStr[1]){
                    case "p":                               //Place
                        do{
                            place();
                            log=in.nextLine();
                            if(log.equals("NEAR"))
                            {
                               Jmatrice.labelError.setText("Dati inseriti non accettabili, reinserire");
                            }
                        }while(log.equals("NEAR"));

                        Jmatrice.label.setText("Barca posizionata correttamente");
                        EventQueue.invokeLater(() -> {
                            Jmatrice.insert(verso,iLung);
                        });
                        

                        break;

                    case "w":                               //wait
                        System.out.println("Turno dell'avversario");
                        break;

                    case "a":                               //attack
                        do{
                            
                            Jmatrice.buttonEnable("b");
                            attack();
                            
                            log=in.nextLine();

                            switch (log) {
                                case "c":
                                    Jmatrice.label.setText("Barca colpita");
                                    Jmatrice.opponent(x,y,"c");
                                    
                                    
                                    
                                    break;
                                case "d":
                                    Jmatrice.label.setText("Barca distrutta");
                                    Jmatrice.opponent(x,y,"d");
                                    break;
                                case "m":
                                    Jmatrice.opponent(x,y,"m");;
                                    break;
                                case "gc":
                                     Jmatrice.label.setText("Barca già colpita");
                                    break;
                                case "f":
                                    System.out.println("Dati inseriti non accettabili, reinserire");
                                    break;
                                case "win":
                                    Jmatrice.label.setText("Hai vinto");
                                    this.socket.close();
                                    break;
                            }

                        }while(log.equals("f"));
                        
                        break;

                    case "v":
                        visual();
                        break;
                                                  
                    case "lose":
                        System.out.println("Hai perso");
                        break;
                }
            }
            catch(Exception e){
                System.out.println("err" + e);
                break;
            }
        }while(!ArrOfStr[1].equals("lose"));
        
        System.out.println("Gioco finito");
        this.socket.close();
    }
    
    public void visual(){
        out.println("stampa");
        System.out.println(in.nextLine().replaceAll("#", "\n"));
    }

    @Override
    public void run() {
        
    }

    

}
