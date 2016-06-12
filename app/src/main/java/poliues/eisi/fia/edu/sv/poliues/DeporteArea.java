package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by JhonyWilliam on 08/05/2016.
 */
public class DeporteArea {



    private Integer iddeportearea;
    private Area idarea;
    private Deporte iddeporte;

    public DeporteArea() {
    }

    public DeporteArea(Integer iddeportearea, Area idarea, Deporte iddeporte) {
        this.iddeportearea = iddeportearea;
        this.idarea = idarea;
        this.iddeporte = iddeporte;
    }

    public Integer getIddeportearea() {
        return iddeportearea;
    }

    public void setIddeportearea(Integer iddeportearea) {
        this.iddeportearea = iddeportearea;
    }

    public Area getIdarea() {
        return idarea;
    }

    public void setIdarea(Area idarea) {
        this.idarea = idarea;
    }

    public Deporte getIddeporte() {
        return iddeporte;
    }

    public void setIddeporte(Deporte iddeporte) {
        this.iddeporte = iddeporte;
    }
}
