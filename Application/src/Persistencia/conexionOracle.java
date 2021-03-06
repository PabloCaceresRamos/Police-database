

package Persistencia;
import java.sql.*;

/**
 * Clase para la trabajar con la base de datos
 * Se utiliza para realizar trabajar con la base de datos
 * @author Pablo Caceres
 * @version 1.0
 */
public class conexionOracle {
    /**
     * Es lo que se utilizara para comunicarnos con la sesion de la base de datos
     */
    Connection conn = null;
    
    /** 
      Establece la conexión con el servidor
    @throws Exception si ocurre cualquier anormalidad
    */
    public conexionOracle() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");

        conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida",
                "DDSI_007", "DDSI_000");
    }    
    /** 
      Implementa la desconexión con el servidor
    @throws SQLException si ocurre cualquier anormalidad
    */
    public void desconexion() throws SQLException {
            conn.close();
    }
    /**  
    *  Inicia una transacción
    *@throws SQLException si ocurre cualquier anormalidad
    */
    public void inicioTransaccion() throws SQLException {
        conn.setAutoCommit(false);
    }
    /** 
    *  Finaliza una transacción con commint
    *@throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionCommit() throws SQLException {
       conn.commit();
       conn.setAutoCommit(true);
    }
    /**  
    *  Finaliza una transacción con rollback
    @throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionRollback() throws SQLException {
        conn.rollback();
        conn.setAutoCommit(true);
        
    }

}
