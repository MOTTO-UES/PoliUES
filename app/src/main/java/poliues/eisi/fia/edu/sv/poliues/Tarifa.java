package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class Tarifa {
    private int idTarifa;
    private int cantidadPersonas;
    double tarifaUnitaria;

    public Tarifa(){

    }

    public Tarifa(int idTarifa, int cantidadPersonas, double tarifaUnitaria) {
        this.idTarifa = idTarifa;
        this.cantidadPersonas = cantidadPersonas;
        this.tarifaUnitaria = tarifaUnitaria;
    }




    public double getTarifaUnitaria() {
        return tarifaUnitaria;
    }

    public void setTarifaUnitaria(double tarifaUnitaria) {
        this.tarifaUnitaria = tarifaUnitaria;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }




}
