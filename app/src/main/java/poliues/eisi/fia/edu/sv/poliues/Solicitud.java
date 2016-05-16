package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Solicitud {
    private int idSolicitud;
    private int actividad;
    private double tarifa;
    private int administrador;
    private int solicitante;
    private String motivoSolicitud;
    private String estadoSolicitud;
    private String fechaCreacion;
    private int cantidadPersonas;

    public Solicitud(){

    }

    public Solicitud(int idSolicitud, int actividad, double tarifa, int administrador, int solicitante, String motivoSolicitud, String fechaCreacion, int cantidadPersonas) {
        this.idSolicitud = idSolicitud;
        this.actividad = actividad;
        this.tarifa = tarifa;
        this.administrador = administrador;
        this.solicitante = solicitante;
        this.motivoSolicitud = motivoSolicitud;
        this.fechaCreacion = fechaCreacion;
        this.cantidadPersonas = cantidadPersonas;
    }



    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
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

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
}
