package Persistencia;
/**
 * Clase de la tabla caso_policial
 * Se utilizara para guardar distintas tuplas de la tabla caso
 * @author Pablo Caceres
 * @version 1.0
 */


public class caso {
    /**
     * codCaso se utiliza para guardar el codigo del caso en formato String
     */
    private String codCaso;
    /**
     * nombre se utiliza para guardar el nombre del caso en formato Stiring
     */
    private String nombre;
    /**
     * fechaInicio gurada la fecha de inicio del caso en formato String
     */
    private String fechaInicio;
    /**
     * fechaFin guarda la fecha final del caso en formato String
     */
    private String fechaFin;
    
    
    public caso(String cc,String n, String fi, String ff) {
	this.codCaso=cc;
        this.fechaFin=ff;
        this.nombre=n;
        this.fechaInicio=fi;
    }
    /**
     * Lo utilizaremos para devolver el codigo del caso
     * @return devuelve el codigo del caso
     */
    public String getCodCaso(){
      return this.codCaso;
    }	
    /**
     * Lo utilizaremos para devolver el nombre del caso
     * @return devuelve el nombre del caso
     */
    public String getNombre(){
     return this.nombre;
    }
    /**
     * Lo utilizaremos para devolver la fecha de inicio del caso
     * @return devuelve la fecha de inicio del caso
     */
    public String getFechaInicio(){
       return this.fechaInicio;
    }
    /**
     * Lo utilizaremos para devolver la fecha de fin del caso
     * @return devuelve la fecha de fin
     */
    public String getFechaFin(){
      
        return this.fechaFin;
    }
    /**
     * Lo utilizaremos para modificar el codigo del caso
     * @param c es el codigo de caso que vamos a guardar
     */
    public void setCodCaso(String c){
       this.codCaso=c;
    }
    /**
     * Lo utilizaremos para modificar el nombre del caso
     * @param n es el nombre del caso que vamos a utilizar
     */
    public void setNombre(String n){
      this.nombre=n;
    }
    /**
     * Lo utilizaremos para modificar la fecha de inicio
     * @param fi es la fecha que vamos a utilizar
     */
    public void setFechaInicio(String fi){
       this.fechaInicio=fi;
    }
    /**
     * Lo utilizaremos para modificar la fecha final 
     * @param ff es la fecha que vamos a utilizar como final
     */
    public void setFechaFin(String ff){
      this.fechaFin=ff;
    }
	    
}
