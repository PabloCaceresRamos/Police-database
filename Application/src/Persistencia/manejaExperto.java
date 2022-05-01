package Persistencia;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase de la tabla Experto
 * Se utiliza para trabajar con la tabla experto
 * @author Pablo Caceres
 * @version 1.0
 */
public class manejaExperto {
    // Se crea un objeto de tipo "conexionOracle"
    /**
     * Se utiliza para guardar la conexionOracle
     */
    conexionOracle conexion = null;
    
    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
     /**
     * Se utiliza para utilizar el PreparedStatements para poder hacer transiciones
     */
    PreparedStatement ps = null;
    

    /**
    * Implementa operaciones sobre la tabla EXPERTO
    * @param c conexión con Oracle
    */    
    public manejaExperto(conexionOracle c) {
      conexion = c;
    }
    
   /**
    * Devuelve todos los expertos que pertenecen al pais pasado por parametros
    * @param pais pais del que queremos los expertos
    * @return ArrayList con los expertos
    * @throws SQLException excepcion del la base de datos
    */
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException {
        
        ArrayList<experto> array=new ArrayList();
        ps=conexion.conn.prepareStatement("select * from experto where pais=?");
        ps.setString(1, pais);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            array.add(new experto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            
        }
        return array;
    }    
    /**
     * Devuelve todos los expertos
     * @return ArrayList con todos los expertos
     * @throws SQLException excepcion de la base de datos
     */
     public ArrayList<experto> listaExpertos() throws SQLException {
        
        ArrayList<experto> array=new ArrayList();
        ps=conexion.conn.prepareStatement("select * from experto");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            array.add(new experto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            
        }
        return array;
    }    
    
    /**
     * Nos dice si existe el experto con codigo pasado por parametro
     * @param codExperto codigo del experto
     * @return true si existe el experto, false en caso contrario
     * @throws SQLException Excepcion de la base de datos
     */
    public boolean existeExperto(String codExperto) throws SQLException {
         ps=conexion.conn.prepareStatement("select * from experto where CODEXPERTO=?");
         ps.setString(1, codExperto);
         ResultSet rs=ps.executeQuery();
          
         
         return rs.next();
        
        
    }
    
  /**
   * Inserta el experto mandado por parametros
   * @param exp experto a insertar
   * @throws SQLException excepcion de la base de datos
   */ 
    public void insertaExperto(experto exp) throws SQLException {
        
        ps=conexion.conn.prepareStatement("insert into experto values(?,?,?,?,?)");
        ps.setString(1, exp.getCodExperto());
        ps.setString(2, exp.getNombre());
        ps.setString(3, exp.getPais());
        ps.setString(4, exp.getSexo());
        ps.setString(5, exp.getEspecialidad());
        ps.executeUpdate();
    }
    /**
     * Elimina el experto con el mismo codigo que el pasado por parametro
     * @param cod codigo de experto a eliminar
     * @throws SQLException excepcion de la base de datos
     */
    public void EliminarExperto(String cod) throws SQLException{
        ps=conexion.conn.prepareStatement("Delete from Experto where codExperto=? ");
        ps.setString(1, cod);
        ps.executeUpdate();
    }
    /**
     * Devuelve el numero de expertos con el sexo pasado por parametro
     * @param sex sexo 
     * @return num de experto con el sexo elegido
     * @throws SQLException excepcion de la base de datos
     */
    public int numSexo(String sex) throws SQLException{
        CallableStatement stmt=null;
        String sql="{?=call getNumSex(?)}";
        stmt=conexion.conn.prepareCall(sql);
        stmt.setString(2,sex);
        stmt.registerOutParameter(1,Types.INTEGER);
        stmt.executeUpdate();
        int valor=stmt.getInt(1);
        return valor;
    }
}