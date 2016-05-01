package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Actividad {
   private int idActividad;
    private int prioridad;
    private String descripcionActividad;

    public Actividad(){

    }

    public Actividad(int idActividad, int prioridad, String descripcionActividad) {
        this.idActividad = idActividad;
        this.prioridad = prioridad;
        this.descripcionActividad = descripcionActividad;
    }



    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }



}
