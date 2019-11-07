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
 * @author informatica
 */
public class Game implements Runnable{

    public String sName;
    Socket socket;
    Scanner input;
    public ArrayList<Boat> Boats = new ArrayList<Boat>();;
    public Box refGrid[][];

        PrintWriter output;
        public Game(Socket socket, String sName,Box refGrid[][],ArrayList<Boat> Boats) 
        {
            this.socket = socket;
            this.sName = sName;
            this.refGrid = refGrid;
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
            for(int i = x1;i <= x2;i++)
            {
                for(int j = y1; i<y2;i++)
                {
                  refGrid[i][j].contenuto = 'b';
                  refGrid[i][j].nomeBarca=boatName;
                }
            }
        }
    }
    

