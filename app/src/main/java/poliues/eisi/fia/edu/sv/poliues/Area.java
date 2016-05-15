package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by JhonyWilliam on 08/05/2016.
 */
public class Area extends Deporte {
    private Integer idarea;
    private Integer maximopersonas;
    private String nombrearea;
    private String descripcionarea;

    public Area() {
    }

    public Area(Integer idarea, Integer maximopersonas, String nombrearea, String descripcionarea) {
        this.idarea = idarea;
        this.maximopersonas = maximopersonas;
        this.nombrearea = nombrearea;
        this.descripcionarea = descripcionarea;
    }

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public Integer getMaximopersonas() {
        return maximopersonas;
    }

    public void setMaximopersonas(Integer maximopersonas) {
        this.maximopersonas = maximopersonas;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }

    public String getDescripcionarea() {
        return descripcionarea;
    }

    public void setDescripcionarea(String descripcionarea) {
        this.descripcionarea = descripcionarea;
    }
}
