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
 * @param iLunghezza
 * Boat lenght
 * @param bPosizione
 * Box occuped by this boat
 * 
 */
public class Boat {
    public  int iLunghezza ;
    public ArrayList<Box> bPosizione;

    public Boat(int iLunghezza) {
        this.iLunghezza=iLunghezza;
        this.bPosizione = new ArrayList <Box>();
    }
    
    /**
     * @param a
     * Boat how need check
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
