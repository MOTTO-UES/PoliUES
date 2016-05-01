package poliues.eisi.fia.edu.sv.poliues;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

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

}
