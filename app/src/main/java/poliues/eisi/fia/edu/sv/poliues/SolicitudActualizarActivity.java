package poliues.eisi.fia.edu.sv.poliues;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SolicitudActualizarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ControlBDPoliUES helper;
    EditText actividadET;
    EditText areaET;
    EditText motivoET;
    TextView FI;
    TextView FF;
    EditText CT;
    String actividadSeleccionada;
    Spinner spinnerActividad;
    Spinner spinnerAreas;
    String AreaSeleccionada;
    String FechaInicio;
    String FechaFin;

    Solicitud solicitud = null;
    DetalleSolicitud DS = null;

    private TextView mDateDisplayInicio;
    private TextView mDateDisplayFin;
    private Button mPickDateInicio;
    private Button mPickDateFin;
    private int mYearFin;
    private int mMonthFin;
    private int mDayFin;
    private int mYearInicio;
    private int mMonthInicio;
    private int mDayInicio;
    static final int DATE_DIALOG_ID_Inicio = 0;
    static final int DATE_DIALOG_ID_Fin = 1;

    Solicitante soli=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_solicitud);

        Bundle bundle = getIntent().getExtras();

        soli = new Solicitante();

        soli.setIdSolicitante(getIntent().getExtras().getInt("IDUSUARIO"));

        System.out.println("usuario: "+soli.getIdSolicitante());

        helper = new ControlBDPoliUES(this);

        //EXTRAER CAMPOS

       //actividadET = (EditText) findViewById(R.id.editActividad);
        motivoET= (EditText) findViewById(R.id.editMotivoSolicitud);
        FI= (TextView) findViewById(R.id.dateDisplayInicio);
        FF= (TextView) findViewById(R.id.dateDisplayFin);


        //LLENAR ACTIVIDADES
        spinnerActividad = (Spinner) findViewById(R.id.SpinnerActividad);


        ArrayAdapter<CharSequence> adapterAct = ArrayAdapter.createFromResource(this, R.array.Actividades, android.R.layout.simple_spinner_item);


        adapterAct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActividad.setAdapter(adapterAct);
        spinnerActividad.setOnItemSelectedListener(this);


        //CREAR CALENDARIOS
        mDateDisplayInicio = (TextView) findViewById(R.id.dateDisplayInicio);
        mDateDisplayFin = (TextView) findViewById(R.id.dateDisplayFin);

        mPickDateInicio = (Button) findViewById(R.id.pickDateInicio);
        mPickDateFin = (Button) findViewById(R.id.pickDateFin);

        mPickDateInicio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID_Inicio);
            }
        });

        mPickDateFin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID_Fin);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYearFin = c.get(Calendar.YEAR);
        mMonthFin = c.get(Calendar.MONTH);
        mDayFin = c.get(Calendar.DAY_OF_MONTH);

        final Calendar b = Calendar.getInstance();
        mYearInicio = b.get(Calendar.YEAR);
        mMonthInicio = b.get(Calendar.MONTH);
        mDayInicio = b.get(Calendar.DAY_OF_MONTH);

        updateDisplayFin();
        updateDisplayInicio();


       mostrarDatos(bundle.getString("motivo"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opcionessolicitante,menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.consultarSolicitud:
                intent = new Intent(this,SolicitudConsultarActivity.class);
                intent.putExtra("IDUSUARIO",soli.getIdSolicitante());
                startActivity(intent);
                break;
            case R.id.actInsertar:
                intent = new Intent(this,SolicitudInsertarActivity.class);
                intent.putExtra("IDUSUARIO",soli.getIdSolicitante());
                startActivity(intent);
                break;
            case R.id.actPrincipalUsuario:
                intent = new Intent(this,PrincipalUsuario.class);
                intent.putExtra("EnvioSolicitanteID",soli.getIdSolicitante());
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.actividadSeleccionada = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void updateDisplayInicio(){
        mDateDisplayInicio.setText(
                new StringBuilder()

                        .append(mDayInicio).append("-")
                        .append(mMonthInicio + 1).append("-")
                        .append(mYearInicio).append("")
        );

        this.FechaInicio = mDayInicio+"-"+(mMonthInicio+1)+"-"+mYearInicio;
        System.out.println(FechaInicio);

    }

    private DatePickerDialog.OnDateSetListener mDateListenerInicio = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int mes, int dia){
            mYearInicio = year;
            mMonthInicio = mes;
            mDayInicio = dia;
            updateDisplayInicio();
        }
    };


    private void updateDisplayFin(){
        mDateDisplayFin.setText(
                new StringBuilder()

                        .append(mDayFin).append("-")
                        .append(mMonthFin + 1).append("-")
                        .append(mYearFin).append("")
        );

        this.FechaFin = mDayFin+"-"+(mMonthFin+1)+"-"+mYearFin;
        System.out.println(FechaFin);
    }

    private DatePickerDialog.OnDateSetListener mDateListenerFin = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int mes, int dia){
            mYearFin = year;
            mMonthFin = mes;
            mDayFin = dia;
            updateDisplayFin();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID_Inicio:
                return new DatePickerDialog(this,mDateListenerInicio,mYearInicio,mMonthInicio,mDayInicio);
            case DATE_DIALOG_ID_Fin:
                return new DatePickerDialog(this,mDateListenerFin,mYearFin,mMonthFin,mDayFin);

        }

        return null;
    }

    public void mostrarDatos(String motivo){

        helper = new ControlBDPoliUES(this);
        helper.leer();
        Cursor cursor = helper.consultarSolicitud();
        //helper.cerrar();

        Cursor cursor2 = helper.consultarDetalleSolicitud();

        solicitud = helper.buscarSolicitud(cursor,motivo);

        DS = helper.buscarDetalleSolicitud(cursor2,solicitud.getIdSolicitud());


        if(solicitud == null && DS == null){
            Toast.makeText(this,"no encontrado", Toast.LENGTH_LONG).show();
        }

        else{
            if (solicitud.getEstadoSolicitud().equals("aprobado")){
                Toast.makeText(this,"YA FUE APROBADO NO SE PUEDE MODIFICAR", Toast.LENGTH_LONG).show();
                Intent o = new Intent(this, verSolicitudActivity.class);
                o.putExtra("IDUSUARIO",soli.getIdSolicitante());
                o.putExtra("motivo",motivo);
                startActivity(o);
            }
            else {

                motivoET.setText(solicitud.getMotivoSolicitud());
                FI.setText(DS.getFechaInicio());
                FF.setText(DS.getFechaFinal());

            }


        }
    }


    public void ActualizarSolicitudYDS(View v){

        String actividad = this.actividadSeleccionada;
        String motivo = motivoET.getText().toString();

        String regInsertados;

        solicitud.setMotivoSolicitud(motivo);
        solicitud.setActividad(2);
        solicitud.setTarifa(2);

        DS.setCobroTotal(80);
        DS.setArea(2);
        DS.setFechaInicio(FI.getText().toString());
        DS.setFechaFinal(FF.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(solicitud);
        String estado2 = helper.actualizar(DS);
        helper.cerrar();
        Toast.makeText(this, estado + estado2, Toast.LENGTH_SHORT).show();

        Intent o = new Intent(this, verSolicitudActivity.class);
        o.putExtra("IDUSUARIO",soli.getIdSolicitante());
        o.putExtra("motivo",motivo);
        startActivity(o);

    }
}
