package poliues.eisi.fia.edu.sv.poliues;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class verSolicitudActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText actividadET;
    EditText areaET;
    EditText motivoET;
    EditText FI;
    EditText FF;
    EditText CT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_solicitud);

        Bundle bundle = getIntent().getExtras();

        helper = new ControlBDPoliUES(this);

        actividadET = (EditText) findViewById(R.id.editActividad);
        areaET = (EditText) findViewById(R.id.editArea);
        motivoET= (EditText) findViewById(R.id.editMotivo);
        FI= (EditText) findViewById(R.id.editFechaInicio);
        FF= (EditText) findViewById(R.id.editFechaFin);
        CT= (EditText) findViewById(R.id.editCobro);

        verDatos(bundle.getString("motivo"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opcionessolicitante,menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.opcionesMenu) {
            Intent intent = new Intent(this,SolicitudConsultarActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void verDatos(String motivo) {
        helper = new ControlBDPoliUES(this);
        helper.leer();
        Cursor cursor = helper.consultarSolicitud();
        //helper.cerrar();

        Cursor cursor2 = helper.consultarDetalleSolicitud();

        Solicitud solicitud = null;
        DetalleSolicitud DS = null;

        if(cursor.moveToFirst()){

            do {

                solicitud = new Solicitud();

                solicitud.setIdSolicitud(cursor.getInt(0));
                solicitud.setActividad(cursor.getInt(1));
                solicitud.setTarifa(cursor.getInt(2));
                solicitud.setAdministrador(cursor.getInt(3));
                solicitud.setSolicitante(cursor.getInt(4));
                solicitud.setMotivoSolicitud(cursor.getString(5));
                solicitud.setEstadoSolicitud(cursor.getString(6));
                solicitud.setFechaCreacion(cursor.getString(7));

                if (solicitud.getMotivoSolicitud().equals(motivo)){
                    break;
                }
            }while(cursor.moveToNext());
        }


        if(cursor2.moveToFirst()){

            do {

                DS = new DetalleSolicitud();

                DS.setIdDescripcion(cursor2.getInt(0));
                DS.setSolicitud(cursor2.getInt(1));
                DS.setArea(cursor2.getInt(2));
                DS.setFechaInicio(cursor2.getString(3));
                DS.setFechaFinal(cursor2.getString(4));
                DS.setCobroTotal(cursor2.getDouble(5));

                if (DS.getSolicitud() == solicitud.getIdSolicitud()){
                    break;
                }

            }while(cursor.moveToNext());
        }

        if(solicitud == null)
            Toast.makeText(this,"no encontrado", Toast.LENGTH_LONG).show();
        else{
            actividadET.setText(String.valueOf(solicitud.getActividad()));
            motivoET.setText(solicitud.getMotivoSolicitud());
            areaET.setText(String.valueOf(DS.getArea()));
            FI.setText(DS.getFechaInicio());
            FF.setText(DS.getFechaFinal());
            CT.setText(String.valueOf(DS.getCobroTotal()));
        }
    }
}
