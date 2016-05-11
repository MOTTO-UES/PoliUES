package poliues.eisi.fia.edu.sv.poliues;

import java.util.Date;

/**
 * Created by jonathan on 2/5/2016.
 */
public class Horario {
    private int idhorario;
    private int idreserva;
    private String  fechareserva;
    private String horarioinicio;
    private String horariofin;

    public Horario() {
    }

    public Horario(int idhorario, int idreserva, String  fechareserva, String horarioinicio, String horariofin) {
        this.idhorario = idhorario;
        this.idreserva = idreserva;
        this.fechareserva = fechareserva;
        this.horarioinicio = horarioinicio;
        this.horariofin = horariofin;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String  getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(String  fechareserva) {
        this.fechareserva = fechareserva;
    }

    public String getHorarioinicio() {
        return horarioinicio;
    }

    public void setHorarioinicio(String horarioinicio) {
        this.horarioinicio = horarioinicio;
    }

    public String getHorariofin() {
        return horariofin;
    }

    public void setHorariofin(String horariofin) {
        this.horariofin = horariofin;
    }
}
