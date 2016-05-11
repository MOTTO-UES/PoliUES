package poliues.eisi.fia.edu.sv.poliues;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jonathan on 21/4/2016.
 */
public class ControlBDPoliUES {
    private static final  String[] camposreserva = new  String[] {"idreserva","idfacultad","fechaingreso","numeropersonas","motivo","descripcionreserva"};
    private static final  String[] camposdetallereserva = new String[] { "iddetallereserva","idarea","idreserva"};
    private static final  String[] camposarea = new String[] {"idarea","maximopersonas","foto","nombrearea","descripcionarea"};
    private static final  String[] camposhorario = new String[] {"idhorario","idreserva","fechareserva"," horarioinicio"," horariofin"};
    private static final  String[] camposfacultad = new String[] {"idfacultad","nombrefacultad"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDPoliUES(Context ctx) {
        this.context = ctx;
        DBHelper =new DatabaseHelper(context);

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
                /*Creamos nuestros tablas*/

                db.execSQL("create table reserva (idreserva  INTEGER PRIMARY KEY AUTOINCREMENT,idfacultad INTEGER NOT NULL,fechaingreso VARCHAR(50) NOT NULL,numeropersonas INTEGER NOT NULL, motivo VARCHAR(25) NOT NULL,descripcionreserva   VARCHAR(50) NOT NULL);");
                db.execSQL("create table detallereserva ( iddetallereserva INTEGER PRIMARY KEY AUTOINCREMENT,idarea INTEGER NOT NULL, idreserva INTEGER NOT NULL);");
                db.execSQL("create table horario ( idhorario INTEGER PRIMARY KEY AUTOINCREMENT,idreserva INTEGER,fechareserva VARCHAR(50) NOT NULL, horarioinicio VARCHAR(50) NOT NULL, horariofin VARCHAR(50)  NOT NULL);");
                db.execSQL("create table area (idarea INTEGER PRIMARY KEY AUTOINCREMENT,maximopersonas INTEGER NOT NULL,foto  VARCHAR(50) NOT NULL,nombrearea  VARCHAR(30) NOT NULL,descripcionarea VARCHAR(50) NOT NULL);");
                db.execSQL("create table facultad(idfacultad INTEGER PRIMARY KEY AUTOINCREMENT ,nombrefacultad VARCHAR(40));");
                /*Creamos nuestros triggers*/

                db.execSQL("CREATE TRIGGER exiten_id_detallereserva" +
                        "BEFORE INSERT ON detallereserva" +
                        "FOR EACH ROW" +
                        "BEGIN" +
                        "SELECT CASE" +
                        /*verifica que exista el area para detallereserva*/
                        "WHEN((SELECT idarea from area WHERE idarea = NEW.idarea) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe el area')" +
                        /*verifica que exista la reserva para detallereserva*/
                        "WHEN((SELECT idreserva from reserva WHERE idreserva = NEW.idreserva) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe la reserva')" +
                        "END;" +
                        "END;");
                /*verifica que exista el reserva para horario*/
                db.execSQL("CREATE TRIGGER exite_idreserva_en_reserva_horario" +
                        "BEFORE INSERT ON horario" +
                        "FOR EACH ROW" +
                        "BEGIN" +
                        "SELECT CASE" +
                        "WHEN((SELECT idreserva from reserva WHERE idreserva = NEW.idreserva) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe la reserva')" +
                        "END;" +
                        "END;");
                /*verifica que exista la faculta para reserva*/
                db.execSQL("CREATE TRIGGER exite_idfacultad_en_facultad_reserva" +
                        "BEFORE INSERT ON reserva" +
                        "FOR EACH ROW" +
                        "BEGIN" +
                        "SELECT CASE" +
                        "WHEN((SELECT idfacultad from facultad WHERE idfacultad = NEW.idfacultad) IS NULL)" +
                        "THEN RAISE(ABORT, 'no existe la facultad')" +
                        "END;" +
                        "END;");





                System.out.println("se crearon todas las tablas");




            }catch(SQLException e){

                System.out.println("ubo un error en la creacion de tablas");

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
        area1.put("maximopersonas", area.getMaximopersona());
        area1.put("foto", area.getFoto());
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
            return "Registro Actualizado Correctamente";
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
            return "Registro Actualizado Correctamente";
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
            db.update("detallereserva", cv, "idreserva = ?", id);
            return "Registro Actualizado Correctamente";
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
        Cursor cursor = db.query("area", camposarea, "idarea = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Area area = new Area();
            area.setIdarea(Integer.parseInt(cursor.getString(0)));
            area.setMaximopersona(Integer.parseInt(cursor.getString(1)));
            area.setFoto(cursor.getString(2));
            area.setNombrearea(cursor.getString(3));
            area.setDescripcionarea(cursor.getString(4));

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
        final String[] VAfoto = {"vfdvfd","4vfdvdf","4vfvfdvdf"};
        final String[] VAnombrearea = {"Cancha Engramada","Cancha Duela","Cancha de Cemento"};
        final String[] VAdescripcionarea = {"La mejor Cancha","Multi Usos","Multi Uso 2"};

        final String[] VDRidetalle = {"1","2","3"};
        final String[] VDRidarea = {"1","2","3"};
        final String[] VDRidreserva = {"1","2","3"};
        ;


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
        db.execSQL("DELETE FROM area");
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

        Area area = new Area();
        for(int i=0;i<3;i++){
            area.setIdarea(Integer.parseInt(VAidarea[i]));
            area.setMaximopersona(Integer.parseInt(VAmaximopersona[i]));
            area.setFoto(VAfoto[i]);
            area.setNombrearea(VAnombrearea[i]);
            area.setDescripcionarea(VAdescripcionarea[i]);
            insertar(area);
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

}
