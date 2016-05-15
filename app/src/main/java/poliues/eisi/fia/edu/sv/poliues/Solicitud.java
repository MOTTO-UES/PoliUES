package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Solicitud {
    private int idSolicitud;
    private String actividad;
    private int tarifa;
    private int administrador;
    private int solicitante;
    private String motivoSolicitud;
    private String estadoSolicitud;
    private String fechaCreacion;

    public Solicitud(){

    }

    public Solicitud(int idSolicitud, String actividad, int tarifa, int administrador, int solicitante, String motivoSolicitud, String fechaCreacion) {
        this.idSolicitud = idSolicitud;
        this.actividad = actividad;
        this.tarifa = tarifa;
        this.administrador = administrador;
        this.solicitante = solicitante;
        this.motivoSolicitud = motivoSolicitud;
        this.fechaCreacion = fechaCreacion;
    }



    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public int getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(int solicitante) {
        this.solicitante = solicitante;
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

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
}
