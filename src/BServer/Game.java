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
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bonex & Admir M.
 */
public class Game implements Runnable{

    private char sName;
    Socket socket;
    Scanner input;
    PrintWriter output;
    private ArrayList<Boat> Boats = new ArrayList<Boat>();
    private Box refGrid[][];
    private Box refOpponent[][];
    

    String[] arrOfStr;
    
    public Game(Socket socket, char sName,Box refGrid[][],Box refOpponent[][],ArrayList<Boat> Boats) 
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
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(),true);
            System.out.println(sName);
            setup();           
        }                 
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private void showMatrix()
    {
        String a = "";
        for(int i = 0; i<21;i++)
         {
             for(int j = 0; j<21;j++)
            {
                a += refGrid[i][j].contenuto+"|";
                
            }
             
             a+="#";
         } 
       // output.println(a);
        output.println(a);
    }
    
            
    private String setBoat(int x,int y,char cOr,String boatName,Boat b)
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
    
    
    public boolean attackBoat(int x,int y)
    {
        //for(int i=0;i<)
       if(refOpponent[x][y].contenuto=='b'&& refOpponent[x][y].contenuto=='m')
         {
            refOpponent[x][y].contenuto='d';
            
               return true;
         }
        else if(refOpponent[x][y].contenuto=='d')
         {
                return false;
          }
           
        return false;
    }
  
    public void setup() throws IOException
    {
        String comando;
        //System.out.println(this.sName+ " connesso!");
         
            
            for(int i = 0; i < Boats.size();i++)
                {
                    output.println(this.sName+"@p");//client
                    output.println(Boats.get(i).iLunghezza+"@"+Boats.get(i).nome);
                    comando = input.nextLine();
                    arrOfStr= comando.split("@", 10);
                    this.setBoat(Integer.parseInt(arrOfStr[0]),Integer.parseInt(arrOfStr[1]),arrOfStr[2].charAt(0),Boats.get(i).nome,Boats.get(i));
                   setup();
                }
            
       
    }
    //controllo il turno del giocatore e se hai un avversario


}
    

