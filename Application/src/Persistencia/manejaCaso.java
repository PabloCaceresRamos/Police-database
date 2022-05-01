package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de la tabla Caso_policial
 * Se utiliza para trabajar con la tabla caso_policial
 * @author Pablo Caceres
 * @version 1.0
 */
public class manejaCaso {
    // Crea un objeto de tipo "conexionOracle"
    /**
     * Se utiliza para guardar la conexionOracle
     */
    conexionOracle conexion = null;
    
    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    /**
     * Se utiliza para utilizar el PreparedStatements para poder hacer transiciones
     */
    PreparedStatement ps = null;

    
    /**
    * Implementa operaciones sobre la tabla CASO
    * @param c conexión con Oracle
    */
    public manejaCaso(conexionOracle c) {
        conexion = c;
    }
	 
     /**
    *  Comprueba si existe un caso en la tabla de CASO_POLICIAL dado un código de caso
    * @param codCaso código del caso a buscar
    * @return devuelve true si existe el caso, false en caso contrario
    * @throws SQLException si ocurre alguna anomalía
    */
    public boolean existeCaso(String codCaso) throws SQLException {
     
         ps=conexion.conn.prepareStatement("select CODCASO  from caso_policial where CODCASO=?");
         ps.setString(1, codCaso);
         ResultSet rs=ps.executeQuery();
         return rs.next();//si hay alguna tupla devuelve true, si no devuelve false
          
         
    	  }
     /**
    *  Inserta caso en la tabla de CASO_POLICIAL
    * @param cs caso a insertar
    * @throws SQLException si ocurre alguna anomalía
    */
    public void insertaCaso (caso cs) throws SQLException  {
        ps=conexion.conn.prepareStatement("insert into caso_policial values(?,?,?,?)");
        ps.setString(1, cs.getCodCaso());
        ps.setString(2, cs.getNombre());
        ps.setString(3, cs.getFechaInicio());
        ps.setString(4, cs.getFechaFin());
        
        ps.executeUpdate();
    }
    /**
     * Elimina el caso con codigo igual al pasado por parametro
     * @param cod codigo del caso a liminar
     * @throws SQLException excepcion de la base de datos
     */
     public void EliminarCaso(String cod) throws SQLException{
        ps=conexion.conn.prepareStatement("Delete from caso_policial where codcaso=? ");
        ps.setString(1, cod);
        ps.executeUpdate();
    }
    /**
     * devuelve todos los casos que hay en la base de datos
     * @return ArrayList con todos los casos
     * @throws SQLException excepcion de la base de datos
     */
     public ArrayList<caso> listaCasos() throws SQLException {
        
        ArrayList<caso> array=new ArrayList();
        ps=conexion.conn.prepareStatement("select * from caso_policial");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            array.add(new caso(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            
        }
        return array;
    }    
     /**
      * Devuelve el caso que tiene le codigo mandado por parametro
      * @param codCaso codigo del caso que queremos
      * @return devuelve un caso
      * @throws SQLException excepcion de la base de datos
      */
    public caso getCaso(String codCaso) throws SQLException {
        
        ps=conexion.conn.prepareStatement("select * from caso_policial");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            if(codCaso.equals(rs.getString(1)))
                return new caso(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
        }
        
        return null;
            
        
       
    }
}

	 


