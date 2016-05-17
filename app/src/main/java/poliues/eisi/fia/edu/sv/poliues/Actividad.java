package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Actividad {
    private int idActividad;
    private String nombreActividad;
    private String descripcionActividad;

    public Actividad(){

    }

    public Actividad(int idActividad, String nombreActividad, String descripcionActividad) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcionActividad = descripcionActividad;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }
}
