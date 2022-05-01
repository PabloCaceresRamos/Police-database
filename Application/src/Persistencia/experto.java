package Persistencia;

/**
 * Clase de la tabla Experto
 * Se utiliza para guardar las distintas tuplas de la clase Experto
 * @author Pablo Caceres
 * @version 1.0
 */
public class experto {
    /**
     * Se utiliza para guardar el codigo del experto en formato String
     */
	private String codExperto;
        /**
     * Se utiliza para guardar el nombre del experto en formato String
     */
	private String nombre;
        /**
     * Se utiliza para guardar el pais del experto en formato String
     */
	private String pais;
        /**
     * Se utiliza para guardar el sexo del experto en formato String
     */
	private String sexo;
        /**
     * Se utiliza para guardar la especialidad del experto en formato String
     */
	private String especialidad;
	   
	 /**
        * Constructor por defecto
         */
	public experto() {
	   codExperto=null;
	   nombre=null;
	   pais=null;
	   sexo=null;
	   especialidad=null;
  
	}
	            
	 /**
          * Constructor
          * @param c codigo experto
          * @param n nombre experto
          * @param p pais del experto
          * @param s sexo del experto
          * @param e especialidad del experto
          */	
	public experto(String c, String n, String p, String s, String e) {
            codExperto = c;
            nombre = n;
            pais = p;
            sexo = s;
            especialidad = e;
        }		
	/**
         * Devuelve el codigo del experto
         * @return devuelve codigo del experto
         */	
	public String getCodExperto() {
            return codExperto;
        }
        
	/**
         * Devuelve el nombre del experto
         * @return Devuelve el nombre del experto
         */
	public String getNombre() {
            return nombre;
        }
	/**
         * Devuelve el pais el experto
         * @return Devuelve el pais del experto
         */
	public String getPais() {
            return pais;
        }
	/**
         * Devuelve el sexo del experto
         * @return devuelve el sexo
         */	
	public String getSexo() {
            return sexo;
        }
        /**
         * Devuelve la expecialidad del experto
         * @return devuelve la especialidad
         */
	public String getEspecialidad() {
            return especialidad;
        }
	/**
         * Modifica el codigo del experto
         * @param c codigo del experto
         */	
	public void setCodExperto(String c) {
            codExperto = c;
        }
	/**
         * modifica el nombre del experto
         * @param n nombre del experto
         */
	public void setNombre(String n) {
            nombre = n;
        }
	/**
         * Modifica el pais del experto
         * @param p pais del experto
         */
	public void setPais(String p) {
            pais = p;
        }
	/**
         * Modifica el sexo del experto
         * @param s sexo del experto
         */
	public void setSexo(String s) {
            sexo = s;
        }
	/**
         * Modifica la especialidad del experto
         * @param e especialidad
         */
	public void setEspecialidad(String e) {
            especialidad = e;
        }
}