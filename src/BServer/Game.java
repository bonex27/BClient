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

    public String sName;
    Socket socket;
    Scanner input;
    public ArrayList<Boat> Boats = new ArrayList<Boat>();
    public Box refGrid[][];
    public Box refOpponent[][];
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
    public void setBoat(int x1,int x2,int y1,int y2,String boatName)
    {
        if(x1 == x2 || y1 == y2)
        {
            if(checkSpazio(x1,x2,y1,y2) == true)
            {
                for(int i = x1;i <= x2;i++)
                    {
                        for(int j = y1; i<y2;i++)
                        {
                            refGrid[i][j].contenuto = 'b';
                            refGrid[i][j].nomeBarca=boatName;
                        }
                    }
            }
            else//da settare errore per il client!
                System.out.println("Barche vicine!");
            }
        else
        {
            System.out.println("Errore, orizzontale o verticale");
        }

    }
    public boolean checkSpazio(int x1,int x2,int y1,int y2)//Controlla in tutte le 8 caselle vicine 
    {
     for(int i = x1;i <= x2;i++)
        {
            for(int j = y1; i<y2;i++)
            {
                if(   refGrid[i][j+1].contenuto != 'm' &&  
                      refGrid[i][j-1].contenuto != 'm' &&
                      refGrid[i+1][j+1].contenuto != 'm' &&
                      refGrid[i+1][j-1].contenuto != 'm' &&
                      refGrid[i+1][j].contenuto != 'm' &&  
                      refGrid[i-1][j+1].contenuto != 'm' &&
                      refGrid[i-1][j-1].contenuto != 'm' &&
                      refGrid[i-1][j].contenuto != 'm')
                    return false;                  
            }
        }   
    return true; // se posizione libera
    }
}
    

