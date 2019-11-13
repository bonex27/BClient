/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Bonex & Admir M.
 */
public class Game implements Runnable{

    private String sName;
    Socket socket;
    Scanner input;
    private ArrayList<Boat> Boats = new ArrayList<Boat>();
    private Box refGrid[][];
    private Box refOpponent[][];
    PrintWriter output;
    
    public Game(Socket socket, String sName,Box refGrid[][],Box refOpponent[][],ArrayList<Boat> Boats) 
    {
        this.socket = socket;
        this.sName = sName;
        this.refGrid = refGrid;
        this.refOpponent = refOpponent;
        this.Boats = (ArrayList)Boats.clone();//Copia delle barche pronte a esssere inserite
    }

    @Override
    public void run() 
    {
        try
        {
            System.out.println(this.sName+ " connesso!");
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(),true);
            while(input.hasNextLine())
            {
                output.println("Sei il " + this.sName);
            }

        }
        catch(IOException e)
        {
            System.out.println("Errore class player: " + e);
        }
    }
    public String setBoat(int x,int y,char cOr,String boatName,Boat b)
    {
        if(this.checkSpazio(x, y, b.iLunghezza, cOr) == true)
        {
            
        
            int iL;
            if(cOr == 'v')
            {
                 iL = x;
                 for(int i = 0; i < b.iLunghezza;i++, iL++)
                    {
                        refGrid[iL][y].contenuto = 'b';
                        refGrid[iL][y].nomeBarca= boatName;

                    }
            }
            else if(cOr == 'o')
            {
                iL = y;
            for(int i = 0; i < b.iLunghezza;i++, iL++)
                {
                    refGrid[x][iL].contenuto = 'b';
                    refGrid[x][iL].nomeBarca= boatName;
                }
            }
            return "Barca aggiunta!";
        }
        else
        {
          return "Barca vicina!";  
        }
        

    }
    public boolean checkSpazio(int x,int y,int iLung,char cOr)//Controlla in tutte le 8 caselle vicine 
    {
        int iL;
        if(cOr == 'v')
        {
             iL = x;
             for(int i = 0; i < iLung;i++, iL++)
           {
                   if(   refGrid[iL][y].contenuto != 'm' &&  
                         refGrid[iL][y].contenuto != 'm' &&
                         refGrid[iL][y].contenuto != 'm' &&
                         refGrid[iL][y].contenuto != 'm' &&
                         refGrid[iL][y].contenuto != 'm' &&  
                         refGrid[iL][y].contenuto != 'm' &&
                         refGrid[iL][y].contenuto != 'm' &&
                         refGrid[iL][y].contenuto != 'm')
                       return false;                  
           }   
        return true;
        }
        else if(cOr == 'o')
        {
            iL = y;
             for(int i = 0; i < iLung;i++, iL++)
           {
                   if(   refGrid[x][iL].contenuto != 'm' &&  
                         refGrid[x][iL].contenuto != 'm' &&
                         refGrid[x][iL].contenuto != 'm' &&
                         refGrid[x][iL].contenuto != 'm' &&
                         refGrid[x][iL].contenuto != 'm' &&  
                         refGrid[x][iL].contenuto != 'm' &&
                         refGrid[x][iL].contenuto != 'm' &&
                         refGrid[x][iL].contenuto != 'm')
                       return false;                  
           }   
        return true;
        }       
        return false;
    }
}
    

