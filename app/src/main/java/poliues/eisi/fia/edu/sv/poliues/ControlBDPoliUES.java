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

    private static final  String[] camposSolicitud = new  String[]
        {"idSolicitud", "actividad", "tarifa", "administrador", "motivoSolicitud", "fechaCreacion"};

    private static final String[] camposDetalleSolicitud = new String[]
            {"idDescripcion", "solicitud", "area", "fechaInicio", "fechaFinal", "cobroTotal"};



    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;



    public ControlBDPoliUES(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
      }


    private static class DatabaseHelper extends SQLiteOpenHelper{

        private static final String BASE_DATOS = "PoliUES.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context){
            super(context,BASE_DATOS,null,VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){

            try{

                db.execSQL("CREATE TABLE Solicitud(idSolicitud INTEGER NOT NULL PRIMARY KEY, actividad INTEGER, tarifa INTEGER, administrador INTEGER,solicitante INTEGER; motivoSolicitud VARCHAR(100), fechaCreacion VARCHAR(10));");
                db.execSQL("CREATE TABLE DetalleSolicitud(idDescripcion INTEGER NOT NULL PRIMARY KEY, solicitud INTEGER, area INTEGER, fechaInicio VARCHAR(10), fechaFinal VARCHAR(10), cobroTotal REAL);");

                System.out.println("SE EJECUTO LA CREACION DE TABLAS");

                /*COMPRUEBA QUE EXISTA LA ACTIVIDAD EN LA CREACION DE SOLICITUD-trigger-RODRIGO*/
                db.execSQL("CREATE TRIGGER fk_Solicitud_Actividad" +
                        " BEFORE INSERT ON Solicitud" +
                        " FOR EACH ROW" +
                        " BEGIN" +
                        "   SELECT CASE" +
                        "   WHEN((SELECT idActividad from Actividad WHERE idActividad = actividad) IS NULL)" +
                        "   WHEN((SELECT idTarifa from Tarifa WHERE idTarifa = tarifa) IS NULL)" +
                        "   WHEN((SELECT idSolicitante from Solicitante WHERE idSolicitante = solicitante) IS NULL)" +
                        "   THEN RAISE(ABORT, 'NO SIRVE')" +
                        " END;" +
                        " END;");

                /*COMPRUEBA QUE EXISTA LA TARIFA EN LA CREACION DE SOLICITUD-trigger-RODRIGO*/
                db.execSQL("CREATE TRIGGER fk_Solicitud_Tarifa" +
                        " BEFORE INSERT ON Solicitud" +
                        " FOR EACH ROW" +
                        " BEGIN" +
                        "   SELECT CASE" +
                        "   WHEN((SELECT idTarifa from Tarifa WHERE idTarifa = tarifa) IS NULL)" +
                        "   THEN RAISE(ABORT, 'NO EXISTE LA TARIFA')" +
                        " END;" +
                        " END;");

                /*COMPRUEBA QUE EXISTA El SOLICITANTE EN LA CREACION DE SOLICITUD-trigger-RODRIGO*/
                db.execSQL("CREATE TRIGGER fk_Solicitud_Solicitante" +
                        " BEFORE INSERT ON Solicitud" +
                        " FOR EACH ROW" +
                        " BEGIN" +
                        "   SELECT CASE" +
                        "   WHEN((SELECT idSolicitante from Solicitante WHERE idSolicitante = solicitante) IS NULL)" +
                        "   THEN RAISE(ABORT, 'NO EXISTE El solicitante')" +
                        " END;" +
                        " END;");

                /*COMPRUEBA QUE EXISTA LA SOLICITUD EN LA CREACION DE DETALLESOLICITUD-trigger-RODRIGO*/
                db.execSQL("CREATE TRIGGER fk_DetalleSolicitud_Solicitud" +
                        " BEFORE INSERT ON DetalleSolicitud" +
                        " FOR EACH ROW" +
                        " BEGIN" +
                        "   SELECT CASE" +
                        "   WHEN((SELECT idSolicitud from Solicitud WHERE idSolicitud = solicitud) IS NULL)" +
                        "   THEN RAISE(ABORT, 'NO EXISTE LA SOLICITUD')" +
                        " END;" +
                        " END;");

                /*COMPRUEBA QUE EXISTA LA AREA EN LA CREACION DE DETALLESOLICITUD-trigger-RODRIGO*/
                db.execSQL("CREATE TRIGGER fk_DetalleSolicitud_Area" +
                        " BEFORE INSERT ON DetalleSolicitud" +
                        " FOR EACH ROW" +
                        " BEGIN" +
                        "   SELECT CASE" +
                        "   WHEN((SELECT idArea from Area WHERE idArea = area) IS NULL)" +
                        "   THEN RAISE(ABORT, 'NO EXISTE El area')" +
                        " END;" +
                        " END;");

                /*COMPRUEBA QUE EXISTA LA AREA EN LA CREACION DE DETALLESOLICITUD-trigger-RODRIGO*/
                db.execSQL("CREATE TRIGGER fk_DetalleSolicitud_Area" +
                        " BEFORE INSERT ON DetalleSolicitud" +
                        " FOR EACH ROW" +
                        " BEGIN" +
                        "   SELECT CASE" +
                        "   WHEN((SELECT idArea from Area WHERE idArea = area) IS NULL)" +
                        "   THEN RAISE(ABORT, 'NO EXISTE El area')" +
                        " END;" +
                        " END;");


            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }



    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
    }

    public void cerrar(){
        DBHelper.close();
    }


    /*CRUD SOLICITUD-RODRIGO*/
    public String insertar(Solicitud solicitud){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues sol = new ContentValues();

        sol.put("idSolicitud",solicitud.getIdSolicitud());
        sol.put("actividad",solicitud.getActividad());
        sol.put("tarifa",solicitud.getTarifa());
        sol.put("administrador",solicitud.getAdministrador());
        sol.put("solicitante",solicitud.getSolicitante());
        sol.put("motivoSolicitud",solicitud.getMotivoSolicitud());
        sol.put("fechaCreacion",solicitud.getFechaCreacion());

        contador=db.insert("Solicitud", null, sol);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public String actualizar(Solicitud solicitud){
        return null;
    }

    public String eliminar(Solicitud solicitud){
        return null;
    }

    public String consultarSolicitud(Solicitud solicitud){
        return null;
    }


    /*CRUD DETALLESOLICITUD-RODRIGO*/
    public String insertar(DetalleSolicitud detalleSolicitud){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues detSol = new ContentValues();

        detSol.put("idDescripcion",detalleSolicitud.getIdDescripcion());
        detSol.put("solicitud",detalleSolicitud.getSolicitud());
        detSol.put("area",detalleSolicitud.getArea());
        detSol.put("fechaInicio",detalleSolicitud.getFechaInicio());
        detSol.put("fechaFinal",detalleSolicitud.getFechaFinal());
        detSol.put("cobroTotal",detalleSolicitud.getCobroTotal());


        contador=db.insert("DetalleSolicitud", null, detSol);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public String actualizar(DetalleSolicitud detalleSolicitud){
        return null;
    }

    public String eliminar(DetalleSolicitud detalleSolicitud){
        return null;
    }

    public String consultarSolicitud(DetalleSolicitud detalleSolicitud){
        return null;
    }



    public String llenarBDSR11038(){

           /*REGISTROS TABLA SOLICITUD*/
        final int[] TSidSolicitud = {1,2};
        final int[] TSactividad = {1,3};
        final int[] TStarifa = {1,1};
        final int[] TSadministrador = {1,1};
        final int[] TSsolicitante = {1,2};
        final String[] TSmotivoSolicitud = {"intramuros","juegos interfacultad"};
        final String[] TSfechaCreacion = {"2016-04-29","206-04-30"};


        /*REGISTROS TABLA DETALLESOLICITUD*/
        final int[] TDSidDescripcion = {1,2};
        final int[] TDSsolicitud = {1, 2};
        final int[] TDSarea = {1, 3};
        final String[] TDSfechaInicio ={"2016-05-02", "2016-05-04"};
        final String[] TDSfechaFinal ={"2016-05-02", "2016-05-05"};
        final double[] TDScobroTotal = {0,0};


        abrir();

        db.execSQL("DELETE FROM Solicitud");
        db.execSQL("DELETE FROM DetalleSolicitud");
        //db.execSQL("DELETE FROM DetalleSolicitud");


        Solicitud soli = new Solicitud();

        for(int i=0;i<2;i++) {
            soli.setIdSolicitud(TSidSolicitud[i]);
            soli.setActividad(TSactividad[i]);
            soli.setTarifa(TDSsolicitud[i]);
            soli.setAdministrador(TSadministrador[i]);
            soli.setSolicitante(TSsolicitante[i]);
            soli.setMotivoSolicitud(TSmotivoSolicitud[i]);
            soli.setFechaCreacion(TSfechaCreacion[i]);
           insertar(soli);
        }

        DetalleSolicitud DS = new DetalleSolicitud();
        for(int i=0;i<2;i++){
            DS.setIdDescripcion(TDSidDescripcion[i]);
            DS.setSolicitud(TDSsolicitud[i]);
            DS.setArea(TDSarea[i]);
            DS.setFechaInicio(TDSfechaInicio[i]);
            DS.setFechaFinal(TDSfechaFinal[i]);
            DS.setCobroTotal(TDScobroTotal[i]);
            insertar(DS);
        }


        cerrar();
        return "Guardo Correctamente";
    }
}


