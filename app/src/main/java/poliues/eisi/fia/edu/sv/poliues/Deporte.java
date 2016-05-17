package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by JhonyWilliam on 08/05/2016.
 */
public class Deporte {
    private Integer iddeporte;
    private String nombredeporte;
    private String descripciondeporte;

    public Deporte() {
    }

    public Deporte(Integer iddeporte, String nombredeporte, String descripciondeporte) {
        this.iddeporte = iddeporte;
        this.nombredeporte = nombredeporte;
        this.descripciondeporte = descripciondeporte;
    }

    public Integer getIddeporte() {
        return iddeporte;
    }

    public void setIddeporte(Integer iddeporte) {
        this.iddeporte = iddeporte;
    }

    public String getNombredeporte() {
        return nombredeporte;
    }

    public void setNombredeporte(String nombredeporte) {
        this.nombredeporte = nombredeporte;
    }

    public String getDescripciondeporte() {
        return descripciondeporte;
    }

    public void setDescripciondeporte(String descripciondeporte) {
        this.descripciondeporte = descripciondeporte;
    }
}