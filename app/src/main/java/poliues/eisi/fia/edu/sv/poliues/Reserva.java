package poliues.eisi.fia.edu.sv.poliues;

import java.util.Date;

/**
 * Created by jonathan on 2/5/2016.
 */
public class Reserva {
    private int idreserva;
    private int idfacultad;
    private String fechaingreso;
    private int numeropersonas;
    private String motivo;
    private String descripcionreserva;

    public Reserva() {
    }

    public Reserva(int idreserva, int idfacultad, String fechaingreso, int numeropersonas, String motivo, String descripcionreserva) {
        this.idreserva = idreserva;
        this.idfacultad = idfacultad;
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

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
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
