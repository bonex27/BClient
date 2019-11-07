/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BServer;

import java.util.ArrayList;

/**
 *
 * @author pbone
 */
public class Boat {
    public  int iLunghezza ;
    public Game p;
    public ArrayList<Box> bPosizione;

    public Boat(int iLunghezza, Game p, Box[] bPosizione) {
        this.iLunghezza=iLunghezza;
        this.p = p;
        this.bPosizione =new ArrayList <Box>();
    }
    
    /**
    *@code Check if a boat is alive and if is true it return  true
    */
    public boolean checkCondizione(Boat a)
    {
        for(int i = 0; i < a.iLunghezza;i++)
        {
            if(a.bPosizione.get(i).contenuto == 'm')
            {
                return true;
            }   
        }
        return false;
    }
    
}
