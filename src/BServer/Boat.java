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
    public Game.Player p;
    public ArrayList<Box> bPosizione;

    public Boat(int iLunghezza, Game.Player p, Box[] bPosizione) {
        this.iLunghezza=iLunghezza;
        this.p = p;
        this.bPosizione =new ArrayList <Box>();
    }
    
}
