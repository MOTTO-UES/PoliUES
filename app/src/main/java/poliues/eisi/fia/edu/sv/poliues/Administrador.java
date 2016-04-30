package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Administrador {
    private int idAdministrador;
    private String nombreAdmin;
    private String passwordAdmin;
    private String correoAdmin;

    public Administrador(){

    }

    public Administrador(int idAdministrador, String nombreAdmin, String passwordAdmin, String correoAdmin) {
        this.idAdministrador = idAdministrador;
        this.nombreAdmin = nombreAdmin;
        this.passwordAdmin = passwordAdmin;
        this.correoAdmin = correoAdmin;
    }





    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }




}
