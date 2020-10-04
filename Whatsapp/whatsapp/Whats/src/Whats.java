
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Whats extends javax.swing.JFrame implements Runnable{
    Socket soc;
    Scanner scan;   
    PrintWriter writer;
    public String cliente,destinatario;    
    public Whats() {
        try {
            initComponents();
            destinatario="Nayeli";
            soc = new Socket("127.0.0.1",8189);
            InputStream in=soc.getInputStream();
            OutputStream out=soc.getOutputStream();
            scan=new Scanner(in);
            writer=new PrintWriter(out,true);
            btn_Enviar.setVisible(false);
            cmb_Destino.setVisible(false);
            txtRecibido.setVisible(false);
            txt_Mensaje.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Whats.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_Mensaje = new javax.swing.JTextField();
        btn_Enviar = new javax.swing.JButton();
        cmb_Destino = new javax.swing.JComboBox<>();
        cmbUsuario = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRecibido = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_Mensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MensajeActionPerformed(evt);
            }
        });

        btn_Enviar.setText("Enviar");
        btn_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnviarActionPerformed(evt);
            }
        });

        cmb_Destino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ariadna", "Nayeli", "Rodrigo", "Abraham" }));
        cmb_Destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_DestinoActionPerformed(evt);
            }
        });

        cmbUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ariadna", "Nayeli", "Rodrigo", "Abraham" }));
        cmbUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuarioActionPerformed(evt);
            }
        });

        txtRecibido.setColumns(20);
        txtRecibido.setRows(5);
        jScrollPane1.setViewportView(txtRecibido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmb_Destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(txt_Mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btn_Enviar)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_Destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Enviar))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_MensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MensajeActionPerformed
        
    }//GEN-LAST:event_txt_MensajeActionPerformed

    private void cmb_DestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_DestinoActionPerformed
        destinatario=cmb_Destino.getSelectedItem().toString();
        System.out.println(destinatario);
    }//GEN-LAST:event_cmb_DestinoActionPerformed

    private void cmbUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuarioActionPerformed
        cliente=cmbUsuario.getSelectedItem().toString();
        writer.println(cliente);   
        //No dejar que puche para si mismo
        for(int i=0;i<4;i++){
            if(cliente.equals(cmb_Destino.getItemAt(i)))                
                cmb_Destino.removeItemAt(i);
        }
        
        btn_Enviar.setVisible(true);
        cmb_Destino.setVisible(true);
        txtRecibido.setVisible(true);
        txt_Mensaje.setVisible(true);
        cmbUsuario.setEnabled(false);
    }//GEN-LAST:event_cmbUsuarioActionPerformed

    private void btn_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviarActionPerformed
       String mensaje;
       mensaje=txt_Mensaje.getText();
       writer.println(destinatario+","+mensaje);    
       txtRecibido.append("Tú: "+mensaje+"\n");
       txt_Mensaje.setText("");
    }//GEN-LAST:event_btn_EnviarActionPerformed

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
            java.util.logging.Logger.getLogger(Whats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Whats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Whats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Whats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            Thread l;
            Whats w;
            public void run() {
                w=new Whats();
                w.setVisible(true);                
                l=new Thread(w) ;
                l.start();
            }
        });        
        

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Enviar;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JComboBox<String> cmb_Destino;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtRecibido;
    private javax.swing.JTextField txt_Mensaje;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        String z="";
        while(scan.hasNextLine()){
            z=scan.nextLine();
            String[] items=z.split(",");
            txtRecibido.append(items[0]+": "+items[1]+", "+items[2]+"\n");
        }
    }
}
