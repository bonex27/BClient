/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author informatica
 */
public class NavalBattleServer { 
    /**
     * @param args
     */
     
    public static void main(String[] args) throws IOException 
    {
        Box bPlayerOne[][] = new Box[21][21];
        Box bPlayerTwo[][] = new Box[21][21];
        initMatrix(bPlayerOne);
        initMatrix(bPlayerTwo);
        try{      
            ServerSocket server = new ServerSocket(6012);
            System.out.println("BServer is onine!");
            ExecutorService ListaConnessioni = Executors.newFixedThreadPool(2);
            while(true)
            {
                Game game = new Game();
                
                
                ListaConnessioni.execute(game.new Player(server.accept(),"Player1"));
                ListaConnessioni.execute(game.new Player(server.accept(),"Player2"));
            }
        }
        catch(Exception e)
        {
            System.out.println("Errore server"+ e);
        }
        
    }
    static void initMatrix(Box a[][])
        {
           for(int i = 0; i<21;i++)
         {
             for(int j = 0; j<21;i++)
            {
             a[i][j].contenuto = 'm';
            }
         } 
        }
    
}
