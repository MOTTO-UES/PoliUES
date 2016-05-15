package poliues.eisi.fia.edu.sv.poliues;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;


public class ReservaConsultarActivity extends AppCompatActivity {
    ControlBDPoliUES dbhelper;
    Spinner spfacultades;
    EditText editspareas;
    EditText editfechacreacion;
    EditText editnumeropersonas;
    EditText editmotivo;
    EditText editdescripcionreserva;
    EditText editfechareserva;
    EditText edithorainicio;
    EditText edithorafin;
    Button btnConsultar;
    private static int realFacultadId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_consultar);
        dbhelper = new ControlBDPoliUES(this);
        dbhelper.abrir();
        setUIComponents();
        addItemsOnSpinnerFacultades();

    }

    private void setUIComponents(){
        spfacultades = (Spinner) findViewById(R.id.spfacultades);

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
        editfechacreacion=(EditText) findViewById(R.id.editfechacreacion);
        editnumeropersonas = (EditText) findViewById(R.id.editnumeropersonas);
        editdescripcionreserva = (EditText) findViewById(R.id.editdescripcionreserva);
        editspareas = (EditText) findViewById(R.id.editspareas);
        //Fecha Reserva
        editfechareserva = (EditText) findViewById(R.id.editfechareserva);
        //Hora Reserva
        //Hora Inicio
        edithorainicio = (EditText) findViewById(R.id.edithorarioinicio);
        //Hora Fin
        edithorafin = (EditText) findViewById(R.id.edithorariofin);

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

    public void consultarReserva(View v) {
        if (TextUtils.isEmpty(editmotivo.getText().toString())){
            mensajes("El campo motivo esta vacio");
        }else{
            dbhelper.abrir();
            Reserva getReserva = dbhelper.consultarReserva(String.valueOf(realFacultadId), editmotivo.getText().toString());

            dbhelper.cerrar();

            if (getReserva != null ) {
                dbhelper.abrir();



                DetalleReserva getDReserva = dbhelper.consultarDetalleReserva(String.valueOf(getReserva.getIdreserva()));
                Area getArea = dbhelper.consultarArea(String.valueOf(getDReserva.getIdarea()));
                Horario getHorario = dbhelper.consultarHorario(String.valueOf(getReserva.getIdreserva()));
                dbhelper.cerrar();
                //TODO spinner to update
                spfacultades.setSelection(getReserva.getIdfacultad()-1);
                editfechacreacion.setText(getReserva.getFechaingreso());
                editnumeropersonas.setText(String.valueOf(getReserva.getNumeropersonas()));
                editmotivo.setText(getReserva.getMotivo());
                editdescripcionreserva.setText(getReserva.getDescripcionreserva());
                editspareas.setText(String.valueOf(getArea.getNombrearea()));
                editfechareserva.setText(getHorario.getFechareserva());
                edithorainicio.setText(getHorario.getHorarioinicio());
                edithorafin.setText(getHorario.getHorariofin());

            }else{
                mensajes("No se encontro Registro con esas Expecificaciones");
            }
        }



    }

    public void limpiarTexto(View v){
        editspareas.setText("");
        editnumeropersonas.setText("");
        editmotivo.setText("");
        editfechacreacion.setText("");
        editdescripcionreserva.setText("");
        editfechareserva.setText("");
        edithorainicio.setText("");
        edithorafin.setText("");
    }

    public void mensajes(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


    }
}
