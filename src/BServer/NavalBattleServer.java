/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Bonex & Admir M.
 */
public class NavalBattleServer { 
    /**
     * @param args
     */
    //static int turnPlay = 1;
    
    static Box bPlayerOne[][] = new Box[21][21];
    static Box bPlayerTwo[][] = new Box[21][21]; 
    static ArrayList<Boat> Boats = new ArrayList<Boat>();//Riempito e clonato per ogni giocatore
    static Game currentPlayer;
    public static void main(String[] args) throws IOException 
    {
           initMatrix (bPlayerOne);//Inizializzazione matrice a m
                initMatrix (bPlayerTwo);
        
        
        try{
            Boat b1 = new Boat(2,"b1");
            Boats.add(b1);
            Boat b2 = new Boat(2,"b2");
            Boats.add(b1);
            Boat b3 = new Boat(2,"b3");
            Boats.add(b1);
            Boat b4 = new Boat(3,"b4");
            Boats.add(b1);
            Boat b5 = new Boat(3,"b5");
            Boats.add(b1);
            Boat b6 = new Boat(4,"b6");
            Boats.add(b1);
            Boat b7 = new Boat(5,"b7");
            Boats.add(b1);
                
            ServerSocket server = new ServerSocket(6012);
            System.out.println("BServer is onine!");
            ExecutorService ListaConnessioni = Executors.newFixedThreadPool(2);
            while(true)
            {
             
                ListaConnessioni.execute(new Game(server.accept(),'1',bPlayerOne,bPlayerTwo,Boats));              
                ListaConnessioni.execute(new Game(server.accept(),'2',bPlayerTwo,bPlayerOne,Boats));
            }
        }
        catch(Exception e)
        {
            System.out.println("Errore server"+ e);
        }
        
        
    }
        static void initMatrix(Box a[][])
        {
            Box b = new Box();
           for(int i = 0; i<21;i++)
         {
             for(int j = 0; j<21;j++)
            {
                b.x = i;
                b.y = j;
             a[i][j] = b;
            }
         } 
        }
    
    
}
