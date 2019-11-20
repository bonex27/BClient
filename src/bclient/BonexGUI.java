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
public class BonexGUI extends javax.swing.JFrame {

    String mess = "";
    
    public Scanner in; 
    public PrintWriter out; 
    public Scanner scanner = new Scanner(System.in);
    private String log, ArrOfStr[], init[];
    Socket socket;
    /**
     * Creates new form BonexGUI
     */
    public BonexGUI()  {
        initComponents();
        this.txtOutput.append("Inserisci l'indirizzo ip o il dominio del server\n");       
    }
    public void connect(String a)
    {
        try{
            socket = new Socket(this.lblInput.getText() , 6012);          
            this.txtOutput.append("Connesso al server di gioco\n");
            this.run();
        }   
        catch (IOException ex) {
            this.txtOutput.setText("Errore connessione" + ex);
        }
    }   
    public void place(){
        log = in.nextLine();
        ArrOfStr = log.split("@",2);
        
        System.out.println("La barca "+ArrOfStr[1]+ " da posizionare è lunga "+ ArrOfStr[0] );
        
        System.out.println("Inserisci la x(0-20):");
        log = scanner.nextLine()+"@";
        System.out.println("Inserisci la y(0-20):");
        log += scanner.nextLine();
        
        
        System.out.println("In verticale(v) o in orizzontale(o):");
        log += "@" + scanner.nextLine();
             
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
    
    public void run() throws IOException{
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        do{
        
            try{
                log=in.nextLine();
                ArrOfStr=log.split("@",2);
                this.lblPlayer.setText("Player " + ArrOfStr[0]);
                this.txtOutput.append("Sei il giocatore " + ArrOfStr[0]+"\n");
                this.txtOutput.revalidate();
                switch(ArrOfStr[1]){
                    case "p":                               //Place
                        do{
                            place();
                            log=in.nextLine();
                            if(log.equals("NEAR"))
                                System.out.println("Dati inseriti non accettabili, reinserire");
                        }while(log.equals("NEAR"));

                        System.out.println("Barca posizionata correttamente");

                        break;

                    case "w":                               //wait
                        System.out.println("Turno dell'avversario");
                        break;

                    case "a":                               //attack
                        do{
                            attack();
                            log=in.nextLine();

                            switch (log) {
                                case "c":
                                    System.out.println("Barca colpita");
                                    break;
                                case "d":
                                    System.out.println("Barca distrutta");
                                    break;
                                case "m":
                                    System.out.println("Barca mancata");
                                    break;
                                case "gc":
                                    System.out.println("Barca già colpita");
                                    break;
                                case "f":
                                    System.out.println("Dati inseriti non accettabili, reinserire");
                                    break;
                                case "win":
                                    System.out.println("Hai vinto");
                                    this.socket.close();
                                    break;
                            }

                        }while(log.equals("f"));
                        
                        break;

                    case "v":
                        visual();
                        break;
                                                  
                    case "lose":
                        System.out.println("Hai perso");
                        break;
                }
            }
            catch(Exception e){
                System.out.println("err" + e);
                break;
            }
        }while(!ArrOfStr[1].equals("lose"));
        
        System.out.println("Gioco finito");
        this.socket.close();
    }
    
    public void visual(){
        out.println("stampa");
        System.out.println(in.nextLine().replaceAll("#", "\n"));
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();
        lblInput = new javax.swing.JTextField();
        lblPlayer = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnOk.setText("ok");
        btnOk.setName("btnOk"); // NOI18N
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblInput.setName("lblInput"); // NOI18N
        lblInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblInputActionPerformed(evt);
            }
        });

        lblPlayer.setText("Player ");
        lblPlayer.setName("lblPlayerName"); // NOI18N

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        txtOutput.setEnabled(false);
        txtOutput.setName("txtMsgServer"); // NOI18N
        jScrollPane3.setViewportView(txtOutput);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel3.setText("Battaglia navale");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblInput)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblPlayer)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(0, 120, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(btnOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlayer)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.connect(this.lblInput.getText()); 
    }//GEN-LAST:event_btnOkActionPerformed

    private void lblInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(BonexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BonexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BonexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BonexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BonexGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lblInput;
    private javax.swing.JLabel lblPlayer;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
