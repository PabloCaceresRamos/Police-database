
package Aplicacion;

import Persistencia.caso;
import Persistencia.colabora;
import Persistencia.conexionOracle;
import Persistencia.experto;
import Persistencia.manejaCaso;
import Persistencia.manejaColabora;
import Persistencia.manejaExperto;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame para la ventana GestionCompleta
 * Se utiliza trabajar graficamente con toda la base de datos
 * @author Pablo Caceres
 * @version 1.0
 */
public class GestionCompleta extends javax.swing.JFrame {

    /**
     * Contructor de la clase
     * @param co conexion oracle
     * @param vP ventana principal
     * @throws SQLException excepcion de la base de datos
     */
    public GestionCompleta(conexionOracle co, ventanaPrincipal vP) throws SQLException {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.co = co;
        mExp = new manejaExperto(co);
        mCa = new manejaCaso(co);
        mCo = new manejaColabora(co);
        this.vP = vP;
        //ajustar tabla experto
        modeloTExpertos = new DefaultTableModel();
        JTable tExpertos = new JTable(modeloTExpertos);
        dibujarTablaExpertos();
        pideExpertos();
        //ajustar tabla Caso
        modeloTCaso = new DefaultTableModel();
        JTable TablaCaso = new JTable(modeloTCaso);
        dibujarTablaCaso();

        //ajustar tabla Colaboracion
        modeloTColaboracion = new DefaultTableModel();
        JTable TablaColaboracion = new JTable(modeloTColaboracion);
        dibujarTablaColaboracion();

    }
    /**
     * Configuracion de la tabla de experto del JFrame
     */
    void dibujarTablaExpertos() {
        tExpertos.setModel(modeloTExpertos);
        String[] columnasTablas = {"Código", "Nombre", "País", "Sexo", "Especialidad"};
        modeloTExpertos.setColumnIdentifiers(columnasTablas);
        // Para no permitir el redimensionamiento de las columnas con el ratón
        tExpertos.getTableHeader().setResizingAllowed(false);
        // Así se fija el ancho de las columnas
        tExpertos.getColumnModel().getColumn(0).setPreferredWidth(25);
        tExpertos.getColumnModel().getColumn(1).setPreferredWidth(140);
        tExpertos.getColumnModel().getColumn(2).setPreferredWidth(80);
        tExpertos.getColumnModel().getColumn(3).setPreferredWidth(8);
        tExpertos.getColumnModel().getColumn(4).setPreferredWidth(122);
    }
    /**
     * Rellena la tabla de experto con los datos de la base de datos
     * @param expertos Lista con todos los expertos de la base de datos
     */
    private void rellenarTablaExpertos(ArrayList<experto> expertos) {
        Object[] columna = new Object[5];
        int numRegistros = expertos.size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = expertos.get(i).getCodExperto();
            columna[1] = expertos.get(i).getNombre();
            columna[2] = expertos.get(i).getPais();
            columna[3] = expertos.get(i).getSexo();
            columna[4] = expertos.get(i).getEspecialidad();
            modeloTExpertos.addRow(columna);
        }
    }
    /**
     * Elimina todos los datos de la tabla de JFrame
     */
    private void vaciarTablaExpertos() {
        while (modeloTExpertos.getRowCount() > 0) {
            modeloTExpertos.removeRow(0);
        }
    }
    /**
     * Pide todos los expertos que hay en la base de datos para luego
     * rellenar la tabla con ellos
     * @throws SQLException 
     */
    private void pideExpertos() throws SQLException {
        ArrayList<experto> lExp = mExp.listaExpertos();
        rellenarTablaExpertos(lExp);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////caso/////////////////////////////////////////////////
    /**
     * Dibuja la tabla de caso en el JFrame
     * @throws SQLException Excepcion de la base e datos
     */
    private void dibujarTablaCaso() throws SQLException {
        tCaso.setModel(modeloTCaso);
        String[] columnasTabla = {"Código", "Nombre", "Fecha_Inicio", "Fecha_Fin"};
        modeloTCaso.setColumnIdentifiers(columnasTabla);
        tCaso.setColumnSelectionAllowed(false);//hace que el raton no pueda redimensionar la tabla

        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(100);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tCaso.getColumnModel().getColumn(0).setPreferredWidth(25);

        rellenarTablaCaso(mCa.listaCasos());
    }
    /**
     * Elimina todos los datos que esta en la tabla de caso
     */
    private void vaciarTablaCaso() {
        while (modeloTCaso.getRowCount() > 0) {
            modeloTCaso.removeRow(0);
        }
    }
    /**
     * Rellena la tabla de casos con los casos que hay en la base de datos
     * @param list Lista con los casos
     * @throws SQLException 
     */
    private void rellenarTablaCaso(ArrayList<caso> list) throws SQLException {
        Object[] columna = new Object[4];
        int numFilas = list.size();
        for (int i = 0; i < numFilas; i++) {
            columna[0] = list.get(i).getCodCaso();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getFechaInicio();
            columna[3] = list.get(i).getFechaFin();

            modeloTCaso.addRow(columna);

        }
    }

    //////////////////////////////////////////////////////////////////////////////Colaboracion/////////////////////////////////////////////////////
    /**
     * Dibuja la tabla de configuracion del JFrame
     * @throws SQLException excepcion de la base de datos
     */
    private void dibujarTablaColaboracion() throws SQLException {
        tColaboracion.setModel(modeloTColaboracion);
        String[] columnasTabla = {"Experto", "Caso", "Fecha", "Descripcion"};
        modeloTColaboracion.setColumnIdentifiers(columnasTabla);
        tColaboracion.setColumnSelectionAllowed(false);//hace que el raton no pueda redimensionar la tabla

        tColaboracion.getColumnModel().getColumn(0).setPreferredWidth(25);
        tColaboracion.getColumnModel().getColumn(0).setPreferredWidth(100);
        tColaboracion.getColumnModel().getColumn(0).setPreferredWidth(25);
        tColaboracion.getColumnModel().getColumn(0).setPreferredWidth(150);

        rellenarTablaColaboracion(mCo.listaColabora());
    }
    /**
     * Elimina todos los datos que hay en la tabla Colaboraciones del JFrame
     */
    private void vaciarTablaColaboracion() {
        while (modeloTColaboracion.getRowCount() > 0) {
            modeloTColaboracion.removeRow(0);
        }
    }
    /**
     * Rellena la tabla de Colaboraciones con los datos que hay 
     * en la base de datos
     * @param list Lista de colaboraciones
     * @throws SQLException excepcion de la base de datos
     */
    private void rellenarTablaColaboracion(ArrayList<colabora> list) throws SQLException {
        Object[] columna = new Object[4];
        int numFilas = list.size();
        for (int i = 0; i < numFilas; i++) {
            colabora col = list.get(i);
            columna[0] = col.getCodExperto();
            columna[1] = col.getCodCaso();
            columna[2] = col.getFecha();
            columna[3] = col.getDescripcionColaboracion();
            modeloTColaboracion.addRow(columna);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        codigoE = new javax.swing.JTextField();
        nombreE = new javax.swing.JTextField();
        paisE = new javax.swing.JTextField();
        sexoE = new javax.swing.JTextField();
        especialidadE = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        codigoCa = new javax.swing.JTextField();
        nombreCa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tExpertos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tCaso = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        InsertarExp = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tColaboracion = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        codigoECo = new javax.swing.JTextField();
        codigoCaCo = new javax.swing.JTextField();
        descripcionCo = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Código");

        jLabel2.setText("Nombre");

        jLabel3.setText("País");

        jLabel4.setText("Sexo");

        jLabel5.setText("Especialidad");

        jLabel6.setText("Código");

        jLabel7.setText("Nombre");

        jLabel8.setText("Fecha Inicio");

        jLabel9.setText("Fecha Final");

        tExpertos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tExpertos);

        jLabel10.setText("Listado de Expertos");

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
        jScrollPane2.setViewportView(tCaso);

        jLabel11.setText("Listado de Casos Policiales");

        jButton1.setText("Eliminar Caso");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar Experto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        InsertarExp.setText("Insertar Experto");
        InsertarExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarExpActionPerformed(evt);
            }
        });

        jButton4.setText("Insertar Caso Policial");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Listar Todo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Limpiar Todo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tColaboracion.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tColaboracion);

        jLabel12.setText("Listado de Colaboraciones");

        jLabel13.setText("Código del Experto");

        jLabel14.setText("Código del Caso");

        jLabel15.setText("Fecha de incorporación");

        jLabel16.setText("Descripción");

        jButton7.setText("Insertar Colaboración");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Eliminar Colaboración");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(70, 70, 70)
                                .addComponent(sexoE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(codigoE, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(InsertarExp))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(28, 28, 28)
                                    .addComponent(especialidadE, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(75, 75, 75)
                                    .addComponent(paisE))))
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codigoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)
                                .addGap(212, 212, 212)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addGap(49, 49, 49))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreCa, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(253, 253, 253)
                                .addComponent(jLabel10))
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descripcionCo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(codigoECo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                            .addComponent(codigoCaCo, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(90, 90, 90)
                                        .addComponent(jButton7))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                                        .addComponent(jScrollPane5)))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(259, 259, 259))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(codigoCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InsertarExp)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(nombreCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(paisE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sexoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(especialidadE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(1, 1, 1)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(codigoECo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(codigoCaCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(descripcionCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)))
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Inseta Experto en la base de datos con los datos recogidos en el JFrame
     * @param evt click del raton
     */
    private void InsertarExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarExpActionPerformed
        ////////////////////////////////////////////////////////////////////////////////////////////////////////// Insertar Experto
        try {
            String codigo = codigoE.getText();
            if (mExp.existeExperto(codigo)) {
                JOptionPane.showMessageDialog(null, "Ya existe el experto");
                throw new Exception("Ya existe el experto");

            }

            String nombre = nombreE.getText();

            String pais = paisE.getText();

            String sexo = sexoE.getText();

            String Espe = especialidadE.getText();

            if (codigo.length() == 0 || nombre.length() == 0 || pais.length() == 0|| Espe.length() == 0) {
                JOptionPane.showMessageDialog(null, "Campo vacio");
                throw new Exception("Campo vacio");
            }
            mExp.insertaExperto(new experto(codigo, nombre, pais, sexo, Espe));
            vaciarTablaExpertos();
            pideExpertos();
        } catch (Exception ex) {
            System.out.println("Error en la insercion del experto  " + ex.getMessage());
        }

    }//GEN-LAST:event_InsertarExpActionPerformed
    /**
     * Inserta un caso con los valores tomados del JFrame
     * @param evt click del raton 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ////////////////////////////////////////////////////////////////////////////////////////////////////////// Insertar Caso
        try {
            String codigo = codigoCa.getText();
            if (mCa.existeCaso(codigo)) {
                JOptionPane.showMessageDialog(null, "Ya existe el caso");
                throw new Exception("Ya existe el caso");
            }
            String nombre = nombreCa.getText();

            String fechaIni ; 
            if(fechaInicioCa.getDate()==null){
                fechaIni=null;
            }else{
                fechaIni=new SimpleDateFormat("dd/MM/yyy").format(fechaInicioCa.getDate());
            }

            String fechaFin;
            if (fechaFinCa.getDate() == null) {
                fechaFin = null;
            } else {
                fechaFin = new SimpleDateFormat("dd/MM/yyy").format(fechaFinCa.getDate());
                if(fechaIni==null){
                     JOptionPane.showMessageDialog(null, "No hay fecha de inicio");
                    throw new Exception("No hay fecha de inicio");
                }
                if (fechaInicioCa.getDate().after(fechaFinCa.getDate())) {
                    JOptionPane.showMessageDialog(null, "Fecha fin mayor que fecha inical");
                    throw new Exception("Fecha fin erronea");
                }
            }

            mCa.insertaCaso(new caso(codigo, nombre, fechaIni, fechaFin));
            vaciarTablaCaso();
            rellenarTablaCaso(mCa.listaCasos());
        } catch (Exception ex) {
            System.out.println("Error en la insercion del caso    " + ex.getMessage());
        }

    }//GEN-LAST:event_jButton4ActionPerformed
    /**
     * Inserta una colaboracion con los datos introducidos en el JFrame
     * @param evt Click del raton
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ////////////////////////////////////////////////////////////////////////////////////////////////////////// Insertar Colabora
        try {

            int filac = tCaso.getSelectedRow();
            int filae = tExpertos.getSelectedRow();
            String codigoE;
            if (filac == -1) {
                codigoE = codigoECo.getText();
            } else {
                codigoE = tExpertos.getValueAt(filae, 0).toString();
                codigoECo.setText(codigoE);
            }

            if (!mExp.existeExperto(codigoE)) {
                JOptionPane.showMessageDialog(null, "No existe el experto");
                throw new Exception("No existe el experto");
            }
            String codigoCa;
            if (filac == -1) {
                codigoCa = codigoCaCo.getText();
            } else {
                codigoCa = tCaso.getValueAt(filac, 0).toString();
                codigoCaCo.setText(codigoCa);
            }

            if (!mCa.existeCaso(codigoCa)) {
                JOptionPane.showMessageDialog(null, "No existe el caso");
                throw new Exception("No existe el caso");
            }
            if(fechaCo.getDate()==null){
                JOptionPane.showMessageDialog(null, "No hay fecha");
                throw new Exception("No hay fecha");
            }
            String fechaIn = new SimpleDateFormat("dd/MM/yyy").format(fechaCo.getDate());
            if (!controlaFechas(mCa.getCaso(codigoCa), fechaIn)) {
                JOptionPane.showMessageDialog(null, "Fecha no valida");
                throw new Exception("Fecha no valida");
            }

            if (mCo.existeColaboracion(codigoE, codigoCa, fechaIn)) {
                JOptionPane.showMessageDialog(null, "Ya existe la colaboracion");
                throw new Exception("Ya existe la colaboracion");
            }

            String des = descripcionCo.getText();

            mCo.insertaColaboracion(new colabora(codigoE, codigoCa, fechaIn, des));
            vaciarTablaColaboracion();
            rellenarTablaColaboracion(mCo.listaColabora());
        } catch (Exception ex) {
            System.out.println("Error en la insercion de LA Colaboracion    " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed
    /**
     * Elimina al experto seleccionado en la tabla
     * @param evt click del raton
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /////////////////////////////////////////////////////////////////////////////////////////Eliminar Experto
        try {
            int fila = tExpertos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada");
                throw new Exception("No hay ningula fila seleccionada");
            }
            int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar ese experto?", null, JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                mExp.EliminarExperto(tExpertos.getValueAt(fila, 0).toString());
                vaciarTablaExpertos();
                pideExpertos();
                vaciarTablaColaboracion();
                rellenarTablaColaboracion(mCo.listaColabora());
            }
        } catch (Exception e) {
            System.out.println("Error eliminar Experto  \n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Elimina el caso seleccionado de la tabla de caso
     * @param evt click del raton
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ////////////////////////////////////////////////////////////////////////////////////Eliminar Caso
        try {
            int fila = tCaso.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada");
                throw new Exception("No hay ningula fila seleccionada");
            }
            int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar ese caso?", null, JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                mCa.EliminarCaso(tCaso.getValueAt(fila, 0).toString());
                vaciarTablaCaso();
                rellenarTablaCaso(mCa.listaCasos());
                vaciarTablaColaboracion();
                rellenarTablaColaboracion(mCo.listaColabora());
            }
        } catch (Exception e) {
            System.out.println("Error eliminar Experto  \n" + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Elimina la colaboracion seleccionada en la tabla de Colaboraciones
     * @param evt click del raton
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ////////////////////////////////////////////////////////////////////////////////////Eliminar Colaboracion
        try {
            int fila = tColaboracion.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada");
                throw new Exception("No hay ningula fila seleccionada");
            }
            int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar esa colaboracion?", null, JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                String fecha = (String) tColaboracion.getValueAt(fila, 2);
                fecha = fecha.substring(8, 10) + "/" + fecha.substring(5, 7) + "/" + fecha.substring(0, 4);
                mCo.EliminarColaboracion(tColaboracion.getValueAt(fila, 0).toString(), tColaboracion.getValueAt(fila, 1).toString(), fecha);
                vaciarTablaColaboracion();
                rellenarTablaColaboracion(mCo.listaColabora());
            }
        } catch (Exception e) {
            System.out.println("Error eliminar Experto  \n" + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed
    /**
     * Rellena todas las tablas con sus datos 
     * @param evt click del raton
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        /////////////////////////////////////////////////////////////////////////Listar Todos

        try {
            vaciarTablaCaso();
            rellenarTablaCaso(mCa.listaCasos());

            vaciarTablaColaboracion();
            rellenarTablaColaboracion(mCo.listaColabora());

            vaciarTablaExpertos();
            pideExpertos();

        } catch (SQLException ex) {
            Logger.getLogger(GestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    /**
     * Vacia todas las tablas
     * @param evt clic del raton
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        vaciarTablaCaso();
        vaciarTablaColaboracion();
        vaciarTablaExpertos();

    }//GEN-LAST:event_jButton6ActionPerformed
    /**
     * Compara que la fecha de la colaboracion este entra las fechas del caso
     * @param Caso caso al que pertenece la colaboracion
     * @param fCol fecha de la colaboracion
     * @return 
     */
    private boolean controlaFechas(caso Caso, String fCol) {
        boolean Valida = true;
        int diaCas, mesCaso, anoCas;
        int diaCol, mesCol, anoCol;

        diaCol = ((int) fCol.charAt(0)) * 10 + (int) fCol.charAt(1);
        mesCol = ((int) fCol.charAt(3)) * 10 + (int) fCol.charAt(4);
        anoCol = ((int) fCol.charAt(6)) * 1000 + ((int) fCol.charAt(7)) * 100 + ((int) fCol.charAt(8)) * 10 + (int) fCol.charAt(9);

        String fCas = Caso.getFechaInicio();
        diaCas = ((int) fCas.charAt(8) * 10) + (int) fCas.charAt(9);
        mesCaso = ((int) fCas.charAt(5) * 10) + (int) fCas.charAt(6);
        anoCas = ((int) fCas.charAt(0) * 1000) + ((int) fCas.charAt(1) * 100) + ((int) fCas.charAt(2) * 10) + (int) fCas.charAt(3);

        if (anoCas > anoCol) {
            Valida = false;
        } else if (anoCas == anoCol && mesCaso > mesCol) {
            Valida = false;
        } else if (anoCas == anoCol && mesCaso == mesCol && diaCas > diaCol) {
            Valida = false;
        }
        String f = Caso.getFechaFin();
        if (f != null) {
            fCas = Caso.getFechaFin();
            diaCas = ((int) fCas.charAt(8) * 10) + (int) fCas.charAt(9);
            mesCaso = ((int) fCas.charAt(5) * 10) + (int) fCas.charAt(6);
            anoCas = ((int) fCas.charAt(0) * 1000) + ((int) fCas.charAt(1) * 100) + ((int) fCas.charAt(2) * 10) + (int) fCas.charAt(3);

            if (anoCas < anoCol) {
                Valida = false;
            } else if (anoCas == anoCol && mesCaso < mesCol) {
                Valida = false;
            } else if (anoCas == anoCol && mesCaso == mesCol && diaCas < diaCol) {
                Valida = false;
            }
        }

        return Valida;
    }

    /**
     * Se utiliza para que cuando se cierre la ventana se abra la ventana principal
     */
    @Override
    public void dispose() {//metodo del boton cerrar
        vP.setVisible(true);
        super.dispose();
    }
/**
 * Lo utilizaremos para guardar la conexionOracle
 */
    private conexionOracle co;
    /**
     * Lo utilizaremos para guardar la ventana principal
     */
    private ventanaPrincipal vP;
     /**
     * Lo utilizaremos para poder trabajar con manejaExperto
     */
    private manejaExperto mExp;
    /**
     * Lo utilizaremos para trabajar con manejaCaso
     */
    private manejaCaso mCa;
    /**
     * Lo utilizaremos para trabajar con manejaColabora
     */
    private manejaColabora mCo;
    /**
     * Lo utilizaremos para trabajar con la tabla de Expertos en el frame 
     */
    private DefaultTableModel modeloTExpertos;
    /**
     * Lo utilizaremos para trabajar con la tabla de caso_policial en el frame 
     */
    private DefaultTableModel modeloTCaso;
    /**
     * Lo utilizaremos para trabajar con la tabla colabora en el frame 
     */
    private DefaultTableModel modeloTColaboracion;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InsertarExp;
    private javax.swing.JTextField codigoCa;
    private javax.swing.JTextField codigoCaCo;
    private javax.swing.JTextField codigoE;
    private javax.swing.JTextField codigoECo;
    private javax.swing.JTextField descripcionCo;
    private javax.swing.JTextField especialidadE;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField nombreCa;
    private javax.swing.JTextField nombreE;
    private javax.swing.JTextField paisE;
    private javax.swing.JTextField sexoE;
    private javax.swing.JTable tCaso;
    private javax.swing.JTable tColaboracion;
    private javax.swing.JTable tExpertos;
    // End of variables declaration//GEN-END:variables
}
