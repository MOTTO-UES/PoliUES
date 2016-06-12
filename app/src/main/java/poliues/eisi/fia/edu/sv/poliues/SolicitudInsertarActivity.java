package poliues.eisi.fia.edu.sv.poliues;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SolicitudInsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ControlBDPoliUES helper;
    EditText editMotivo;
    EditText editPersonas;
    Spinner spinnerActividad;
    String actividadSeleccionada;
    Solicitante soli=null;
    List<String> actividades = null;
    Bundle admi=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);
        admi = getIntent().getExtras();

        soli = new Solicitante();

        soli.setIdSolicitante(getIntent().getExtras().getInt("IDUSUARIO"));

        System.out.println("usuario: "+soli.getIdSolicitante());


        helper = new ControlBDPoliUES(this);
        editMotivo = (EditText) findViewById(R.id.editMotivoSolicitud);
        editPersonas = (EditText) findViewById(R.id.editCantidadPersonasSolicitud);
        spinnerActividad = (Spinner) findViewById(R.id.SpinnerActividad);


        actividades = actividades();


        //ArrayAdapter<CharSequence> adapterAct = ArrayAdapter.createFromResource(this, R.array.Actividades, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapterAct= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, actividades);


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

    public void InsertarSolicitud(View v) {

        String actividad = this.actividadSeleccionada;
        String motivo = editMotivo.getText().toString();
        int personas =  0;
        double tarifa=0;
        double tarifaMinima=1000000;
        String regInsertados;
        String fechaCreacion;
        Solicitud solicitud = new Solicitud();
        Actividad activi =null;
        int motivoExistente=0;
        String admin="";

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        personas+=Integer.valueOf(editPersonas.getText().toString());

        fechaCreacion = String.valueOf(dateFormat.format(cal.getTime()));
        System.out.println(fechaCreacion);
        helper.abrir();

        Cursor acti = helper.consultarActividad();

        if (acti.moveToFirst()){

            do {
                Actividad actividadC= new Actividad();

                actividadC.setIdActividad(acti.getInt(0));
                actividadC.setNombreActividad(acti.getString(1));
                actividadC.setDescripcionActividad(acti.getString(2));

                if (actividadC.getNombreActividad().equals(actividadSeleccionada)){
                    activi = actividadC;
                    break;
                }


            }while (acti.moveToNext());

        }

        if(actividadSeleccionada.equals("Politica")){

            Cursor cursorTarifa = helper.obtenerTarifa();

            if (cursorTarifa.moveToFirst()){
                do {
                    if(personas>cursorTarifa.getInt(1)){
                        tarifa = cursorTarifa.getDouble(2);
                    }

                    if(tarifaMinima>cursorTarifa.getDouble(2)){
                        tarifaMinima = cursorTarifa.getDouble(2);
                    }

                }while (cursorTarifa.moveToNext());

            }

            if(tarifa==0){
                tarifa=tarifaMinima;
            }
        }



        Cursor cursorMotivo = helper.consultarSolicitud();

        if (cursorMotivo.moveToFirst()){
            do {
                if(cursorMotivo.getString(5).equals(motivo)){
                    motivoExistente+=1;
                }

            }while (cursorMotivo.moveToNext());
        }

        admin+= admi.getString("EnvioAdministradorIDENTIFICADOR");

        if(admin.equals("admin")){

            Toast.makeText(this,"es un administrador no puede ingresar",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this,principal.class);
            intent.putExtra("IDUSUARIO",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(intent);

        }
        else {if(motivoExistente==0){
                solicitud.setActividad(activi.getIdActividad());
                solicitud.setMotivoSolicitud(motivo);
                solicitud.setTarifa(tarifa);
                solicitud.setSolicitante(soli.getIdSolicitante());
                solicitud.setEstadoSolicitud("pendiente");
                solicitud.setFechaCreacion(fechaCreacion);
                solicitud.setCantidadPersonas(personas);


                regInsertados = helper.insertar(solicitud);
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


                Intent i = new Intent(this, DetalleSolicitudInsertarActivity.class);
                i.putExtra("IDUSUARIO",soli.getIdSolicitante());
                i.putExtra("tarifa",tarifa);
                startActivity(i);

            }
            else {
                Toast.makeText(this, "Motivo repetido ingrese uno nuevo", Toast.LENGTH_SHORT).show();

            }
            helper.cerrar();

        }




    }


    public void limpiarSolicitud(View v) {
        editMotivo.setText("");
    }

    public ArrayList<String> actividades() {
        List<String> a = new ArrayList<String>();
        Cursor ac;

        helper = new ControlBDPoliUES(this);
        helper.leer();

        ac = helper.consultarActividad();

        if (ac.moveToFirst()) {

            do {

                Actividad act = new Actividad();

                act.setIdActividad(ac.getInt(0));
                act.setNombreActividad(ac.getString(1));
                act.setDescripcionActividad(ac.getString(2));


                a.add(act.getNombreActividad());


            } while (ac.moveToNext());

        }

        return (ArrayList<String>) a;
    }
}

