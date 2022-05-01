package Persistencia;

/**
 * Clase de la tabla Colabora
 * Se utiliza para guardar las distintas tuplas de la clase Colabora
 * @author Pablo Caceres
 * @version 1.0
 */
public class colabora {
/**
     * Se utiliza para guardar el codigo del experto en formato String
     */
    private String codExperto;
    /**
     * Se utiliza para guardar el codigo del caso en formato String
     */
    private String codCaso;
    /**
     * Se utiliza para guardar la fecha dela colaboracion en formato String
     */
    private String fecha;
    
    /**
     * Se utiliza para guardar una descripcion de la colaboracion en formato String
     */
    private String descripcionColaboracion;

    /**
     * Constructor por defecto
     */
    public colabora() {
        this.codCaso=null;
        this.codExperto=null;
        this.descripcionColaboracion=null;
        this.fecha=null;
    }

    /**
     * Constructor 
     * @param ce es el codigo de esperto
     * @param cc es el codigo de caso
     * @param f es la fecha de la colaboracion
     * @param dc es la decripcion de la colaboracion
     */
    public colabora(String ce, String cc, String f, String dc) {
        codExperto = ce;
        codCaso = cc;
        fecha = f;
        descripcionColaboracion = dc;
    }
    /**
     * Se usa para devolver el codigo de experto
     * @return devuelve codigo de experto
     */
    public String getCodExperto() {
        return codExperto;
    }
    /**
     * Se utiliza para devolver el codigo del caso
     * @return devuelve el codigo del caso
     */
    public String getCodCaso() {
        return codCaso;
    }
    /**
     * Se utiliza para devolver la fecha
     * @return devuelve la fecha de la colaboracion
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Se utiliza para devolver la descripcion de la colaboracion
     * @return devuelve la descripcion de la colaboracion
     */
    public String getDescripcionColaboracion() {
        return descripcionColaboracion;
    }
    /**
     * Se utiliza para modifica el codigo de experto
     * @param ce codigo de experto
     */
    public void setCodExperto(String ce) {
        codExperto = ce;
    }
    /**
     * Se utiliza para modificar el codigo del caso
     * @param cc codigo del caso
     */
    public void setCodcaso(String cc) {
        codCaso = cc;
    }
    /**
     * Se utiliza para modifacar la fecha
     * @param f fecha de la colaboracion
     */
    public void setFecha(String f) {
        fecha = f;
    }
    /**
     * se utiliza para modificar la descripcion
     * @param dc descripcion de la colaboracion
     */
    public void setDescripcionColaboracion(String dc) {
        descripcionColaboracion = dc;
    }
}
