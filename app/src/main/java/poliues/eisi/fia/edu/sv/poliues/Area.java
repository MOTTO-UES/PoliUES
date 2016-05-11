package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by jonathan on 2/5/2016.
 */
public class Area {
    private int idarea;
    private int maximopersona;
    private String foto;
    private String nombrearea;
    private String descripcionarea;

    public Area(){

    }

    public Area(int idarea, int maximopersona, String foto, String nombrearea, String descripcionarea) {
        this.idarea = idarea;
        this.maximopersona = maximopersona;
        this.foto = foto;
        this.nombrearea = nombrearea;
        this.descripcionarea = descripcionarea;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public int getMaximopersona() {
        return maximopersona;
    }

    public void setMaximopersona(int maximopersona) {
        this.maximopersona = maximopersona;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }

    public String getDescripcionarea() {
        return descripcionarea;
    }

    public void setDescripcionarea(String descripcionarea) {
        this.descripcionarea = descripcionarea;
    }
}
