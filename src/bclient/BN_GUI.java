package bclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author admirportatile
 */
public class BN_GUI implements MouseListener {

    JFrame fram = new JFrame("Battaglia navale"); //creates frame
    JButton[][] grid; //names the grid of button
    JPanel panel = new JPanel();
    JPanel oponent = new JPanel();
    JButton[][] opo;
    int x, y;
    String name;
    String giocatore = "a";
    boolean l = false;
    JLabel label = new JLabel();
    JLabel labelError = new JLabel();
    JLabel lblPlayer = new JLabel();

    public BN_GUI(int width, int length) {
        setup(width, length);
    }

    public void setup(int width, int length) {
        oponent.setPreferredSize(new Dimension(423, 300));
  
        panel.setPreferredSize(new Dimension(423, 300));
        grid = new JButton[width][length]; //allocate the size of grid
        opo = new JButton[width][length];
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new JButton();
                grid[y][x].setPreferredSize(new Dimension(15, 15));
                grid[y][x].setName(y + " " + x + " " + "p");
                opo[y][x] = new JButton(); //creates new button
                opo[y][x].setPreferredSize(new Dimension(15, 15));
                opo[y][x].setName(y + " " + x + " " + "o");
                grid[y][x].addMouseListener(this);
                opo[y][x].addMouseListener(this);
                panel.add(grid[y][x]); //adds button to grid
                oponent.add(opo[y][x]);
            }
        }
        fram.setResizable(false);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram.setPreferredSize(new Dimension(1024, 490));
        fram.add(panel, BorderLayout.WEST);
        fram.add(oponent, BorderLayout.EAST);
        fram.add(label, BorderLayout.NORTH);
        fram.add(labelError, BorderLayout.SOUTH);
        fram.add(lblPlayer, BorderLayout.CENTER);
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
        String[] indici = command.split(" ");
        x = Integer.parseInt(indici[0]);
        y = Integer.parseInt(indici[1]);
        name = indici[2];
        l = true;
    }
    public void finalState()
    {
        
        JOptionPane.showMessageDialog(new JFrame(),
    "Hai vinto!");
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {

    }

    public String buttonEnable(String a) {
        giocatore = a;
        for (int y = 0; y < 21; y++) {
            for (int x = 0; x < 21; x++) {
                if (a.equals("a")) {

                    opo[y][x].setEnabled(false);
                    grid[y][x].setEnabled(true);

                } else if (a.equals("b")) {
                    grid[y][x].setEnabled(false);
                    opo[y][x].setEnabled(true);
                }
            }
        }
        return a;
    }

    public void insert(String a, int iLung) {
        int Xa = x;
        int Ya = y;
        if (a.equals("o")) {
            for (int i = Ya; i < Ya + iLung; i++) {
                grid[x][i].setBackground(Color.yellow);

            }

        } else if (a.equals("v")) {
            for (int g = Xa; g < Xa + iLung; g++) {
                grid[g][y].setBackground(Color.yellow);
            }

        }
    }

    public void opponent(int a, int b, String PosAttack) {
        if (PosAttack.equals("d") || PosAttack.equals("c")) {
            opo[a][b].setBackground(Color.RED);
        } else if (PosAttack.equals("m")) {
            opo[a][b].setBackground(Color.BLUE);
        }
    }

    public void grid(int a, int b, String PosAttack) {

        if (PosAttack.equals("d") || PosAttack.equals("c")) {
            grid[a][b].setBackground(Color.RED);
        } else if (PosAttack.equals("m")) {
            grid[a][b].setBackground(Color.BLUE);
        }

    }
}
