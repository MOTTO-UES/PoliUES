package poliues.eisi.fia.edu.sv.poliues;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by jonathan on 21/4/2016.
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

    private static final String[] camposActividad = new String[]
            {"IDACTIVIDAD","NOMBREACTIVIDAD","DESCRIPCIONACTIVIDAD"};

    private static final  String[] camposreserva = new  String[] {"idreserva","idfacultad","fechaingreso","numeropersonas","motivo","descripcionreserva"};
    private static final  String[] camposdetallereserva = new String[] { "iddetallereserva","idarea","idreserva"};
    private static final  String[] camposarea = new String[] {"idarea","maximopersonas","nombrearea","descripcionarea"};
    private static final  String[] camposhorario = new String[] {"idhorario","idreserva","fechareserva"," horarioinicio"," horariofin"};
    private static final  String[] camposfacultad = new String[] {"idfacultad","nombrefacultad"};


    private static final String[]camposDeporte = new String [] {"iddeporte","nombredeporte","descripciondeporte"};
    private static final String[]camposArea = new String [] {"idarea","maximopersonas","nombrearea","descripcionarea"};

    private static final String[] camposTarifa = new String[]
            {"idtarifa", "cantpersonas", "tarifaunitaria"};

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
                //TABLAS RODRIGO
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
                db.execSQL(
                        "CREATE TABLE ACTIVIDAD (" +
                                "IDACTIVIDAD INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
                                "NOMBREACTIVIDAD VARCHAR(25)  NOT NULL," +
                                "DESCRIPCIONACTIVIDAD VARCHAR(25)  NOT NULL" +
                                ")"
                );

                //FIN CREACION TBL MOTTO
                //////////////////////////////////////////////////////////////////


                //TABLAS ALEX
                db.execSQL("create table reserva (idreserva  INTEGER PRIMARY KEY AUTOINCREMENT,idfacultad INTEGER NOT NULL,fechaingreso VARCHAR(50) NOT NULL,numeropersonas INTEGER NOT NULL, motivo VARCHAR(25) NOT NULL,descripcionreserva   VARCHAR(50) NOT NULL);");
                db.execSQL("create table detallereserva ( iddetallereserva INTEGER PRIMARY KEY AUTOINCREMENT,idarea INTEGER NOT NULL, idreserva INTEGER NOT NULL);");
                db.execSQL("create table horario ( idhorario INTEGER PRIMARY KEY AUTOINCREMENT,idreserva INTEGER,fechareserva VARCHAR(50) NOT NULL, horarioinicio VARCHAR(50) NOT NULL, horariofin VARCHAR(50)  NOT NULL);");
                db.execSQL("create table facultad(idfacultad INTEGER PRIMARY KEY AUTOINCREMENT ,nombrefacultad VARCHAR(40));");


                //TABLAS JW
                db.execSQL("create table deporte (" +
                        "iddeporte INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombredeporte VARCHAR(50) NOT NULL," +
                        "descripciondeporte  VARCHAR(30) NOT NULL)");

                db.execSQL("create table area (" +
                        "idarea INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "maximopersonas INTEGER NOT NULL," +
                        "nombrearea  VARCHAR(30) NOT NULL," +
                        "descripcionarea VARCHAR(50) NOT NULL)");

                db.execSQL("create table deportearea (" +
                        "iddeportearea  INTEGER not null ," +
                        "idarea  INTEGER,iddeporte  INTEGER," +
                        "constraint PK_DEPORTEAREA primary key (iddeportearea))");


                //TABLA GERARDO
                db.execSQL(
                        "CREATE TABLE TARIFA (" +
                                "idtarifa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                                "cantpersonas INTEGER NOT NULL,"+
                                "tarifaunitaria REAL"+
                                ")"
                );

                //Creamos nuestros triggers

                /*
                db.execSQL("CREATE TRIGGER exiten_id_detallereserva" +
                        "BEFORE INSERT ON detallereserva" +
                        "FOR EACH ROW" +
                        "BEGIN" +
                        "SELECT CASE" +
                        //verifica que exista el area para detallereserva
                        "WHEN((SELECT idarea from area WHERE idarea = NEW.idarea) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe el area')" +
                        //verifica que exista la reserva para detallereserva
                        "WHEN((SELECT idreserva from reserva WHERE idreserva = NEW.idreserva) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe la reserva')" +
                        "END;" +
                        "END;");
                //verifica que exista el reserva para horario
                db.execSQL("CREATE TRIGGER exite_idreserva_en_reserva_horario" +
                        "BEFORE INSERT ON horario" +
                        "FOR EACH ROW" +
                        "BEGIN" +
                        "SELECT CASE" +
                        "WHEN((SELECT idreserva from reserva WHERE idreserva = NEW.idreserva) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe la reserva')" +
                        "END;" +
                        "END;");
                //verifica que exista la faculta para reserva
                db.execSQL("CREATE TRIGGER exite_idfacultad_en_facultad_reserva" +
                        "BEFORE INSERT ON reserva" +
                        "FOR EACH ROW" +
                        "BEGIN" +
                        "SELECT CASE" +
                        "WHEN((SELECT idfacultad from facultad WHERE idfacultad = NEW.idfacultad) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe la facultad')" +
                        "END;" +
                        "END;");
                        */



                System.out.println("SE EJECUTO LA CREACION DE TABLAS");


                /////////////////////////////////////////////////////////////////////////////
                //TRIGGER == FK


                //FIN TRIGGER == FK
                ////////////////////////////////////////////////////////////////////////////////////
            }catch(SQLException e){

                System.out.println("ubo un error en la creacion de tablas");

                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                ////////////////////////////////////////////////////////////////////
                //DROP TBL_MOTTO
                db.execSQL("DROP TABLE IF EXIST ADMINISTRADOR");
                db.execSQL("DROP TABLE IF EXIST SOLICITANTE");
                db.execSQL("DROP TABLE IF EXIST ACTIVIDAD");
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
    //Actualizar Administrador
    public String actualizarAdministrador(Administrador administrador){

        String[] IDADMINISTRADOR = {String.valueOf(administrador.getIdAdministrador())};
        ContentValues cv = new ContentValues();

        cv.put("NOMBREADMINISTRADOR", administrador.getNombreAdmin());
        cv.put("PASSWORDADMINISTRADOR", administrador.getPasswordAdmin());
        cv.put("CORREOADMINISTRADOR", administrador.getCorreoAdmin());

        db.update("ADMINISTRADOR", cv, "IDADMINISTRADOR = ?", IDADMINISTRADOR);

        return "Administrador Actualizado Correctamente";
    }
    //Obtener Administrador por Correo
    public Administrador obtenerAdministradorPorCorreo(String correo){

        String[] Correo = {String.valueOf(correo)};
        Administrador administrador = new Administrador();

        Cursor c = db.query("ADMINISTRADOR",camposAdministrador,"CORREOADMINISTRADOR=?",Correo,null,null,null,null);

        if(c.moveToFirst()){
            do {
                administrador.setIdAdministrador(c.getInt(0));
                administrador.setNombreAdmin(c.getString(1));
                administrador.setPasswordAdmin(c.getString(2));
                administrador.setCorreoAdmin(c.getString(3));
            }while(c.moveToNext());

        }
        return administrador;
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
    //Obtener Solicitante por Correo
    public Solicitante obtenerSolicitantePorCorreo(String correo){

        String[] Correo = {String.valueOf(correo)};
        Solicitante solicitante = new Solicitante();

        Cursor c = db.query("SOLICITANTE",camposSolicitante,"CORREO=?",Correo,null,null,null,null);

        if(c.moveToFirst()){
            do {
                solicitante.setIdSolicitante(c.getInt(0));
                solicitante.setNombre(c.getString(1));
                solicitante.setPassword(c.getString(2));
                solicitante.setCorreo(c.getString(3));
            }while(c.moveToNext());

        }
        return solicitante;
    }
    ///////////////////////////////////////////////////////////////////////////
    //Insertar Actividad
    public String insertarActividad(Actividad actividad){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues val = new ContentValues();

        val.put(camposActividad[1],actividad.getNombreActividad());
        val.put(camposActividad[2],actividad.getDescripcionActividad());

        contador=db.insert("ACTIVIDAD", null, val);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar la Actividad, Actividad Duplicada. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }
    //Consultar Actividad
    public Cursor consultarActividad(){
        Cursor c = db.query("ACTIVIDAD",camposActividad,null,null,null,null,null,null);
        return c;
    }
    //Eliminar Actividad
    public String eliminarActividad(Actividad actividad){

        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("ACTIVIDAD", "IDACTIVIDAD='"+actividad.getIdActividad()+"'", null);
        regAfectados+=contador;

        return regAfectados;
    }
    //Actualizar Actividad
    public String actualizarActividad(Actividad actividad){

        String[] IDACTIVIDAD = {String.valueOf(actividad.getIdActividad())};
        ContentValues val = new ContentValues();

        val.put(camposActividad[1],actividad.getNombreActividad());
        val.put(camposActividad[2],actividad.getDescripcionActividad());

        db.update("ACTIVIDAD", val, "IDACTIVIDAD = ?", IDACTIVIDAD);

        return "ACTIVIDAD Actualizada Correctamente";
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
        String[] IDSolicitud = {String.valueOf(solicitud.getIdSolicitud())};

        ContentValues Con = new ContentValues();

        Con.put("actividad",solicitud.getActividad());
        Con.put("tarifa",solicitud.getTarifa());
        Con.put("motivoSolicitud",solicitud.getMotivoSolicitud());

        db.update("Solicitud", Con,"idSolicitud = ?", IDSolicitud);

        return "Solicitud ";
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
    public String insertarDS(DetalleSolicitud detalleSolicitud) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues detSol = new ContentValues();

        detSol.put("solicitud", detalleSolicitud.getSolicitud());
        detSol.put("area", detalleSolicitud.getArea());
        detSol.put("fechaInicio", detalleSolicitud.getFechaInicio());
        detSol.put("fechaFinal", detalleSolicitud.getFechaFinal());
        detSol.put("cobroTotal", detalleSolicitud.getCobroTotal());


        contador = db.insert("DetalleSolicitud", null, detSol);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";

        }

        return regInsertados;
    }


    public String insertar(Reserva reserva){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues reserva1 = new ContentValues();
        reserva1.put("idfacultad", reserva.getIdfacultad());
        reserva1.put("fechaingreso", reserva.getFechaingreso());
        reserva1.put("numeropersonas", reserva.getNumeropersonas());
        reserva1.put("motivo", reserva.getMotivo());
        reserva1.put("descripcionreserva ",reserva.getDescripcionreserva());
        contador=db.insert("reserva", null, reserva1);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }

    public String insertar(DetalleReserva detalleReserva){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues detalle = new ContentValues();
        detalle.put("idarea", detalleReserva.getIdarea());
        detalle.put("idreserva", detalleReserva.getIdreserva());
        contador=db.insert("detallereserva", null, detalle);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";

        }
        else {
            regInsertados=regInsertados+contador;
        }


        return regInsertados;
    }

    public String actualizar(DetalleSolicitud detalleSolicitud){
        String[] IDSolicitud = {String.valueOf(detalleSolicitud.getSolicitud())};

        ContentValues Con = new ContentValues();

        Con.put("area",detalleSolicitud.getArea());
        Con.put("fechaInicio",detalleSolicitud.getFechaInicio());
        Con.put("fechaFinal",detalleSolicitud.getFechaFinal());
        Con.put("cobroTotal",detalleSolicitud.getCobroTotal());

        db.update("DetalleSolicitud", Con,"solicitud = ?", IDSolicitud);

        return "y Detalle Solicitud actualizada";

    }

    public String eliminar(DetalleSolicitud solicitud){
        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("DetalleSolicitud", "solicitud='"+solicitud.getSolicitud()+"'", null);
        contador+=db.delete("Solicitud", "idSolicitud='"+solicitud.getSolicitud()+"'", null);


        return regAfectados;
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

    public Cursor obtenerAreas(){
        Cursor c= db.query("area",camposarea,null,null,null,null,null,null);

        return c;
    }

    public Area consultarAreaNombre(Cursor cursor,String nombre){
        Area area = null;
        if(cursor.moveToFirst()){

            do {
                area = new Area();
                area.setIdarea(cursor.getInt(0));
                area.setMaximopersonas(cursor.getInt(1));
                area.setNombrearea(cursor.getString(2));
                area.setDescripcionarea(cursor.getString(3));

                if(nombre.equals(area.getNombrearea())){

                    System.out.println("mombre area buscado:"+area.getNombrearea());
                    System.out.println("id area buscado:"+area.getIdarea());
                    break;
                }

            }while(cursor.moveToNext());



            return area;
        }else{
            return null;
        }
    }

    public Area consultarAreaID(Cursor cursor,int id){
        Area area = null;
        if(cursor.moveToFirst()){

            do {
                area = new Area();
                area.setIdarea(cursor.getInt(0));
                area.setMaximopersonas(cursor.getInt(1));
                area.setNombrearea(cursor.getString(2));
                area.setDescripcionarea(cursor.getString(3));

                if(id == area.getIdarea()){

                    System.out.println("mombre area buscado:"+area.getNombrearea());
                    System.out.println("id area buscado:"+area.getIdarea());
                    break;
                }

            }while(cursor.moveToNext());



            return area;
        }else{
            return null;
        }
    }


    public String llenarBDSR11038(){

           /*REGISTROS TABLA SOLICITUD*/
        final int[] TSidSolicitud = {1,2};
        final int[] TSactividad = {1,2};
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

        String regInsertados = "";

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

        return regInsertados;

    }

    public String insertar(Horario horario) {

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        ContentValues horario1 = new ContentValues();
        horario1.put("idreserva", horario.getIdreserva());
        horario1.put("fechareserva", horario.getFechareserva());
        horario1.put("horarioinicio", horario.getHorarioinicio());
        horario1.put("horariofin", horario.getHorariofin());
        contador = db.insert("horario", null, horario1);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Facultad facultad) {

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        ContentValues facultad1 = new ContentValues();
        //facultad1 .put("idfacultad", facultad.getIdfacultad());
        facultad1 .put("nombrefacultad", facultad.getNombre());
        contador = db.insert("facultad", null, facultad1);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Area area) {

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        ContentValues area1 = new ContentValues();
        //area1.put("idarea", area.getIdarea());
        area1.put("maximopersonas", area.getMaximopersonas());
        area1.put("nombrearea", area.getNombrearea());
        area1.put("descripcionarea", area.getDescripcionarea());
                contador = db.insert("area", null, area1);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }



    public String actualizar(Reserva reserva){
        if(verificarIntegridad(reserva, 1)){
            String[] id = {String.valueOf(reserva.getIdreserva())};
            ContentValues cv = new ContentValues();
            cv.put("idfacultad", reserva.getIdfacultad());
            cv.put("fechaingreso", reserva.getFechaingreso());
            cv.put("numeropersonas", reserva.getNumeropersonas());
            cv.put("motivo", reserva.getMotivo());
            cv.put("descripcionreserva ", reserva.getDescripcionreserva());
            db.update("reserva", cv, "idreserva = ?", id);
            return "Registro de reserva Actualizado Correctamente";
        }else{
            return "Registro con codigo " + reserva.getIdreserva() + " no existe";
        }
    }

    public String actualizar(DetalleReserva detallereserva){
        if(verificarIntegridad(detallereserva, 2)){
            String[] id = {String.valueOf(detallereserva.getIdreserva())};
            ContentValues cv = new ContentValues();
            cv.put("idarea", detallereserva.getIdarea());
            cv.put("idreserva", detallereserva.getIdreserva());
            db.update("detallereserva", cv, "iddetallereserva = ?", id);
            return "Registro de detalle reserva Actualizado Correctamente";
        }else{
            return "Registro con codigo " + detallereserva.getIddetallereserva() + " no existe";
        }
    }

    public String actualizar(Horario horario){
        if(verificarIntegridad(horario, 3)){
            String[] id = {String.valueOf(horario.getIdreserva())};
            ContentValues cv = new ContentValues();
           // cv.put("idreserva", horario.getIdreserva());
            cv.put("fechareserva", horario.getFechareserva());
            cv.put("horarioinicio", horario.getHorarioinicio());
            cv.put("horariofin", horario.getHorariofin());
            db.update("horario", cv, "idreserva = ?", id);
            return "Registro de Horario Actualizado Correctamente";
        }else{
            return "Registro con codigo de reserva " + horario.getIdreserva()+ " no existe";
        }
    }

    public Reserva consultarReserva(String idreserva){
        String[] id = {idreserva};
        Cursor cursor = db.query("reserva", camposreserva, "idreserva = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Reserva reserva = new Reserva();
            reserva.setIdreserva(Integer.parseInt(cursor.getString(0)));
            reserva.setIdfacultad(Integer.parseInt(cursor.getString(1)));

            reserva.setFechaingreso(cursor.getString(2));
            reserva.setNumeropersonas(Integer.parseInt(cursor.getString(3)));
            reserva.setMotivo(cursor.getString(4));
            reserva.setDescripcionreserva(cursor.getString(5));
            return reserva;
        }else{
            return null;
        }
    }

    public Reserva consultarReserva(String idfacultab,String motivo){
        String[] id = {idfacultab,motivo};
        Cursor cursor = db.query("reserva", camposreserva, "idfacultad = ? AND motivo = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Reserva reserva = new Reserva();
            reserva.setIdreserva(Integer.parseInt(cursor.getString(0)));
            reserva.setIdfacultad(Integer.parseInt(cursor.getString(1)));

            reserva.setFechaingreso(cursor.getString(2));
            reserva.setNumeropersonas(Integer.parseInt(cursor.getString(3)));
            reserva.setMotivo(cursor.getString(4));
            reserva.setDescripcionreserva(cursor.getString(5));
            return reserva;
        }else{
            return null;
        }
    }

    public Facultad consultarFacultad(String idfacultad){
        String[] id = {idfacultad};
        Cursor cursor = db.query("facultad", camposfacultad, "idfacultad = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Facultad facu = new Facultad();
            facu.setIdfacultad(Integer.parseInt(cursor.getString(0)));
            facu.setNombre(cursor.getString(1));
            return facu;
        }else{
            return null;
        }
    }

    public Horario consultarHorario(String idreserva){
        String[] id = {idreserva};
        Cursor cursor = db.query("horario", camposhorario, "idreserva = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Horario horario1 = new Horario();
            horario1.setIdhorario(Integer.parseInt(cursor.getString(0)));
            horario1.setIdreserva(Integer.parseInt(cursor.getString(1)));
            horario1.setFechareserva(cursor.getString(2));
            horario1.setHorarioinicio(cursor.getString(3));
            horario1.setHorariofin(cursor.getString(4));
            return horario1;
        }else{
            return null;
        }
    }

    public Area consultarArea(String idarea){
        String[] id = {idarea};
        Cursor cursor = db.query("area", camposarea, "idarea = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Area area = new Area();
            area.setIdarea(Integer.parseInt(cursor.getString(0)));
            area.setMaximopersonas(Integer.parseInt(cursor.getString(1)));
            area.setNombrearea(cursor.getString(2));
            area.setDescripcionarea(cursor.getString(3));

            return area;
        }else{
            return null;
        }
    }

    public DetalleReserva consultarDetalleReserva(String idreserva){
        String[] id = {idreserva};
        Cursor cursor = db.query("detallereserva", camposdetallereserva, "idreserva = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            DetalleReserva detalle = new DetalleReserva();
            detalle.setIddetallereserva(Integer.parseInt(cursor.getString(0)));
            detalle.setIdarea(Integer.parseInt(cursor.getString(1)));
            detalle.setIdreserva(Integer.parseInt(cursor.getString(2)));

            return detalle;
        }else{
            return null;
        }
    }



    public String eliminar(Reserva reserva){

        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(reserva, 4)) {
            contador+=db.delete("detallereserva", "idreserva='"+reserva.getIdreserva()+"'", null);
        }
        if (verificarIntegridad(reserva,5)){
            contador+=db.delete("horario", "idreserva='"+reserva.getIdreserva()+"'", null);
        }
        contador+=db.delete("reserva", "idreserva='"+reserva.getIdreserva()+"'", null);
        regAfectados+=contador;
        return regAfectados;


    }

    public Cursor todaslasreservas() {
        Cursor mcursor = db.query("reserva", camposreserva, null, null,
                null, null, null);

        if (mcursor != null) {
            mcursor.moveToFirst();
        }
        return mcursor;
    }

    public Cursor todaslasfacultades() {
        Cursor mcursor = db.query("facultad", camposfacultad, null, null,
                null, null, null);

        if (mcursor != null) {
            mcursor.moveToFirst();
        }
        return mcursor;
    }


    public Cursor todaslasareas() {
        Cursor mcursor = db.query("area", camposarea, null, null,
                null, null, null);

        if (mcursor != null) {
            mcursor.moveToFirst();
        }
        return mcursor;
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){
            case 1:
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

            case 2:
            {
                //verificar que exista DetalleReserva
                DetalleReserva detalleReserva = (DetalleReserva)dato;
                String[] idm = {String.valueOf(detalleReserva.getIdreserva())};
                abrir();
                Cursor cm = db.query("detallereserva", null, "iddetallereserva = ?", idm, null,
                        null, null);
                if(cm.moveToFirst()){
                    //Se encontro el DetalleReserva
                    return true;
                }
                return false;

            }

            case 3:
            {
                //verificar que exista Horario
                Horario horario = (Horario)dato;
                String[] idm = {String.valueOf(horario.getIdreserva())};
                abrir();
                Cursor cm = db.query("horario", null, "idreserva = ?", idm, null,
                        null, null);
                if(cm.moveToFirst()){
                    //Se encontro el Horario
                    return true;
                }
                return false;

            }

            case 4:
            {
                Reserva reserva = (Reserva)dato;
                Cursor c=db.query(true, "detallereserva", new String[] {
                                "idreserva" }, "idreserva='"+reserva.getIdreserva()+"'",null,
                        null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 5:
            {
                Reserva reserva = (Reserva)dato;
                Cursor c=db.query(true, "horario", new String[] {
                                "idreserva" }, "idreserva='"+reserva.getIdreserva()+"'",null,
                        null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;

            }
            default:
            {
                return false;

            }


        }


    }

    public String llenarBDPoli(){

        final String[] VFidFacultad = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        final String[] VFnombre = {"Agronomia","Paracentral","Ingenieria y Arquitectura","Naturales y Matematica","Economia","Jurisprudencia Ciencias Sociales","Humanidades","Medidicina","Quimica y Farmacia","Odontologia","Multiciplinaria de Occidente","Multiciplinaria de Oriente"};


        final String[] VAidarea = {"1","2","3"};
        final String[] VAmaximopersona = {"30","40","50"};
        final String[] VAnombrearea = {"Cancha Engramada","Cancha Duela","Cancha de Cemento"};
        final String[] VAdescripcionarea = {"La mejor Cancha","Multi Usos","Multi Uso 2"};

        final String[] VDRidetalle = {"1","2","3"};
        final String[] VDRidarea = {"1","2","3"};
        final String[] VDRidreserva = {"1","2","3"};



        final String[] VHidhorario = {"1","2","3"};
        final String[] VHidreserva = {"1","2","3"};
        final String[] VHfechareserva = {"3/5/2016","3/5/2016","3/5/2016"};
        final String[] VHhorarioinicio = {"10 PM ","11:00 AM","10:00 PM"};
        final String[] VHhorariofin = {"11:00 AM","12:00 AM","11:00 PM"};

        final String[] VRidreserva = {"1","2","3"};
        final String[] VRidfacultad = {"1","2","3"};
        final String[] VRfechaingreso = {"3/5/2016","3/5/2016","3/5/2016"};
        final String[] VRnumeropersonaa = {"10","20","40"};
        final String[] VRmotivo = {"Intramuros","Graduaciones","Interfacultades"};
        final String[] VRdescripconreserva = {"Intramuros del Ricardone","Graduaciones de todas las Facultades","Juegos interfacultades"};

        abrir();
        db.execSQL("DELETE FROM facultad");
        db.execSQL("DELETE FROM reserva");
        db.execSQL("DELETE FROM horario ");
        db.execSQL("DELETE FROM detallereserva");
         /*llenamos con datos*/

        Facultad facultad = new Facultad();
        for(int i=0;i<12;i++){
            facultad.setIdfacultad(Integer.parseInt(VFidFacultad[i]));
            facultad.setNombre(VFnombre[i]);
            insertar(facultad);
        }


        Reserva reserva = new Reserva();
        for(int i=0;i<3;i++){
            reserva.setIdreserva(Integer.parseInt(VRidreserva[i]));
            reserva.setIdfacultad(Integer.parseInt(VRidfacultad[i]));
            reserva.setNumeropersonas(Integer.parseInt((VRnumeropersonaa[i])));
            reserva.setFechaingreso(VRfechaingreso[i]);
            reserva.setMotivo(VRmotivo[i]);
            reserva.setDescripcionreserva(VRdescripconreserva[i]);
            insertar(reserva);
        }

        DetalleReserva dreserva = new DetalleReserva();
        for(int i=0;i<3;i++){
            dreserva.setIddetallereserva(Integer.parseInt(VDRidetalle[i]));
            dreserva.setIdarea(Integer.parseInt(VDRidarea[i]));
            dreserva.setIdreserva(Integer.parseInt(VDRidreserva[i]));
            insertar(dreserva);
        }

        Horario horario = new Horario();
        for(int i=0;i<3;i++){
            horario.setIdhorario(Integer.parseInt(VHidhorario[i]));
            horario.setIdreserva(Integer.parseInt(VHidreserva[i]));
            horario.setFechareserva(VHfechareserva[i]);
            horario.setHorarioinicio(VHhorarioinicio[i]);
            horario.setHorariofin(VHhorariofin[i]);

            insertar(horario);
        }




        cerrar();
        System.out.println("se crearon todas las insert");
        return "Guardo Correctamente";
    }


    public String crearAdmin(){
        String mensaje = null;
        Administrador ad = new Administrador();
        ad.setCorreoAdmin("rodrigoxj32@hotmail.com");
        ad.setNombreAdmin("Rodrigo Romero");
        ad.setPasswordAdmin("12345");

        db.execSQL("DELETE FROM ADMINISTRADOR");
        mensaje = insertarAdministrador(ad);

        return mensaje;
    }


    //CODIGO DE JW
    public String insertarArea(Area area){
        String regInsertados="Area Insertado N =";
        long contador=0;

        ContentValues are =new ContentValues();
        are.put("idarea",area.getIdarea());
        are.put("maximopersonas",area.getMaximopersonas());
        are.put("nombrearea",area.getNombrearea());
        are.put("descripcionarea",area.getDescripcionarea());

        contador=db.insert("area", null,are);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el area, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }

    public String actualizarArea(Area area){
        String[] id = {String.valueOf(area.getIdarea())};
        ContentValues cv = new ContentValues();
        cv.put("maximopersonas",area.getMaximopersonas());
        cv.put("nombrearea",area.getNombrearea());
        cv.put("descripcionarea",area.getDescripcionarea());
        db.update("area", cv, "idarea = ?", id);
        return "Area Actualizada Correctamente";

    }

    public Area consultarAreaJ(String idArea){
        String[] id = {idArea};
        Cursor cursor = db.query("area", camposArea, "idarea = ?",id, null, null,null,null);
        if(cursor.moveToFirst()){
            Area area = new Area();
            area.setIdarea(cursor.getInt(0));
            area.setMaximopersonas(cursor.getInt(1));
            area.setNombrearea(cursor.getString(2));
            area.setDescripcionarea(cursor.getString(3));

            System.out.println("encontrado:"+area.getIdarea() );
            return area;

        }else{
            System.out.println("NO encontrado:");
            return null;
        }
    }

    public String eliminarArea(Area area){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="idarea='"+area.getIdarea()+"'";
        contador+=db.delete("area", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String insertar(Deporte deporte) {
        String regInsertados="Deporte Insertado N =";
        long contador=0;
        ContentValues alum =new ContentValues();
        alum.put("iddeporte", deporte.getIddeporte());
        alum.put("nombredeporte",deporte.getNombredeporte());
        alum.put("descripciondeporte",deporte.getDescripciondeporte());
        contador=db.insert("deporte", null, alum);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el deporte, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }



    public String eliminarDeporte(Deporte deporte){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="iddeporte='"+deporte.getIddeporte()+"'";
        contador+=db.delete("deporte", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String actualizar(Deporte deporte){
        String[] id = {String.valueOf(deporte.getIddeporte())};
        ContentValues cv = new ContentValues();
        cv.put("nombredeporte",deporte.getNombredeporte());
        cv.put("descripciondeporte",deporte.getDescripciondeporte());
        db.update("deporte", cv, "iddeporte = ?", id);
        return "Deporte Actualizado Correctamente";
        //return "Registro con id Deporte "+ String.valueOf(deporte.getIddeporte()) + "no Existente";
    }

    public Deporte consultarDeporte(String idDeporte){
        String[] id = {idDeporte};
        Cursor cursor = db.query("deporte", camposDeporte, "iddeporte = ?",id, null, null,null);
        if(cursor.moveToFirst()){
            Deporte deporte = new Deporte();
            deporte.setIddeporte(cursor.getInt(0));
            deporte.setNombredeporte(cursor.getString(1));
            deporte.setDescripciondeporte(cursor.getString(2));
            return deporte;

        }else{ return null;
        }
    }



    public String llenarBDPolideportivo(){

        final Integer[] VDiddeporte = {1,2,3,4,5,6};
        final String[] VDnombre = {"Futbol","Boxeo","Baloncesto","Natacion","Futbo Sala","Voleibol"};
        final String[] VDdescripcion = {"es un deporte de equipo jugado entre dos conjuntos de once jugadores cada uno y algunos árbitros","también llamado a veces boxeo inglés o boxeo irlandés, y coloquialmente conocido como box, es un deporte de combate en el que dos contrincantes luchan utilizando únicamente sus puños con guantes, golpeando a su adversario","es un deporte de equipo que se puede desarrollar tanto en pista cubierta como en descubierta, en el que dos conjuntos de cinco jugadores cada uno, intentan anotar puntos, también llamados canastas o dobles y/o triples introduciendo un balón en un aro colocado a 3,05 metros del suelo del que cuelga una red, lo que le da un aspecto de cesta o canasta","La natación es el movimiento y el desplazamiento a través del agua mediante el uso de las extremidades corporales y por lo general sin utilizar ningún instrumento o apoyo para avanzar","es un deporte colectivo de pelota practicado entre dos equipos de 5 jugadores cada uno, dentro de una cancha de suelo duro. Surgió inspirado en otros deportes como el fútbol, que es la base del juego; el waterpolo; el voleibol; el balonmano y el baloncesto","es un deporte donde dos equipos se enfrentan sobre un terreno de juego liso separados por una red central, tratando de pasar el balón por encima de la red hacia el suelo del campo contrario. El balón debe ser tocado o impulsado con golpes limpios, pero no puede ser parado, sujetado, retenido o acompañado"};

        final Integer[] VAidarea = {1,2,3,4,5,6};
        final Integer[] VAmaximoP = {100,200,300,400,500,600};
        final String[] VAnombre = {"Estadio","Ring","Cancha Techada","Piscina","Cancha Techada F","Cancha de Arena"};
        final String[] VAdescripcion = {"Estadio Martires","Ring de Boxeo","Cancha de Baloncesto","Piscina de 1000L","Duela","Cancha de Voleibol"};
        abrir();
        db.execSQL("DELETE FROM deporte");
        db.execSQL("DELETE FROM area");


        Deporte deporte = new Deporte();
        for(int i=0;i<6;i++){
            deporte.setIddeporte(VDiddeporte[i]);
            deporte.setNombredeporte(VDnombre[i]);
            deporte.setDescripciondeporte(VDdescripcion[i]);
            insertar(deporte);
        }
        Area area = new Area();
        for(int i=0;i<6;i++){
            area.setIdarea(VAidarea[i]);
            area.setMaximopersonas(VAmaximoP[i]);
            area.setNombrearea(VAnombre[i]);
            area.setDescripcionarea(VAdescripcion[i]);
            insertar(area);
        }
        cerrar();
        return "Guardo Correctamente";
    }

    public String llenarActividades(){


        //String[] idactividad = {"1","2","3","4"};
        String[] nombreActividad = {"Academica", "Deportiva","Cultura","Politica"};
        String[] descripcionActivida = {"actividades educativas","Actividades deportivas","Actividades recreativas","actividades de tipo politico tiene un costo"};

        abrir();
        db.execSQL("DELETE FROM ACTIVIDAD");

        Actividad actividad = new Actividad();
        for (int i=0; i<4;i++ ){
            actividad.setNombreActividad(nombreActividad[i]);
            actividad.setDescripcionActividad(descripcionActivida[i]);
            insertarActividad(actividad);
        }

        return "Guardado correctamente";
    }


    public String llenarTarifa(){
        //final int[] VTidtarifa = {1,2,3,4,5};
        final int[] VTcantpersonas = {100,200,300,400,500};
        final double[] VTtarifaunitaria = {1.50, 2.50,3.40,4.25,5};

        abrir();
        db.execSQL("DELETE from Tarifa");


        //tabla gerardo
        Tarifa tarifa = new Tarifa();
        for(int i=0;i<5;i++){
           // tarifa.setIdTarifa(VTidtarifa[i]);
            tarifa.setCantidadPersonas(VTcantpersonas[i]);
            tarifa.setTarifaUnitaria(VTtarifaunitaria[i]);
            InsertarTarifa(tarifa);
        }

        cerrar();
        return "Guardo Correctamente";
    }

    public String InsertarTarifa(Tarifa tarifa){

        String regInsertardo="tarifa Insertada N°:";
        long contador;

        ContentValues tari= new ContentValues();
        //tari.put("idtarifa", tarifa.getIdTarifa());
        tari.put("cantpersonas",tarifa.getCantidadPersonas());
        tari.put("tarifaunitaria",tarifa.getTarifaUnitaria());
        contador=db.insert("tarifa",null,tari);

        regInsertardo=regInsertardo+contador;

        return regInsertardo;

    }

    public String actualizarTarifa(Tarifa tarifa){

        if(verificarIntegridadG(tarifa,1)){
            String[] id = {String.valueOf(tarifa.getCantidadPersonas())};
            ContentValues cv = new ContentValues();
            cv.put("tarifaunitaria", tarifa.getTarifaUnitaria());
            db.update("tarifa", cv, "cantpersonas = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con Cantidad de Personas "+tarifa.getCantidadPersonas() +" no existe";
        }
    }

    public Tarifa consultarTarifa(int cantpersona) {

        String[] id = {String.valueOf(cantpersona)};
        Cursor cursor = db.query("tarifa", camposTarifa, "cantpersonas = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Tarifa tarifa=new Tarifa();
            tarifa.setIdTarifa(cursor.getInt(0));
            tarifa.setCantidadPersonas(cursor.getInt(1));
            tarifa.setTarifaUnitaria(cursor.getDouble(2));
            return tarifa;
        }else{
            return null;
        }

    }

    public String eliminar(Tarifa tarifa){return null;}

    private boolean verificarIntegridadG(Object dato, int relacion) throws SQLException{

        switch (relacion){
            case 1:
            {//verificar si existe rango de personas cantpersonas
                Tarifa tarifa2= (Tarifa)dato;
                String[] id= {String.valueOf(tarifa2.getCantidadPersonas())};
                abrir();
                Cursor c2= db.query("tarifa", null, "cantpersonas = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //tarifa encontrada
                    return true;
                }else{return false;}
            }
            /*case 2:
            {
                Tarifa tarifa= (Tarifa)dato;
                Cursor c=db.query(true,"tarifaUnitaria", new String[]{""})

            }*/
            // Pro si necesito mas xD
            default:
                return false;
        }

    }
}



