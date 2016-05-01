package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Area {
    private int idArea;
    private int maximoPersona;
    private String nombreArea;

    public Area(){

    }

    public Area(int idArea, int maximoPersona, String nombreArea, String descripcionArea) {
        this.idArea = idArea;
        this.maximoPersona = maximoPersona;
        this.nombreArea = nombreArea;
        this.descripcionArea = descripcionArea;
    }

    String descripcionArea;




    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public int getMaximoPersona() {
        return maximoPersona;
    }

    public void setMaximoPersona(int maximoPersona) {
        this.maximoPersona = maximoPersona;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }



}
