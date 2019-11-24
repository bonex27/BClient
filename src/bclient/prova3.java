package bclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static javafx.beans.binding.Bindings.length;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author admirportatile
 */
public class prova3 implements MouseListener
{
        JFrame fram=new JFrame(); //creates frame
        JButton[][] grid; //names the grid of button
        JPanel panel=new JPanel();
        JPanel oponent =new JPanel();
        JButton[][] opo;
        int x,y;
        String name;
        String giocatore="a";
 
       
 
        
        
    public prova3(int width,int length) 
    {
     
       
       setup(width,length);
      }
                
    public void setup(int width,int length)
        {
             oponent.setPreferredSize(new Dimension(423,300));
             //panel.setLayout(new GridLayout(width,length)); //set layout
             panel.setPreferredSize(new Dimension(423,300));
             grid=new JButton[width][length]; //allocate the size of grid
             opo=new JButton[width][length]; 
       for(int y=0; y<length; y++){
		for(int x=0; x<width; x++){
                   grid[y][x]=new JButton();
                   grid[y][x].setPreferredSize(new Dimension(15,15));
                   grid[y][x].setName(x+" "+y+" "+"p");
                   opo[y][x]=new JButton(); //creates new button
                   opo[y][x].setPreferredSize(new Dimension(15,15));
                   opo[y][x].setName(x+" "+y+" "+"o");
                   grid[y][x].addMouseListener(this);
                   opo[y][x].addMouseListener(this);
		   panel.add(grid[y][x]); //adds button to grid
                   oponent.add(opo[y][x]);
                                            }
                                   }
   
                
              fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              fram.setPreferredSize(new Dimension(1024, 490));
              fram.add(panel,BorderLayout.WEST);
              fram.add(oponent,BorderLayout.EAST);
              fram.setVisible(true);
              fram.pack();
              fram.show();
        }

    @Override
    public void mouseClicked(MouseEvent me) {
       
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
           
          String command = ((JButton) me.getSource()).getName();
            String[] indici=command.split(" ");
             y=Integer.parseInt(indici[0]);
             x=Integer.parseInt(indici[1]);
             name=indici[2];
            
              if(giocatore.equals("a")&&name.equals("p"))
              {
                     grid[x][y].setBackground(Color.yellow);
              }
              else if(giocatore.equals("b")&&name.equals("o"))
              {
                     opo[x][y].setBackground(Color.yellow);
              }
            
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
   
    public String buttonEnable(String a)
    {
        for(int y=0; y<21; y++){
		for(int x=0; x<21; x++){
                    if(a.equals("a"))
                    {
                        
                        opo[y][x].setEnabled(false);
                        grid[y][x].setEnabled(true);
                        
                    }
                    else if(a.equals("b"))
                    {
                        grid[y][x].setEnabled(false);
                        opo[y][x].setEnabled(true);
                    }
                }
                }
        return a;
    }
}