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
        {"idSolicitud", "actividad", "tarifa", "administrador", "motivoSolicitud","estadoSolicitud", "fechaCreacion"};

    private static final String[] camposDetalleSolicitud = new String[]
            {"idDescripcion", "solicitud", "area", "fechaInicio", "fechaFinal", "cobroTotal"};



    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;



    public ControlBDPoliUES(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

        //DBHelper.onCreate(db);
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
                ////////////////////////////////////////////////////////////////////////////////////
                ///DROP TBL
               db.execSQL(
                        "DROP TABLE Solicitante; " +
                       "DROP TABLE Administrador;" +
                        "DROP TABLE Solicitud;" +
                        "DROP TABLE DetalleSolicitud;"

                );
                //FIN DROP TBL
                ////////////////////////////////////////////////////////////////////////////////////

                db.execSQL("CREATE TABLE Solicitud(" +
                        "idSolicitud INTEGER NOT NULL PRIMARY KEY, " +
                        "actividad INTEGER, tarifa INTEGER, " +
                        "administrador INTEGER," +
                        "solicitante INTEGER; " +
                        "motivoSolicitud VARCHAR(100)," +
                        "estadoSolicitud VARCHAR (20)," +
                        "fechaCreacion VARCHAR(10));");


                db.execSQL("CREATE TABLE DetalleSolicitud(" +
                        "idDescripcion INTEGER NOT NULL PRIMARY KEY," +
                        "solicitud INTEGER, " +
                        "area INTEGER, " +
                        "fechaInicio VARCHAR(10), " +
                        "fechaFinal VARCHAR(10), " +
                        "cobroTotal REAL);");

                /////////////////////////////////////////////////////////////////////
                ////MOTTO TBL
                db.execSQL(
                        "CREATE TABLE Administrador " +
                        "(" +
                        "   idAdministrador      INTEGER              NOT NULL," +
                        "   nombreAdmin          VARCHAR2(25)         NOT NULL," +
                        "   paswordAdmin         VARCHAR2(25)         NOT NULL," +
                        "   correoAdmin          VARCHAR2(25)         NOT NULL" +
                        ");"
                );
                db.execSQL(
                        "CREATE TABLE Solicitud " +
                        "(" +
                        "   idSolicitante        INTEGER              NOT NULL," +
                        "   nombre               VARCHAR2(25)         NOT NULL," +
                        "   password             VARCHAR2(25)         NOT NULL," +
                        "   correo               VARCHAR2(25)         NOT NULL" +
                        ");"
                );
                //FIN CREACION TBL MOTTO
                //////////////////////////////////////////////////////////////////

                System.out.println("SE EJECUTO LA CREACION DE TABLAS");


                /////////////////////////////////////////////////////////////////////////////
                //TRIGGER == FK



                //FIN TRIGGER == FK
                ////////////////////////////////////////////////////////////////////////////////////
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
    public String insertar(Solicitud solicitud) {
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
        final String[] TSestadoSolicitud = {"aprobada","negada"};
        final String[] TSfechaCreacion = {"2016-04-29","206-04-30"};


        /*REGISTROS TABLA DETALLESOLICITUD*/
        final int[] TDSidDescripcion = {1,2};
        final int[] TDSsolicitud = {1, 2};
        final int[] TDSarea = {1, 3};
        final String[] TDSfechaInicio ={"2016-05-02", "2016-05-04"};
        final String[] TDSfechaFinal ={"2016-05-02", "2016-05-05"};
        final double[] TDScobroTotal = {0,0};


        abrir();
        //db.execSQL("DELETE FROM Solicitud");
        //db.execSQL("DELETE FROM DetalleSolicitud");
        //db.execSQL("DELETE FROM nota");


        Solicitud soli = new Solicitud();

        for(int i=0;i<2;i++) {
            soli.setIdSolicitud(TSidSolicitud[i]);
            soli.setActividad(TSactividad[i]);
            soli.setTarifa(TDSsolicitud[i]);
            soli.setAdministrador(TSadministrador[i]);
            soli.setSolicitante(TSsolicitante[i]);
            soli.setMotivoSolicitud(TSmotivoSolicitud[i]);
            soli.setEstadoSolicitud(TSestadoSolicitud[i]);
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


