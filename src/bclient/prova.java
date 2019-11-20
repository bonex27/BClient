package bclient;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
/*
public void place2(){
        ImageIcon h2o = new ImageIcon ("/home/informatica/Downloads/H2O.jpg");
        ImageIcon SafeBoat = new ImageIcon ("/home/informatica/Downloads/H2O.jpg");
        ImageIcon CrashBoat = new ImageIcon ("/home/informatica/Downloads/H2O.jpg");

        JPanel grid = new JPanel();
        
        System.out.println("La barca da posizionare è lunga "+ 1 );
        
        System.out.println("Inserisci la x(0-20):");
        init[0] = scanner.nextLine();
        System.out.println("Inserisci la y(0-20):");
        init[1] = scanner.nextLine();
        
        grid.setLayout(new GridLayout(21, 21));
        for (int i = 0; i < 21; i++) {
            for (int n = 0; n < 21; n++) {
                if(Integer.parseInt(init[0])==i && Integer.parseInt(init[1])==n)
                    grid.add(new JLabel(SafeBoat));
                else
                    grid.add(new JLabel(h2o));
                                
            }
        }
        
        JFrame frame = new JFrame("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(640, 400));
        frame.add(grid);
        frame.pack();
        frame.setVisible(true);
    }
*/
public class prova extends JFrame {

private static final long serialVersionUID = 1L;

    
    public static void main(String[] args) {
        ImageIcon h2o = new ImageIcon ("/home/informatica/Downloads/H2O.jpg");
        ImageIcon SafeBoat = new ImageIcon ("/home/informatica/Downloads/H2O.jpg");
        ImageIcon CrashBoat = new ImageIcon ("/home/informatica/Downloads/H2O.jpg");

        JPanel grid = new JPanel();
                
        grid.setLayout(new GridLayout(21, 21));
        for (int i = 0; i < 21; i++) {
            for (int n = 0; n < 21; n++) {
                grid.add(new JLabel(h2o));
                
                
                
            }
        }
        JFrame frame = new JFrame("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(640, 400));
        frame.add(grid);
        frame.pack();
        frame.setVisible(true);
    }
}