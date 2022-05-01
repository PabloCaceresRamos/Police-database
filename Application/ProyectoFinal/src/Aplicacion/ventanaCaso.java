/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.caso;
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
 * JFrame para la ventana Caso
 * Se utiliza mostrar la tabla caso_policial
 * @author Pablo Caceres
 * @version 1.0
 */
public class ventanaCaso extends javax.swing.JFrame {

/**
 * Construstor
 * @param co conexionOracle
 * @param vP ventanaPrincipal
 * @throws SQLException excepcion base de datos
 */
    ventanaCaso(conexionOracle co,ventanaPrincipal vP) throws SQLException {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//hace que al cerrar no se termine el programa
        this.co=co;
        this.vP=vP;
        mCa=new manejaCaso(co);
        
        modeloTCaso = new DefaultTableModel();
        JTable TablaCaso=new JTable(modeloTCaso);
        
        dibujarTablaCaso();
    }
    /**
     * Dibuja la tabla de Caso en el JFrame
     * @throws SQLException Excepcion de la base de datos
     */
    private void dibujarTablaCaso() throws SQLException {
        tCaso.setModel(modeloTCaso);
        String[] columnasTabla={"Código","Nombre","Fecha_Inicio","Fecha_Fin"};
        modeloTCaso.setColumnIdentifiers(columnasTabla);
        tCaso.setColumnSelectionAllowed(false);//hace que el raton no pueda redimensionar la tabla
        
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(100);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);
        
        rellenarTabla(mCa.listaCasos());
    }
    
    
       /**
        * Vacia la tabla de caso
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        /**
         * Rellena la tabla con los casos que hay en la base de datos
         * @param list lista con los casos
         * @throws SQLException excepcion de la base de datos
         */
       private void rellenarTabla(ArrayList<caso> list) throws SQLException {
        Object[] columna=new Object[4];
        int numFilas=list.size();
         for (int i = 0; i < numFilas; i++) {
            columna[0] = list.get(i).getCodCaso();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getFechaInicio();
            columna[3] = list.get(i).getFechaFin();
             
             modeloTCaso.addRow(columna);
             
         }
    }
    /**
     * Se utiliza para que cuando se cierre la ventana se abra la ventana principal
     */  
    @Override
    public void dispose(){//metodo del boton cerrar
        vP.setVisible(true);
        super.dispose();
    }
     
    /**
     * Lo utilizaremos para trabajar con manejaCaso
     */  
    private manejaCaso mCa;
    /**
     * Lo utilizaremos para trabajar con manejaColabora 
     */
    private conexionOracle co;
    /**
     * Lo utilizaremos para trabajar con la tabla de casos en el frame 
     */
    private DefaultTableModel modeloTCaso;
    /**
     * Lo utilizaremos guardar la ventana principal para volver cuando
     * se cierra la ventanacaso
     */
    private ventanaPrincipal vP;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tCaso;
    // End of variables declaration//GEN-END:variables

   

    
}
