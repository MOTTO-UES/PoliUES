package poliues.eisi.fia.edu.sv.poliues;

import java.util.Date;

/**
 * Created by jonathan on 1/5/2016.
 */
public class Reserva {
    private int idreserva;
    private int idhorario;
    private String facultad;
    private Date fechaingreso;
    private int numeropersonas;
    private String motivo;
    private String descripcionreserva;

    public Reserva() {
    }

    public Reserva(int idreserva, int idhorario, String facultad, Date fechaingreso, int numeropersonas, String motivo, String descripcionreserva) {
        this.idreserva = idreserva;
        this.idhorario = idhorario;
        this.facultad = facultad;
        this.fechaingreso = fechaingreso;
        this.numeropersonas = numeropersonas;
        this.motivo = motivo;
        this.descripcionreserva = descripcionreserva;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public int getNumeropersonas() {
        return numeropersonas;
    }

    public void setNumeropersonas(int numeropersonas) {
        this.numeropersonas = numeropersonas;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcionreserva() {
        return descripcionreserva;
    }

    public void setDescripcionreserva(String descripcionreserva) {
        this.descripcionreserva = descripcionreserva;
    }
}
