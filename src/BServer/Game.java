/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BServer;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author informatica
 */
public class Game implements Runnable{

    public String sName;
        Socket socket;
        Scanner input;

        PrintWriter output;
        public Game(Socket socket, String sName) 
        {
            this.socket = socket;
            this.sName = sName;
            
        }

        @Override
        public void run() 
        {
            try
            {
                input = new Scanner(socket.getInputStream());
                output = new PrintWriter(socket.getOutputStream(),true);
                while(input.hasNextLine())
                {
                    output.println("Shipe");
                }

            }
            catch(Exception e)
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
                    
                }
            }
        }
    }
    

