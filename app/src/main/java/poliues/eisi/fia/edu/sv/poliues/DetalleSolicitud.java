package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class DetalleSolicitud {
    private int idDescripcion;
    private Solicitud solicitud;
    private Area area;
    private String fechaInicio;
    private String fechaFinal;
    private double cobroTotal;



    public DetalleSolicitud(int idDescripcion, Solicitud solicitud, Area area, String fechaInicio, String fechaFinal, double cobroTotal) {
        this.idDescripcion = idDescripcion;
        this.solicitud = solicitud;
        this.area = area;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.cobroTotal = cobroTotal;
    }

    public DetalleSolicitud() {
    }

    public int getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(int idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getCobroTotal() {
        return cobroTotal;
    }

    public void setCobroTotal(double cobroTotal) {
        this.cobroTotal = cobroTotal;
    }
}
