package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Solicitud {
    private int idSolicitud;
    private Actividad actividad;
    private Tarifa tarifa;
    private Administrador administrador;
    private String motivoSolicitud;
    private String fechaCreacion;

    public Solicitud(){

    }

    public Solicitud(int idSolicitud, Actividad actividad, Tarifa tarifa, Administrador administrador, String motivoSolicitud, String fechaCreacion) {
        this.idSolicitud = idSolicitud;
        this.actividad = actividad;
        this.tarifa = tarifa;
        this.administrador = administrador;
        this.motivoSolicitud = motivoSolicitud;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }




}
