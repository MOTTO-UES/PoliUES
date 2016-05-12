package poliues.eisi.fia.edu.sv.poliues;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class SolicitudInsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ControlBDPoliUES helper;
    EditText editMotivo;
    Spinner spinnerActividad;
    String actividadSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);


        helper = new ControlBDPoliUES(this);
        editMotivo = (EditText) findViewById(R.id.editMotivoSolicitud);
        spinnerActividad = (Spinner) findViewById(R.id.SpinnerActividad);


        ArrayAdapter<CharSequence> adapterAct = ArrayAdapter.createFromResource(this, R.array.Actividades, android.R.layout.simple_spinner_item);


        adapterAct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActividad.setAdapter(adapterAct);
        spinnerActividad.setOnItemSelectedListener(this);


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
            case R.id.opcionesMenu:
                intent = new Intent(this,SolicitudConsultarActivity.class);
                startActivity(intent);
                break;
            case R.id.actInsertar:
                intent = new Intent(this,SolicitudInsertarActivity.class);
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

    public void InsertarSolicitud(View v) {
        String actividad = this.actividadSeleccionada;
        String motivo = editMotivo.getText().toString();
        String regInsertados;
        String fechaCreacion;

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        fechaCreacion = String.valueOf(dateFormat.format(cal.getTime()));
        System.out.println(fechaCreacion);
        Solicitud solicitud = new Solicitud();

        //solicitud.setIdSolicitud(6);
        solicitud.setActividad(4);
        solicitud.setMotivoSolicitud(motivo);
        solicitud.setTarifa(1);
        solicitud.setSolicitante(1);
        solicitud.setEstadoSolicitud("pendiente");
        solicitud.setFechaCreacion(fechaCreacion);

        helper.abrir();
        regInsertados = helper.insertar(solicitud);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


        Intent i = new Intent(this, DetalleSolicitudInsertarActivity.class);
        startActivity(i);
    }


    public void limpiarSolicitud(View v) {
        editMotivo.setText("");
    }
}

