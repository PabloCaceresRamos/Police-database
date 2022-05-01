package Persistencia;

import java.awt.List;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 * Clase de la tabla Colabora
 * Se utiliza para trabajar con la tabla Colabora
 * @author Pablo Caceres
 * @version 1.0
 */
public class manejaColabora {
    // Creamos un objeto de tipo "conexionOracle"
    /**
     * Se utiliza para guardar la conexionOracle
     */
    conexionOracle conexion = null;
    
    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
     /**
     * Se utiliza para utilizar el PreparedStatements para poder hacer transiciones
     */
    PreparedStatement ps = null;
    
     /**
    * Implementa operaciones sobre la tabla COLABORA
    * @param c conexión con Oracle
    */
    public manejaColabora(conexionOracle c) {
        conexion = c;
    }
     /**
    * Comprueba si existe una colaboración en la tabla de COLABORA dado su código
    * @param codExperto, codCaso del experto
    * @param codCaso codigo del caso
    * @param fecha codigo de la fecha
    * @throws SQLException si ocurre alguna anomalía
    * @return devuelve true si existe la colaboracion, false en caso contrario
    */
    public boolean existeColaboracion(String codExperto, String codCaso,String fecha) throws SQLException {
       ps=conexion.conn.prepareStatement("select codexperto,codcaso  from colabora  where CODEXPERTO=? AND CODCASO=? and fecha=?");//hace falta hacer condiciones en el where
         ps.setString(1, codExperto);
         ps.setString(2, codCaso);
         ps.setString(3, fecha);
         ResultSet rs=ps.executeQuery();
         return rs.next();//si hay alguna tupla devuelve true, si no devuelve false
         
    }
    
     /**
    * Inserta una colaboración en la tabla COLABORA
    * @param col objeto colabora a insertar
    * @throws SQLException si ocurre alguna anomalía
    */
    public void insertaColaboracion(colabora col) throws SQLException {
           ps=conexion.conn.prepareStatement("insert into COLABORA values(?,?,?,?)");
           ps.setString(1, col.getCodExperto());
           ps.setString(2, col.getCodCaso());
           ps.setString(3, col.getFecha());
           ps.setString(4, col.getDescripcionColaboracion());
           
           ps.executeUpdate();
        
    }
    /**
     * Elimina la colaboracion pasandole que tiene como clave principal 
     * los datos pasados por parametros
     * @param codE codigo Experto de la colaboracion
     * @param codCa codigo Caso de la colaboracion
     * @param fecha fecha de la colaboracion
     * @throws SQLException excepcion de la base de datos
     */
     public void EliminarColaboracion(String codE,String codCa,String fecha) throws SQLException{
        ps=conexion.conn.prepareStatement("Delete from Colabora where codExperto=? and codCaso=? and fecha=? ");
        ps.setString(1, codE);
        ps.setString(2, codCa);
        ps.setString(3, fecha);
        ps.executeUpdate();
    }
    
    /**
     *Devuelve todos los expertos que trabajan en el caso con el codigo pasado
     * por parametros
     * @param cc codigo del caso
     * @return devuelve un ArrayListe con todos los expertos encontrados
     * @throws SQLException excepcion de la base de datos
     */
    public ArrayList<String[]> getExpDeCaso(String cc) throws SQLException{
        
        String sql="{call getCaso(?,?) }";
        CallableStatement call=conexion.conn.prepareCall(sql);
        call.setString(1,cc);
        call.registerOutParameter(2,OracleTypes.CURSOR);
        call.executeUpdate();
        ResultSet rs=(ResultSet) call.getObject(2);
        ArrayList<String[]> list=new ArrayList();
        
        while(rs.next()){
            String[] a={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
            list.add(a);
        }
        return list;
    }
    
    /**
     * Devuelve todas la colaboraciones
     * @return ArrayList con las colaboraciones
     * @throws SQLException excepcion de la base de datos
     */
     public ArrayList<colabora> listaColabora() throws SQLException {
        
        ArrayList<colabora> array=new ArrayList();
        ps=conexion.conn.prepareStatement("select * from colabora");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            array.add(new colabora(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            
        }
        return array;
    }    
}
