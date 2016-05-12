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
            {"idSolicitud", "actividad", "tarifa", "administrador","solicitante", "motivoSolicitud","estadoSolicitud", "fechaCreacion"};

    private static final String[] camposDetalleSolicitud = new String[]
            {"idDescripcion", "solicitud", "area", "fechaInicio", "fechaFinal", "cobroTotal"};

    private static final String[] camposAdministrador = new String[]
            {"IDADMINISTRADOR","NOMBREADMINISTRADOR","PASSWORDADMINISTRADOR","CORREOADMINISTRADOR"};

    private static final String[] camposSolicitante = new String[]
            {"IDSOLICITANTE","NOMBRE","PASSWORD","CORREO"};


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;



    public ControlBDPoliUES(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
      }


    private static class DatabaseHelper extends SQLiteOpenHelper{

        private static final String BASE_DATOS = "PoliUES.s3db";
        private static final int VERSION =1;

        public DatabaseHelper(Context context){
            super(context,BASE_DATOS,null,VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){

            try{

                ////////////////////////////////////////////////////////////////////////////////////

                db.execSQL("CREATE TABLE Solicitud(" +
                        "idSolicitud INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "actividad INTEGER, " +
                        "tarifa INTEGER, " +
                        "administrador INTEGER," +
                        "solicitante INTEGER, " +
                        "motivoSolicitud VARCHAR(100)," +
                        "estadoSolicitud VARCHAR (20)," +
                        "fechaCreacion VARCHAR(25))");


                db.execSQL("CREATE TABLE DetalleSolicitud(" +
                        "idDescripcion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "solicitud INTEGER, " +
                        "area INTEGER, " +
                        "fechaInicio VARCHAR(10), " +
                        "fechaFinal VARCHAR(10), " +
                        "cobroTotal REAL)");


                /////////////////////////////////////////////////////////////////////
                ////MOTTO TBL
                db.execSQL(
                        "CREATE TABLE ADMINISTRADOR (" +
                                "IDADMINISTRADOR INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
                                "NOMBREADMINISTRADOR VARCHAR(25)  NOT NULL," +
                                "PASSWORDADMINISTRADOR VARCHAR(25)  NOT NULL," +
                                "CORREOADMINISTRADOR VARCHAR(25)  NOT NULL" +
                                ")"
                );
                db.execSQL(
                        "CREATE TABLE SOLICITANTE (" +
                                "IDSOLICITANTE INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
                                "NOMBRE VARCHAR(25)  NOT NULL," +
                                "PASSWORD VARCHAR(25)  NOT NULL," +
                                "CORREO VARCHAR(25)  NOT NULL" +
                                ")"
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
                ////////////////////////////////////////////////////////////////////
                //DROP TBL_MOTTO
                //db.execSQL("DROP TABLE IF EXIST ADMINISTRADOR");
                //db.execSQL("DROP TABLE IF EXIST SOLICITANTE");
                ////////////////////////////////////////////////////////////////////

                onCreate(db);
        }
    }



    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
    }
    public void  leer() throws SQLException{
        db = DBHelper.getReadableDatabase();
    }

    public void cerrar(){
        DBHelper.close();
    }


    ////////////////////////////////////////////////////////////////////////////////
    //Insertar Administrador
    public String insertarAdministrador(Administrador administrador){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues val = new ContentValues();

        //val.put(camposAdministrador[0],administrador.getIdAdministrador());
        val.put(camposAdministrador[1],administrador.getNombreAdmin());
        val.put(camposAdministrador[2],administrador.getPasswordAdmin());
        val.put(camposAdministrador[3],administrador.getCorreoAdmin());

        contador=db.insert("ADMINISTRADOR", null, val);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el Administrador, Administrador Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }
    //Consultar Administrador
    public Cursor consultarAdministrador(){
        Cursor c = db.query("ADMINISTRADOR",camposAdministrador,null,null,null,null,null,null);
        return c;
    }
    //Eliminar Administrador
    public String eliminarAdministrador(Administrador administrador){

        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("ADMINISTRADOR", "IDADMINISTRADOR='"+administrador.getIdAdministrador()+"'", null);
        regAfectados+=contador;

        return regAfectados;
    }
    //Actualizar Solicitante
    public String actualizarAdministrador(Administrador administrador){

        String[] IDADMINISTRADOR = {String.valueOf(administrador.getIdAdministrador())};

        ContentValues cv = new ContentValues();

        cv.put("NOMBREADMINISTRADOR", administrador.getNombreAdmin());
        cv.put("PASSWORDADMINISTRADOR", administrador.getPasswordAdmin());
        cv.put("CORREOADMINISTRADOR", administrador.getCorreoAdmin());

        db.update("ADMINISTRADOR", cv, "IDADMINISTRADOR = ?", IDADMINISTRADOR);

        return "Administrador Actualizado Correctamente";
    }
    /////////////////////////////////////////////////////////////////////////////
    //Agregar Solicitante
    public String insertarSolicitante(Solicitante solicitante){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues val = new ContentValues();

        ///val.put(camposAdministrador[0],administrador.getIdAdministrador());
        val.put(camposSolicitante[1],solicitante.getNombre());
        val.put(camposSolicitante[2],solicitante.getPassword());
        val.put(camposSolicitante[3], solicitante.getCorreo());


        contador = db.insert("SOLICITANTE", null, val);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el Solicitante, Soicitante Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }
    //Consultar Solicitante
    public Cursor consultarSolicitante(){
        Cursor c = db.query("SOLICITANTE", camposSolicitante,null,null,null,null,null,null);
        return c;
    }
    //Eliminar Solicitante
    public String eliminarSolicitante(Solicitante solicitante){

        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("SOLICITANTE", "IDSOLICITANTE='"+solicitante.getIdSolicitante()+"'", null);
        regAfectados+=contador;

        return regAfectados;
    }
    //Actualizar Solicitante
    public String actualizarSolicitante(Solicitante solicitante){

        String[] IDSOLICITANTE = {String.valueOf(solicitante.getIdSolicitante())};

        ContentValues cv = new ContentValues();

        cv.put("NOMBRE", solicitante.getNombre());
        cv.put("PASSWORD", solicitante.getPassword());
        cv.put("CORREO", solicitante.getCorreo());

        db.update("SOLICITANTE", cv, "IDSOLICITANTE = ?", IDSOLICITANTE);

        return "Solicitante Actualizado Correctamente";
    }

    /////////////////////////////////////////////////////////////////////////////

    /*CRUD SOLICITUD-RODRIGO*/
    public String insertar(Solicitud solicitud) {
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues sol = new ContentValues();

        //sol.put("idSolicitud",solicitud.getIdSolicitud());
        sol.put("actividad",solicitud.getActividad());
        sol.put("tarifa",solicitud.getTarifa());
        sol.put("administrador",solicitud.getAdministrador());
        sol.put("solicitante",solicitud.getSolicitante());
        sol.put("motivoSolicitud",solicitud.getMotivoSolicitud());
        sol.put("estadoSolicitud",solicitud.getEstadoSolicitud());
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

    public Cursor consultarSolicitud(){
        Cursor c = db.query("Solicitud",camposSolicitud,null,null,null,null,null,null);
        return c;
    }

    public String actualizar(Solicitud solicitud){
        return null;
    }

    public String eliminar(Solicitud solicitud) {
        return null;
    }

    public Solicitud consultarSolicitudUltima(){

        Cursor cursor = db.query("Solicitud", camposSolicitud,null ,null, null, null, null,null);

        if(cursor.moveToLast()){
            Solicitud solicitud = new Solicitud();

            solicitud.setIdSolicitud(cursor.getInt(0));
            solicitud.setActividad(cursor.getInt(1));
            solicitud.setTarifa(cursor.getInt(2));
            solicitud.setAdministrador(cursor.getInt(3));
            solicitud.setSolicitante(cursor.getInt(4));
            solicitud.setMotivoSolicitud(cursor.getString(5));
            solicitud.setEstadoSolicitud(cursor.getString(6));
            solicitud.setFechaCreacion(cursor.getString(7));

            System.out.println(solicitud.getIdSolicitud());
            return solicitud;
        }else{
            return null;
        }
    }

    public Solicitud buscarSolicitud(Cursor C, String motivo){
        Solicitud solicitud=null;

        if(C.moveToFirst()){

            do {

                solicitud = new Solicitud();

                solicitud.setIdSolicitud(C.getInt(0));
                solicitud.setActividad(C.getInt(1));
                solicitud.setTarifa(C.getInt(2));
                solicitud.setAdministrador(C.getInt(3));
                solicitud.setSolicitante(C.getInt(4));
                solicitud.setMotivoSolicitud(C.getString(5));
                solicitud.setEstadoSolicitud(C.getString(6));
                solicitud.setFechaCreacion(C.getString(7));

                if (solicitud.getMotivoSolicitud().equals(motivo)){
                    break;
                }
            }while(C.moveToNext());
        }

        return solicitud;
    }


    /*CRUD DETALLESOLICITUD-RODRIGO*/
    public String insertarDS(DetalleSolicitud detalleSolicitud){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues detSol = new ContentValues();

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

    public Cursor consultarDetalleSolicitud(){
        Cursor c = db.query("DetalleSolicitud",camposDetalleSolicitud,null,null,null,null,null,null);
        return c;
    }

    public DetalleSolicitud buscarDetalleSolicitud(Cursor C, int sol){
        DetalleSolicitud DS=null;

        if(C.moveToFirst()){

            do {

                DS = new DetalleSolicitud();

                DS.setIdDescripcion(C.getInt(0));
                DS.setSolicitud(C.getInt(1));
                DS.setArea(C.getInt(2));
                DS.setFechaInicio(C.getString(3));
                DS.setFechaFinal(C.getString(4));
                DS.setCobroTotal(C.getDouble(5));

                if (DS.getSolicitud() == sol){
                    break;
                }

            }while(C.moveToNext());
        }

        return DS;
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
            insertarDS(DS);
        }
        cerrar();
        return "Guardo Correctamente";
    }
}



