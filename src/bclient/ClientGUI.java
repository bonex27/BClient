/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author pbone
 */
public class ClientGUI extends javax.swing.JFrame {

    /**
     * Creates new form ClientGUI
     */
    public ClientGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setName("txtOut"); // NOI18N

        jButton2.setText("Ok");
        jButton2.setToolTipText("");
        jButton2.setActionCommand("Ok");
        jButton2.setName("btnOk"); // NOI18N

        jLabel1.setText("Battaglia Navale");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
            }
        }); 
        try
        {
            Socket socket = new Socket("127.0.0.1" , 6012);
        }
        catch(Exception e)
        {
            System.out.println("Socket error " + e);
        }
    }

    Scanner in; 
    PrintWriter out; 
    public Scanner scanner = new Scanner(System.in);
    private String log;
    private String ArrOfStr[];
    
    public void Match(Socket socket) throws IOException{
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }    
     
    public void place(){
        log = in.nextLine();
        ArrOfStr = log.split("@",2);
        
        System.out.println("La barca "+ArrOfStr[1]+ " da posizionare è lunga "+ ArrOfStr[0] );
        
        System.out.println("Inserisci la x(0-20):");
        log = scanner.nextLine()+"@";
        System.out.println("Inserisci la y(0-20):");
        log += scanner.nextLine()+"@";
        System.out.println("In verticale(v) o in orizzontale(o):");
        log += scanner.nextLine();
        
        out.println(log);
    }
    private void attack(){
        System.out.println("Inserisci le cordinate per l'attacco");
        
        System.out.println("Inserisci la x(0-20):");
        log = scanner.nextLine()+"@";
        System.out.println("Inserisci la y(0-20):");
        log += scanner.nextLine();
        
        out.println(log);
        log="";//reset dopo invio
    }
    
    public void run(){
        while(true)
        {
            log=in.nextLine();
            ArrOfStr=log.split("@",2);
            
            switch(ArrOfStr[1]){
                case "p":                               //Place
                    do{
                        place();
                        log=in.nextLine();
                        if(log.equals("NEAR"))
                            System.out.println("Dati inseriti non accettabili, reinserire");
                    }while(log.equals("NEAR"));
                    
                    System.out.println("Barca posizionata correttamente");
                    //visual();
                    
                    break;
                    
                case "w":                               //wait
                    System.out.println("Turno dell'avversario");
                    break;
                    
                case "a":                               //attack
                    do{
                        attack();
                        log=in.nextLine();
                        if(log.equals("c"))
                            System.out.println("Barca colpita");
                        else if(log.equals("d"))
                            System.out.println("Barca distrutta");
                        else if(log.equals("m"))
                            System.out.println("Barca mancata");
                        else if(log.equals("gc"))
                            System.out.println("Barca già colpita");
                        else if(log.equals("f"))
                            System.out.println("Dati inseriti non accettabili, reinserire");
                    }while(log.equals("f"));
                    break;
                    
                case "v":
                    visual();
                    break;
            }
        }
    }
    
    public void visual(){
        out.println("stampa");
        System.out.println(in.nextLine().replaceAll("#", "\n"));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
