/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.conexionOracle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * JFrame para la ventana Principal
 * Se utiliza para tener acceso a las otras ventanas
 * @author Pablo Caceres
 * @version 1.0
 */
public class ventanaPrincipal extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public ventanaPrincipal() {
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        vSubMenuExpertos = new javax.swing.JMenu();
        vSubMenuExperto = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Conexion   ");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Salir");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        jMenuBar1.add(jMenu1);

        vSubMenuExpertos.setText("   Expertos    ");

        vSubMenuExperto.setSelected(true);
        vSubMenuExperto.setText("Gestion experto");
        vSubMenuExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vSubMenuExpertoActionPerformed(evt);
            }
        });
        vSubMenuExpertos.add(vSubMenuExperto);

        jMenuBar1.add(vSubMenuExpertos);

        jMenu5.setText("    Casos ");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Gestion Casos");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu5);

        jMenu2.setText("     Colaboraciones");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Gestion de Colaboraciones");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("   Gesti??n Completa");

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("Gesti??n Completa");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu6.add(jRadioButtonMenuItem3);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Boton de cerrar la aplicacion
     * @param evt click del raton
     */
    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        try {
            co.desconexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            this.dispose();
        }
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed
    /**
     * Abre la ventana de experto
     * @param evt click del raton
     */
    private void vSubMenuExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vSubMenuExpertoActionPerformed
        ventanaExpertos vE = null;
        try {
            vE = new ventanaExpertos(co,this);
        } catch (SQLException ex) {
            Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        vE.setVisible(true);
        //hacer que aparezca en el medio de la pantalla
        //hacer que al cerrar, no se cierre el programa entero
        this.setVisible(false);
    }//GEN-LAST:event_vSubMenuExpertoActionPerformed
    /**
     * Abre la ventana Colaboracion
     * @param evt 
     */
    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
       ventanaColaboracion vC = null;
       vC = new ventanaColaboracion(co,this);
        vC.setVisible(true);
        //hacer que aparezca en el medio de la pantalla
        //hacer que al cerrar, no se cierre el programa entero
        this.setVisible(false);
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed
    /**
     * Abre la ventana de Caso
     * @param evt Click del raton
     */
    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
       ventanaCaso vC = null;
        try {
            vC = new ventanaCaso(co,this);
        } catch (SQLException ex) {
            Logger.getLogger(ventanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       vC.setVisible(true);
        //hacer que aparezca en el medio de la pantalla
        //hacer que al cerrar, no se cierre el programa entero
        this.setVisible(false);
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed
    /**
     * Abre la ventana Gestion Completa
     * @param evt 
     */
    private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem3ActionPerformed
        GestionCompleta gC=null;
        try {
            gC=new GestionCompleta(co,this);
            gC.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            System.out.println("Error Gestion Completa!!!!!!!");
        }
        
    }//GEN-LAST:event_jRadioButtonMenuItem3ActionPerformed

    /**
     * main del programa que lo inicializa todo
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
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaPrincipal().setVisible(true);
            }
        });
        try {
            co = new conexionOracle();
          //  initComponents();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(1);
        }
    }
    /**
     * Lo utilizaremos para guardar la conexionOracle
     */
    static conexionOracle co;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem vSubMenuExperto;
    private javax.swing.JMenu vSubMenuExpertos;
    // End of variables declaration//GEN-END:variables
}
