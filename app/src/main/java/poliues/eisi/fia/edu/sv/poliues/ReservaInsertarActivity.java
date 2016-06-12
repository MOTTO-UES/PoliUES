package poliues.eisi.fia.edu.sv.poliues;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.annotation.SuppressLint;

import org.json.JSONObject;

public class ReservaInsertarActivity extends AppCompatActivity implements View.OnClickListener {

    ControlBDPoliUES dbhelper;
    Spinner spfacultades;
    Spinner spareas;
    EditText editnumeropersonas;
    EditText editmotivo;
    EditText editdescripcionreserva;
    EditText editfechareserva;
    Button btnGuardar;


    ImageButton ib1,ib2,ib3;
    Calendar cal1,cal2,cal3;
    int day;
    int month;
    int year;


    int hour;
    int min;

    EditText edithorainicio;
    EditText edithorafin;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME1_DIALOG_ID = 1;
    static final int TIME2_DIALOG_ID = 2;

    private static boolean isUpdate;
    private static int realId;
    private static int realFacultadId=0;
    private static int realAreaId=0;

    int idReserva=0;
    Bundle admi;
    Conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        conn=new Conexion();

        //obtain the param of Intent of last activity
        isUpdate = getIntent().getBooleanExtra("isEdit", false);
        realId = getIntent().getIntExtra("realId", 0);
        admi = getIntent().getExtras();

        dbhelper = new ControlBDPoliUES(this);
        dbhelper.abrir();

        setUIComponents();
        addItemsOnSpinnerFacultades();
        addItemsOnSpinnerArea();

        //if is update charge the data, in the form
        if(isUpdate)
        {
            mensajes("se activo Actulizar");
            dbhelper.abrir();
            Reserva getReserva = dbhelper.consultarReserva(Integer.toString(realId));
            DetalleReserva getDReserva = dbhelper.consultarDetalleReserva(Integer.toString(realId));
            Horario getHorario = dbhelper.consultarHorario(Integer.toString(realId));
            dbhelper.cerrar();

            if (getReserva != null || getDReserva !=null || getHorario !=null ) {
                //TODO spinner to update
                spfacultades.setSelection(getReserva.getIdfacultad()-1);
                editnumeropersonas.setText(String.valueOf(getReserva.getNumeropersonas()));
                editmotivo.setText(getReserva.getMotivo());
                editdescripcionreserva.setText(getReserva.getDescripcionreserva());
                spareas.setSelection(getDReserva.getIdarea()-1);
                editfechareserva.setText(getHorario.getFechareserva());
                edithorainicio.setText(getHorario.getHorarioinicio());
                edithorafin.setText(getHorario.getHorariofin());

            }
        }
        dbhelper.cerrar();


    }




    private void setUIComponents(){
        spfacultades = (Spinner) findViewById(R.id.spfacultades);
        editnumeropersonas = (EditText) findViewById(R.id.editnumeropersonas);
        editnumeropersonas.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(TextUtils.isEmpty(editnumeropersonas.getText().toString())){
                    editnumeropersonas.setError("El campo esta Vacio");

                }else if(Integer.parseInt(editnumeropersonas.getText().toString())<=0){
                        editnumeropersonas.setError("Debe de ingresar un numero entero positivo");
                    }else{
                    editnumeropersonas.setError(null);
                }
            }



        });


        editmotivo = (EditText) findViewById(R.id.editmotivo);
        editmotivo.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(TextUtils.isEmpty(editmotivo.getText().toString())){
                    editmotivo.setError("El campo motivo esta Vacio");

                }else{
                    editmotivo.setError(null);
                }
            }
        });


        editdescripcionreserva = (EditText) findViewById(R.id.editdescripcionreserva);
        editdescripcionreserva.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(TextUtils.isEmpty(editdescripcionreserva.getText().toString())){
                    editdescripcionreserva.setError("El campo descripcion esta Vacio");

                }else{
                    editdescripcionreserva.setError(null);
                }
            }
        });

        spareas = (Spinner) findViewById(R.id.spareas);
        //Fecha Reserva
        ib1 = (ImageButton) findViewById(R.id.imagefecha);
        cal1 = Calendar.getInstance();
        day = cal1.get(Calendar.DAY_OF_MONTH);
        month = cal1.get(Calendar.MONTH);
        year = cal1.get(Calendar.YEAR);
        editfechareserva = (EditText) findViewById(R.id.editfechareserva);
        ib1.setOnClickListener(this);

        //Hora Reserva
        //Hora Inicio
        ib2 = (ImageButton) findViewById(R.id.imagehorainicio);
        cal2 = Calendar.getInstance();
        hour = cal2.get(Calendar.HOUR_OF_DAY);
        min = cal2.get(Calendar.MINUTE);
        edithorainicio = (EditText) findViewById(R.id.edithorarioinicio);
        ib2.setOnClickListener(this);
        //Hora Fin
        ib3 = (ImageButton) findViewById(R.id.imagehorafin);
        cal3 = Calendar.getInstance();
        hour = cal3.get(Calendar.HOUR_OF_DAY);
        min = cal3.get(Calendar.MINUTE);
        edithorafin = (EditText) findViewById(R.id.edithorariofin);
        ib3.setOnClickListener(this);

        btnGuardar = (Button)findViewById(R.id.buttonguardar);

        //set of button click
        btnGuardar.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                //if is create
                                                if (!isUpdate) {
                                                    if(!(TextUtils.isEmpty(editmotivo.getText().toString()) || TextUtils.isEmpty(editnumeropersonas.getText().toString()) || TextUtils.isEmpty(editdescripcionreserva.getText().toString()) || TextUtils.isEmpty(editdescripcionreserva.getText().toString()) || TextUtils.isEmpty(editfechareserva.getText().toString()) || TextUtils.isEmpty(edithorainicio.getText().toString()) || TextUtils.isEmpty(edithorafin.getText().toString()))){
                                                        Date fechahoy = null;
                                                        Date fechainput = null;
                                                        String fecha = editfechareserva.getText().toString();
                                                        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                                                        String fechaactual = fechaActual();
                                                        try{
                                                            fechahoy=dateformat.parse(fechaactual);
                                                            fechainput=dateformat.parse(fecha);

                                                        }catch (ParseException e){
                                                            e.printStackTrace();
                                                        }

                                                        if (fechainput.compareTo(fechahoy)<=0)
                                                        {
                                                            mensajes("Error No se guardo porque la fecha ingresada es menor o igual a la actual");
                                                        }else{
                                                            Date horainit = null;
                                                            Date horaend = null;

                                                            DateFormat dateformath = new SimpleDateFormat("HH:mm");
                                                            String horacapturada = edithorafin.getText().toString();
                                                            try{
                                                                horainit=dateformath.parse(edithorainicio.getText().toString());
                                                                horaend=dateformath.parse(horacapturada);

                                                            }catch (ParseException e){
                                                                e.printStackTrace();
                                                            }

                                                            if (horaend.compareTo(horainit)<=0)
                                                            {
                                                                mensajes("Error no se realizo la accion porque La hora fin debe ser mayor que la de inicio");
                                                            }else{
                                                                String me;
                                                                String url = null;



                                                                url=conn.getURLLocal()+"/ws_reserva_insertar.php"+ "?idfacultad=" + realFacultadId + "&fechaingreso="
                                                                        + fechaActual() + "&numeropersonas=" + Integer.parseInt(editnumeropersonas.getText().toString()) + "&motivo=" + editmotivo.getText().toString()+ "&descripcionreserva=" + editdescripcionreserva.getText().toString();
                                                                ControladorServicio.insertarReservaPHP(url, ReservaInsertarActivity.this);

                                                                url="";

                                                                url=conn.getURLLocal()+"/ws_reservaid_query.php";

                                                                String idreservaJSON = ControladorServicio.obtenerRespuestaPeticion(url, ReservaInsertarActivity.this);
                                                                realId=ControladorServicio.obtenerIdReservaJSON(idreservaJSON,ReservaInsertarActivity.this);

                                                                url=conn.getURLLocal()+"/ws_detallereserva_insertar.php"+ "?idreserva=" + realId + "&idarea="
                                                                        + realAreaId;
                                                                ControladorServicio.insertarDetalleReservaPHP(url, ReservaInsertarActivity.this);



                                                                url=conn.getURLLocal()+"/ws_horario_insertar.php"+ "?idreserva=" + realId + "&fechareserva="
                                                                        + editfechareserva.getText().toString() + "&horarioinicio=" + edithorainicio.getText().toString() + "&horariofin=" + edithorafin.getText().toString();
                                                                ControladorServicio.insertarHorarioPHP(url, ReservaInsertarActivity.this);


                                                                //go to the list activity
                                                                Intent i = new Intent(ReservaInsertarActivity.this, ListarReservaActivity.class);
                                                                i.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                                                                i.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                                                                i.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                                                                i.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                                                                i.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                                                                startActivity(i);
                                                            }

                                                        }
                                                    }else{
                                                        mensajes("No se pudo realizar la accion por que Tiene campos vacios");
                                                    }
                                                }//if is update
                                                else {
                                                    if(!(TextUtils.isEmpty(editmotivo.getText().toString()) || TextUtils.isEmpty(editnumeropersonas.getText().toString()) || TextUtils.isEmpty(editdescripcionreserva.getText().toString()) || TextUtils.isEmpty(editdescripcionreserva.getText().toString()) || TextUtils.isEmpty(editfechareserva.getText().toString()) || TextUtils.isEmpty(edithorainicio.getText().toString()) || TextUtils.isEmpty(edithorafin.getText().toString()))){
                                                        Date fechahoy = null;
                                                        Date fechainput = null;
                                                        String fecha = editfechareserva.getText().toString();
                                                        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                                                        String fechaactual = fechaActual();
                                                        try{
                                                            fechahoy=dateformat.parse(fechaactual);
                                                            fechainput=dateformat.parse(fecha);

                                                        }catch (ParseException e){
                                                            e.printStackTrace();
                                                        }

                                                        if (fechainput.compareTo(fechahoy)<=0)
                                                        {
                                                            mensajes("Error No se Actualizo porque la fecha ingresada es menor o igual a la actual");
                                                        }else{
                                                            Date horainit = null;
                                                            Date horaend = null;

                                                            DateFormat dateformath = new SimpleDateFormat("HH:mm");
                                                            String horacapturada = edithorafin.getText().toString();
                                                            try{
                                                                horainit=dateformath.parse(edithorainicio.getText().toString());
                                                                horaend=dateformath.parse(horacapturada);

                                                            }catch (ParseException e){
                                                                e.printStackTrace();
                                                            }

                                                            if (horaend.compareTo(horainit)<=0)
                                                            {
                                                                mensajes("Error No se Actualizo porque La hora fin debe ser mayor que la de inicio");
                                                            }else{
                                                                String m;
                                                                // realId

                                                                dbhelper.abrir();
                                                                Reserva reservaactualizar = new Reserva();
                                                                reservaactualizar.setIdreserva(realId);
                                                                reservaactualizar.setIdfacultad(realFacultadId);
                                                                reservaactualizar.setNumeropersonas(Integer.parseInt(editnumeropersonas.getText().toString()));
                                                                reservaactualizar.setMotivo(editmotivo.getText().toString());
                                                                reservaactualizar.setDescripcionreserva(editdescripcionreserva.getText().toString());
                                                                reservaactualizar.setFechaingreso(fechaActual());
                                                                m = dbhelper.actualizar(reservaactualizar);
                                                                    mensajes(m);
                                                                    m=" ";
                                                                DetalleReserva detallereservaactualizar = new DetalleReserva();
                                                                detallereservaactualizar.setIdarea(realAreaId);
                                                                detallereservaactualizar.setIdreserva(realId);
                                                                m= dbhelper.actualizar(detallereservaactualizar);
                                                                    mensajes(m);
                                                                    m=" ";
                                                                Horario horarioactualizar = new Horario();
                                                                horarioactualizar.setIdreserva(realId);
                                                                horarioactualizar.setFechareserva(editfechareserva.getText().toString());
                                                                horarioactualizar.setHorarioinicio(edithorainicio.getText().toString());
                                                                horarioactualizar.setHorariofin(edithorafin.getText().toString());
                                                                m = dbhelper.actualizar(horarioactualizar);
                                                                mensajes(m);
                                                                dbhelper.cerrar();
                                                                //go to the list activity
                                                                Intent i = new Intent(ReservaInsertarActivity.this, ListarReservaActivity.class);
                                                                i.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                                                                i.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                                                                i.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                                                                i.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                                                                i.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));

                                                                startActivity(i);
                                                            }
                                                        }
                                                    }else{
                                                        mensajes("No se pudo realizar la accion por que Tiene campos vacios");
                                                    }

                                                }
                                            }
                                        }
        );


        spfacultades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                realFacultadId = obtainFacultadSelectedId(arg2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //optionally do something here
            }
        });


        spareas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                realAreaId= obtainAreaSelectedId(arg2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //optionally do something here
            }
        });


    }

   private int obtainFacultadSelectedId(int position){
        int toStop=0;
        int returnId=0;
        dbhelper.abrir();
        Cursor getFacultades = dbhelper.todaslasfacultades();
        if (getFacultades != null ) {
            //Move cursor to first row
            if  (getFacultades.moveToFirst()) {
                do {
                    if(position==toStop) {
                        //Get version from Cursor
                        returnId = getFacultades.getInt(getFacultades.getColumnIndex("idfacultad"));
                    }
                    toStop++;
                }while (getFacultades.moveToNext()); //Move to next row
            }
        }
        dbhelper.cerrar();
        return returnId;
    }
    private int obtainAreaSelectedId(int position){
        int toStop=0;
        int returnId=0;
        dbhelper.abrir();
        Cursor getArea = dbhelper.todaslasareas();
        if (getArea != null ) {
            //Move cursor to first row
            if  (getArea.moveToFirst()) {
                do {
                    if(position==toStop) {
                        //Get version from Cursor
                        returnId = getArea.getInt(getArea.getColumnIndex("idarea"));
                    }
                    toStop++;
                }while (getArea.moveToNext()); //Move to next row
            }
        }
        dbhelper.cerrar();
        return returnId;
    }

    private void addItemsOnSpinnerFacultades() {
        ArrayList<String> facultadResults = new ArrayList<String>();
        dbhelper.abrir();
        //obtain the cursor of get all
        Cursor getFacultades = dbhelper.todaslasfacultades();

        if (getFacultades != null ) {
            //Move cursor to first row
            if  (getFacultades.moveToFirst()) {
                do {
                    //Get version from Cursor
                    String firstName = getFacultades.getString(getFacultades.getColumnIndex("nombrefacultad"));
                    //Add the version to Arraylist 'results'
                    facultadResults.add(firstName);
                }while (getFacultades.moveToNext()); //Move to next row
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, facultadResults);
        spfacultades.setAdapter(adapter);
        dbhelper.cerrar();
    }

    private void addItemsOnSpinnerArea() {
        ArrayList<String> areaResults = new ArrayList<String>();
        dbhelper.abrir();

        //obtain the cursor of get all
        Cursor getAreas = dbhelper.todaslasareas();

        if (getAreas != null ) {
            //Move cursor to first row
            if  (getAreas.moveToFirst()) {
                do {
                    //Get version from Cursor
                    String firstName = getAreas.getString(getAreas.getColumnIndex("nombrearea"));
                    //Add the version to Arraylist 'results'
                    areaResults.add(firstName);
                }while (getAreas.moveToNext()); //Move to next row
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, areaResults);
        spareas.setAdapter(adapter);
        dbhelper.cerrar();
    }

    //fecha

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagefecha:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.imagehorainicio:
                showDialog(TIME1_DIALOG_ID);
                break;
            case R.id.imagehorafin:
                showDialog(TIME2_DIALOG_ID);
                break;
            default:
                break;

        }
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DATE_DIALOG_ID: {
                //Fecha Reserva
                return new DatePickerDialog(this, datePickerListener, year, month, day);
            }
            case TIME1_DIALOG_ID: {
                //Hora Inicio

                return new TimePickerDialog(this, timePickerListener1, hour, min, true);
            }
            case TIME2_DIALOG_ID: {
                //Hora Fin
                return new TimePickerDialog(this, timePickerListener2, hour, min,true);
            }
        }
        return null;
    };

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            Date fechaactual = null;
            Date fechaingresada = null;
            String fecha = selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear;
            DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
            String fechadehoy = fechaActual();
            try{
                fechaactual=dateformat.parse(fechadehoy);
                fechaingresada=dateformat.parse(fecha);

            }catch (ParseException e){
                e.printStackTrace();
            }

            if (fechaingresada.compareTo(fechaactual)<=0)
            {
                editfechareserva.setText(selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear);
                editfechareserva.setError("Error la fecha ingresada es menor o igual que la actual");
            }else{
                editfechareserva.setError(null);
            editfechareserva.setText(selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear);}
        }
    };


    private TimePickerDialog.OnTimeSetListener timePickerListener1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            /*int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }*/
            edithorainicio.setText(hourOfDay + ":" + minute);
        }
    };

     private TimePickerDialog.OnTimeSetListener timePickerListener2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            /*int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }*/

            Date horainicio = null;
            Date horafin = null;

            DateFormat dateformat = new SimpleDateFormat("HH:mm");
            String horacapturada = hourOfDay + ":" + minute;
            try{
                horainicio=dateformat.parse(edithorainicio.getText().toString());
                horafin=dateformat.parse(horacapturada);

            }catch (ParseException e){
                e.printStackTrace();
            }

            if (horafin.compareTo(horainicio)<=0)
            {
                edithorafin.setText(hourOfDay + ":" + minute);
                edithorafin.setError("La hora fin debe ser mayor que la de inicio");
            }else{
                edithorafin.setText(hourOfDay + ":" + minute);
                edithorafin.setError(null);
            }
        }
    };



    public String fechaActual(){
        return new SimpleDateFormat( "dd-MM-yyyy", java.util.Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    public void mensajes(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


    }

}

