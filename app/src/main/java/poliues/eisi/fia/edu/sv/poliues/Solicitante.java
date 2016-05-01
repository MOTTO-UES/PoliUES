package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Solicitante {
    private int idSolicitante;
    private String nombre;
    private String password;
    private String correo;


    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



}
