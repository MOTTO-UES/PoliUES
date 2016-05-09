package poliues.eisi.fia.edu.sv.poliues;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetalleSolicitudInsertarActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ControlBDPoliUES helper;
    Spinner spinnerAreas;
    String AreaSeleccionada;
    String FechaInicio;
    String FechaFin;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);

        spinnerAreas = (Spinner) findViewById(R.id.SpinnerArea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Areas, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAreas.setAdapter(adapter);
        spinnerAreas.setOnItemSelectedListener(this);


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


    }

    private void updateDisplayInicio(){
        mDateDisplayInicio.setText(
                new StringBuilder()

                        .append(mDayInicio).append("-")
                        .append(mMonthInicio + 1).append("-")
                        .append(mYearInicio).append("")
        );

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




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.AreaSeleccionada = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void insertarDetalleSolicitud(View v){


    }

    public void limpiarDetalleSolicitud(View v) {


    }
}

