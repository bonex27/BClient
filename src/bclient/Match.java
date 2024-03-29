/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bclient;

import java.awt.EventQueue;
import java.beans.EventHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author informatica
 */
public class Match implements Runnable {

    Scanner in;
    PrintWriter out;
    public Scanner scanner = new Scanner(System.in);
    private String log, ArrOfStr[], init[];
    Socket socket;
    BN_GUI Jmatrice;
    int x, y;
    String verso;
    int iLung = 0;

    public Match(Socket socket, BN_GUI a) throws IOException {
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        this.socket = socket;
        Jmatrice = a;
        Jmatrice.buttonEnable("a");
    }

    public void place() {
        log = in.nextLine();
        ArrOfStr = log.split("@", 2);

        System.out.println("La barca " + ArrOfStr[1] + " da posizionare è lunga " + ArrOfStr[0]);
        iLung = Integer.parseInt(ArrOfStr[0]);
        verso = ArrOfStr[1];
        do {

            Jmatrice.label.setText("La barca " + " da posizionare è lunga " + ArrOfStr[0] + " orientata "+ ArrOfStr[1] );

            //System.out.println();
        } while (Jmatrice.l != true);
        if (Jmatrice.l == true) {

            log += "@" + Jmatrice.y + "@";
            log += Jmatrice.x;

            Jmatrice.l = false;
        }

        out.println(log);

    }

    private void attack() {

        do {

            Jmatrice.label.setText("inserisci la barca  che vuoi attaccare ");
            Jmatrice.labelError.setText("");
        } while (Jmatrice.l != true);

        if (Jmatrice.l == true) {

            log += "@" + Jmatrice.y + "@";
            y = Jmatrice.y;
            log += Jmatrice.x;
            x = Jmatrice.x;
            out.println(log);
        }
        Jmatrice.l = false;
        Jmatrice.label.setText("E' il tuo turno!");
        log = "";//reset dopo invio
    }

    /**
     *
     * @throws IOException
     */
    public void runi() throws IOException {
        do {
            try {
                log = in.nextLine();
                ArrOfStr = log.split("@");
                Jmatrice.lblPlayer.setText("Player " + ArrOfStr[0]);
                //Jmatrice.label.setText("Sei il giocatore " + ArrOfStr[0]);

                switch (ArrOfStr[1]) {
                    case "p":                               //Place
                        do {
                            place();
                            log = in.nextLine();
                            if (log.equals("NEAR")) {
                                Jmatrice.labelError.setText("Dati inseriti non accettabili, reinserire");
                            }
                        } while (log.equals("NEAR"));

                        Jmatrice.label.setText("Barca posizionata correttamente");
                        Jmatrice.insert(verso, iLung);

                        break;
                    case "a":                               //attack
                        do {

                            Jmatrice.buttonEnable("b");
                            if (ArrOfStr.length > 2) {
                                Jmatrice.grid(Integer.parseInt(ArrOfStr[3]), Integer.parseInt(ArrOfStr[2]), ArrOfStr[4]);
                            }
                            attack();
                            log = "";
                            log = in.nextLine();

                            switch (log) {
                                case "c":
                                    Jmatrice.label.setText("Barca colpita");
                                    Jmatrice.opponent(x, y, "c");

                                    break;
                                case "d":
                                    Jmatrice.label.setText("Barca distrutta");
                                    Jmatrice.opponent(x, y, "d");
                                    break;
                                case "m":
                                    Jmatrice.label.setText("Mancata");
                                    Jmatrice.opponent(x, y, "m");
                                    break;
                                case "gc":
                                    Jmatrice.label.setText("Barca già colpita");
                                    break;
                                case "f":
                                    System.out.println("Dati inseriti non accettabili, reinserire");
                                    break;
                            }

                        } while (log.equals("f"));

                        break;

                    case "v":
                        visual();
                        break;

                    case "lose":
                        Jmatrice.label.setText("Hai perso");
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Hai perso!");
                        this.socket.close();
                        break;
                    case "win":
                        Jmatrice.label.setText("Hai vinto!");
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Hai vinto!");
                        this.socket.close();
                        break;
                }
            } catch (Exception e) {
                System.out.println("err" + e);
                break;
            }
        } while (!ArrOfStr[1].equals("lose"));

        System.out.println("Gioco finito");
        this.socket.close();
    }

    public void visual() {
        out.println("stampa");
        System.out.println(in.nextLine().replaceAll("#", "\n"));
    }

    @Override
    public void run() {

    }

}
