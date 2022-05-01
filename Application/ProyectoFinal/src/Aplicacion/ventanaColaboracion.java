/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.conexionOracle;
import Persistencia.experto;
import Persistencia.manejaCaso;
import Persistencia.manejaColabora;
import Persistencia.manejaExperto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame para la ventana Colaboracion
 * Se utiliza trabajar graficamente con la tabla Colaboracion
 * @author Pablo Caceres
 * @version 1.0
 */
public class ventanaColaboracion extends javax.swing.JFrame {

    /**
 * Construstor
 * @param co conexionOracle
 * @param vP ventanaPrincipal
 * @throws SQLException excepcion base de datos
 */
    ventanaColaboracion(conexionOracle co,ventanaPrincipal vP) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//hace que al cerrar no se termine el programa
        this.co=co;
        mExp=new manejaExperto(co);
        mCo=new manejaColabora(co);
        this.vP=vP;
        
        modeloTCaso = new DefaultTableModel();
        JTable TablaCaso=new JTable(modeloTCaso);
        
        dibujarTablaColaboracion();
    }
    /**
     * Dibuja la tabla de colaboraciones en el JFrame
     */
    private void dibujarTablaColaboracion() {
        tCaso.setModel(modeloTCaso);
        String[] columnasTabla={"Código","Nombre","Especialidad","Colaboración"};
        modeloTCaso.setColumnIdentifiers(columnasTabla);
        tCaso.setColumnSelectionAllowed(false);//hace que el raton no pueda redimensionar la tabla
        
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(100);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(150);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(150);
    }
    /**
     * Vacia la tabla de colaboraciones
     */
    private void vaciarTabla(){
        while(modeloTCaso.getRowCount()>0){
            modeloTCaso.removeRow(0);
        }
    }
    
  
    

    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tCaso = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cCaso = new javax.swing.JTextField();
        botonMostrar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tCaso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tCaso);

        jLabel1.setText("Introduce el Código de un Caso");

        cCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCasoActionPerformed(evt);
            }
        });

        botonMostrar.setText("Mostrar");
        botonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(cCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonMostrar)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonMostrar))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        /**
         * Rellena la tabla con las colaboraciones pasadas por parametro
         * @param list lista de colaboraciones
         * @throws SQLException excepcion de la base de datos
         */
       private void rellenarTabla(ArrayList<String[]> list) throws SQLException {
        Object[] columna=new Object[4];
        int numFilas=list.size();
         for (int i = 0; i < numFilas; i++) {
             String[] sAux=list.get(i);
             columna[0]=sAux[0];
             columna[1]=sAux[1];
             columna[2]=sAux[2];
             columna[3]=sAux[3];
             modeloTCaso.addRow(columna);
             
         }
    }
    /**
     * Rellena la tabla con los expertos que pertenecen a el caso introducido en
     * el jFrame
     * @param evt click del raton 
     */   
    private void botonMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrarActionPerformed
         
        try {
            ArrayList<String[]> list=mCo.getExpDeCaso(cCaso.getText());
            vaciarTabla();
            rellenarTabla(list);
            
        } catch (SQLException ex) {
            Logger.getLogger(ventanaExpertos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonMostrarActionPerformed

    /**
     * Se utiliza para que cuando se cierre la ventana se abra la ventana principal
     */
    @Override
    public void dispose(){//metodo del boton cerrar
        vP.setVisible(true);
        super.dispose();
    }
    
    
    private void cCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCasoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cCasoActionPerformed
/**
     * Lo utilizaremos para poder trabajar con manejaExperto 
     */
    private manejaExperto mExp;
    /**
     * Lo utilizaremos para trabajar con manejaColabora
     */
    private manejaColabora mCo;
    /**
     * Lo utilizaremos para guardar la conexionOracle
     */
    private conexionOracle co;
    /**
     * Lo utilizaremos guardar la ventana principal para volver cuando
     * se cierra la ventanaColabora
     */
    private ventanaPrincipal vP;
    /**
     * Lo utilizaremos para trabajar con la tabla de caso en el frame
     */
    private DefaultTableModel modeloTCaso;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton botonMostrar;
    private javax.swing.JTextField cCaso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tCaso;
    // End of variables declaration//GEN-END:variables

   

    
}
