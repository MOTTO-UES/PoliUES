package poliues.eisi.fia.edu.sv.poliues;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rodrigo Daniel on 29/04/2016.
 */
public class ControlBDPoliUES {

    private static final  String[] camposreserva = new  String[]
            {"idreserva","idhorario","facultad","fechaingreso","numeropersonas","motivo","descripcionreserva"};

    private static final  String[] camposSolicitud = new  String[]
        {"idSolicitud", "actividad", "tarifa", "administrador", "motivoSolicitud", "fechaCreacion"};

    private static final String[] camposDetalleSolicitud = new String[]
            {"idDescripcion", "solicitud", "area", "fechaInicio", "fechaFinal", "cobroTotal"};





    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDPoliUES(Context ctx, DatabaseHelper dbHelper, SQLiteDatabase db) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        this.db = db;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper{

        private static final String BASE_DATOS = "PoliUES.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context){
            super(context,BASE_DATOS,null,VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("create table reserva (idreserva  INTEGER NOT NULL PRIMARY KEY,idhorario INTEGER,facultad VARCHAR(25) NOT NULL,fechaingreso DATE NOT NULL,numeropersonas INTEGER NOT NULL, motivo VARCHAR(25) NOT NULL,descripcionreserva   VARCHAR(50) NOT NULL;");
                db.execSQL("create table detallereserva ( iddetallereserva INTEGER NOT NULL PRIMARY KEY,idarea integer, idreserva INTEGER);");

            }catch(SQLException e){

                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    public String insertar(Reserva reserva){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues reserva1 = new ContentValues();
        reserva1.put("idreserva",reserva.getIdreserva());
        reserva1.put("idhorario",reserva.getIdhorario());
        reserva1.put("facultad", reserva.getFacultad());
        reserva1.put("fechaingreso", reserva.getFechaingreso().toString());
        reserva1.put("numeropersonas", reserva.getNumeropersonas());
        reserva1.put("motivo", reserva.getMotivo());
        reserva1.put("descripcionreserva ",reserva.getDescripcionreserva());
        contador=db.insert("reserva", null, reserva1);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro                           Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }

    public String actualizar(Reserva reserva){
        if(verificarIntegridad(reserva, 6)){
            String[] id = {String.valueOf(reserva.getIdreserva())};
            ContentValues cv = new ContentValues();
            cv.put("idhorario", reserva.getIdhorario());
            cv.put("facultad", reserva.getFacultad());
            cv.put("facultad", reserva.getFechaingreso().toString());
            cv.put("facultad", reserva.getNumeropersonas());
            cv.put("motivo", reserva.getMotivo());
            cv.put("descripcionreserva ", reserva.getDescripcionreserva());
            db.update("reserva", cv, "idreserva = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo " + reserva.getIdreserva() + " no existe";
        }
    }

    public Reserva consultarReserva(String idreserva){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String[] id = {idreserva};
        Cursor cursor = db.query("reserva", camposreserva, "idreserva = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Reserva reserva = new Reserva();
            reserva.setIdreserva(Integer.parseInt(cursor.getString(0)));
            reserva.setIdhorario(Integer.parseInt(cursor.getString(1);
            reserva.setFacultad(cursor.getString(2));
            Date fechacreacion = null;
            try {
                fechacreacion = formato.parse(cursor.getString(3));
            }
            catch (ParseException ex)
            {
                System.out.println(ex);
            }
            reserva.setFechaingreso(fechacreacion);
            reserva.setNumeropersonas(Integer.parseInt(cursor.getString(4));
            reserva.setMotivo(cursor.getString(5));
            reserva.setDescripcionreserva(cursor.getString(6));
            return reserva;
        }else{
            return null;
        }
    }

    public String eliminar(Reserva reserva){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="idreserva='"+reserva.getIdreserva()+"'";
        where=where+" AND codmateria='"+reserva.getIdhorario()+"'";
        where=where+" AND ciclo="+reserva.getFechaingreso();
        contador+=db.delete("reserva", where, null);
        regAfectados+=contador;
        return regAfectados;
    }



    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){
            case 1:
            {
                Reserva reserva = (Reserva)dato;
                String[] id1 = {String.valueOf(reserva.getIdreserva())};
                String[] id2 = {String.valueOf(reserva.getIdhorario())};
                //abrir();
                Cursor cursor1 = db.query("reserva", null, "idreserva = ?", id1, null,
                        null, null);
                Cursor cursor2 = db.query("detallereserva", null, "idhorario = ?", id2,
                        null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;

            }

            case 2:
            {

            }

            case 3:
            {

            }

            case 4:
            {

            }

            case 5:
            {

            }

            case 6:
            {
                //verificar que exista Reserva
                Reserva reservay = (Reserva)dato;
                String[] idm = {String.valueOf(reservay.getIdreserva())};
                abrir();
                Cursor cm = db.query("reserva", null, "idreserva = ?", idm, null,
                        null, null);
                if(cm.moveToFirst()){
                    //Se encontro la Reserva
                    return true;
                }
                return false;
            }

            default:
            {
                return false;

            }


        }


    }
}
