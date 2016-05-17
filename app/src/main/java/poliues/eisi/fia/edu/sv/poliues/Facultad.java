package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by jonathan on 2/5/2016.
 */
public class Facultad {
    int  idfacultad;
    private String nombre;

    public Facultad() {
    }

    public Facultad(int idfacultad, String nombre) {
        this.idfacultad = idfacultad;
        this.nombre = nombre;
    }

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
