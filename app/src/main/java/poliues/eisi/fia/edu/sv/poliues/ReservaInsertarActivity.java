package poliues.eisi.fia.edu.sv.poliues;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReservaInsertarActivity extends Activity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_insertar);

        //obtain the param of Intent of last activity
        isUpdate = getIntent().getBooleanExtra("isEdit", false);
        realId = getIntent().getIntExtra("realId", 0);
        

        dbhelper = new ControlBDPoliUES(this);
        dbhelper.abrir();

        setUIComponents();
        addItemsOnSpinnerFacultades();
        addItemsOnSpinnerArea();

        //if is update charge the data, in the form
        if(isUpdate)
        {
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
        editmotivo = (EditText) findViewById(R.id.editmotivo);
        editdescripcionreserva = (EditText) findViewById(R.id.editdescripcionreserva);
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
                                                    if ((realFacultadId == 0) && ( Integer.parseInt(editnumeropersonas.getText().toString())== 0)&& (editmotivo.getText().toString()==" ")&&(editdescripcionreserva.getText().toString()==" ")&&(realAreaId==0)&&(editfechareserva.getText().toString()==" ")&&(edithorainicio.getText().toString()==" ")&&(edithorafin.getText().toString()==" ")){
                                                        String error ="Hay Datos Vacios";
                                                        mensajes(error);
                                                    }else{
                                                        dbhelper.abrir();
                                                        Reserva reservaguardar = new Reserva();
                                                        reservaguardar.setIdfacultad(realFacultadId);
                                                        reservaguardar.setNumeropersonas(Integer.parseInt(editnumeropersonas.getText().toString()));
                                                        reservaguardar.setMotivo(editmotivo.getText().toString());
                                                        reservaguardar.setDescripcionreserva(editdescripcionreserva.getText().toString());
                                                        reservaguardar.setFechaingreso(fechaActual());
                                                        dbhelper.insertar(reservaguardar);
                                                        Cursor getReservas = dbhelper.todaslasreservas();

                                                        if(getReservas!=null)
                                                        {
                                                            getReservas.moveToLast();
                                                            idReserva = getReservas.getInt(getReservas.getColumnIndex("idreserva"));
                                                        }
                                                        DetalleReserva detallereservaguardar = new DetalleReserva();
                                                        detallereservaguardar.setIdarea(realAreaId);
                                                        detallereservaguardar.setIdreserva(idReserva);
                                                        dbhelper.insertar(detallereservaguardar);
                                                        Horario horarioguardar = new Horario();
                                                        horarioguardar.setIdreserva(idReserva);
                                                        horarioguardar.setFechareserva(editfechareserva.getText().toString());
                                                        horarioguardar.setHorarioinicio(edithorainicio.getText().toString());
                                                        horarioguardar.setHorariofin(edithorafin.getText().toString());
                                                        dbhelper.insertar(horarioguardar);
                                                        dbhelper.cerrar();
                                                    }
                                                }//if is update
                                                else {
                                                    // realId
                                                    if ((realFacultadId == 0) && ( Integer.parseInt(editnumeropersonas.getText().toString())== 0)&& (editmotivo.getText().toString()==" ")&&(editdescripcionreserva.getText().toString()==" ")&&(realAreaId==0)&&(editfechareserva.getText().toString()==" ")&&(edithorainicio.getText().toString()==" ")&&(edithorafin.getText().toString()==" ")){
                                                        String error ="Hay Datos Vacios";
                                                        mensajes(error);
                                                    }else{
                                                    dbhelper.abrir();
                                                    Reserva reservaactualizar = new Reserva();
                                                    reservaactualizar.setIdreserva(realId);
                                                    reservaactualizar.setIdfacultad(realFacultadId);
                                                    reservaactualizar.setNumeropersonas(Integer.parseInt(editnumeropersonas.getText().toString()));
                                                    reservaactualizar.setMotivo(editmotivo.getText().toString());
                                                    reservaactualizar.setDescripcionreserva(editdescripcionreserva.getText().toString());
                                                    reservaactualizar.setFechaingreso(fechaActual());
                                                    dbhelper.actualizar(reservaactualizar);
                                                    DetalleReserva detallereservaactualizar = new DetalleReserva();
                                                    detallereservaactualizar.setIdarea(realAreaId);
                                                    detallereservaactualizar.setIdreserva(realId);
                                                    dbhelper.actualizar(detallereservaactualizar);
                                                    Horario horarioactualizar = new Horario();
                                                    horarioactualizar.setIdreserva(realId);
                                                    horarioactualizar.setFechareserva(editfechareserva.getText().toString());
                                                    horarioactualizar.setHorarioinicio(edithorainicio.getText().toString());
                                                    horarioactualizar.setHorariofin(edithorafin.getText().toString());
                                                    String m = dbhelper.actualizar(horarioactualizar);
                                                    mensajes(m);
                                                    dbhelper.cerrar();
                                                    }
                                                }

                                                //go to the list activity
                                                Intent i = new Intent(ReservaInsertarActivity.this, ListarReservaActivity.class);
                                                startActivity(i);
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

                return new TimePickerDialog(this, timePickerListener1, hour, min, false);
            }
            case TIME2_DIALOG_ID: {
                //Hora Fin
                return new TimePickerDialog(this, timePickerListener2, hour, min, false);
            }
        }
        return null;
    };

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            editfechareserva.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
        }
    };


    private TimePickerDialog.OnTimeSetListener timePickerListener1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }
            edithorainicio.setText(hour + " : " + minute + " " + am_pm);
        }
    };

     private TimePickerDialog.OnTimeSetListener timePickerListener2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }
            edithorafin.setText(hour + " : " + minute + " " + am_pm);
        }
    };

    public String fechaActual(){
        return new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    public void mensajes(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


    }

}

