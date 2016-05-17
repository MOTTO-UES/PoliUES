package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by jonathan on 2/5/2016.
 */
public class DetalleReserva {
    private int iddetallereserva;
    private int idreserva;
    private  int idarea;

    public DetalleReserva() {
    }

    public DetalleReserva(int iddetallereserva, int idreserva, int idarea) {
        this.iddetallereserva = iddetallereserva;
        this.idreserva = idreserva;
        this.idarea = idarea;
    }

    public int getIddetallereserva() {
        return iddetallereserva;
    }

    public void setIddetallereserva(int iddetallereserva) {
        this.iddetallereserva = iddetallereserva;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }
}
