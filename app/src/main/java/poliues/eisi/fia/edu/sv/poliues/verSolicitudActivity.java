package poliues.eisi.fia.edu.sv.poliues;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class verSolicitudActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText actividadET;
    EditText areaET;
    EditText motivoET;
    EditText FI;
    EditText FF;
    EditText CT;
    EditText estado;
    Solicitante soli=null;
    String esAdmin=null;
    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_solicitud);

        bundle = getIntent().getExtras();
        esAdmin = bundle.getString("EnvioAdministradorIDENTIFICADOR");
        if(esAdmin==null){
            esAdmin="noEsAdmin";
        }

        soli = new Solicitante();

        soli.setIdSolicitante(getIntent().getExtras().getInt("IDUSUARIO"));

        System.out.println("usuario: "+soli.getIdSolicitante());

        helper = new ControlBDPoliUES(this);

        actividadET = (EditText) findViewById(R.id.editActividad);
        areaET = (EditText) findViewById(R.id.editArea);
        motivoET= (EditText) findViewById(R.id.editMotivo);
        FI= (EditText) findViewById(R.id.editFechaInicio);
        FF= (EditText) findViewById(R.id.editFechaFin);
        CT= (EditText) findViewById(R.id.editCobro);
        estado = (EditText) findViewById(R.id.editEstado);

        verDatos(bundle.getString("motivo"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        if (esAdmin.equals("admin"))
            getMenuInflater().inflate(R.menu.principal,menu);
        else
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
                if (esAdmin.equals("admin")){
                    intent.putExtra("IDUSUARIO",bundle.getInt("EnvioAdministradorID"));
                }
                else {
                intent.putExtra("IDUSUARIO",soli.getIdSolicitante());
                }

                intent.putExtra("EnvioAdministradorID", bundle.getInt("EnvioAdministradorID"));
                intent.putExtra("EnvioAdministradorNOMBRE",bundle.getString("EnvioAdministradorNOMBRE"));
                intent.putExtra("EnvioAdministradorPASS",bundle.getString("EnvioAdministradorPASS"));
                intent.putExtra("EnvioAdministradorCORREO",bundle.getString("EnvioAdministradorCORREO"));
                intent.putExtra("EnvioAdministradorIDENTIFICADOR",bundle.getString("EnvioAdministradorIDENTIFICADOR"));

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
            case R.id.action_settings:
                Intent inte = new Intent(this, principal.class);
                if (esAdmin.equals("admin")){
                    inte.putExtra("IDUSUARIO",bundle.getInt("EnvioAdministradorID"));
                }
                else {
                    inte.putExtra("IDUSUARIO",soli.getIdSolicitante());
                }
                inte.putExtra("EnvioAdministradorID", bundle.getInt("EnvioAdministradorID"));
                inte.putExtra("EnvioAdministradorNOMBRE",bundle.getString("EnvioAdministradorNOMBRE"));
                inte.putExtra("EnvioAdministradorPASS",bundle.getString("EnvioAdministradorPASS"));
                inte.putExtra("EnvioAdministradorCORREO",bundle.getString("EnvioAdministradorCORREO"));
                inte.putExtra("EnvioAdministradorIDENTIFICADOR",bundle.getString("EnvioAdministradorIDENTIFICADOR"));


                startActivity(inte);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void verDatos(String motivo) {
        helper = new ControlBDPoliUES(this);
        helper.leer();
        Cursor cursor = helper.consultarSolicitud();
        //helper.cerrar();

        Cursor cursor2 = helper.consultarDetalleSolicitud();
        Cursor cursor3 = helper.consultarActividad();

        Solicitud solicitud = null;
        DetalleSolicitud DS = null;
        Area area = null;
        Actividad actividad = null;

        solicitud = helper.buscarSolicitud(cursor,motivo);
        DS = helper.buscarDetalleSolicitud(cursor2,solicitud.getIdSolicitud());
        area = helper.consultarAreaJ(String.valueOf(DS.getArea()));

        if (cursor3.moveToFirst()){

            do {
                Actividad actividadC= new Actividad();

                actividadC.setIdActividad(cursor3.getInt(0));
                actividadC.setNombreActividad(cursor3.getString(1));
                actividadC.setDescripcionActividad(cursor3.getString(2));

                if (actividadC.getIdActividad() == solicitud.getActividad()){
                    actividad = actividadC;
                    break;
                }


            }while (cursor3.moveToNext());

        }

        if(solicitud == null || DS == null || area == null || actividad==null){
            Toast.makeText(this,"no encontrado", Toast.LENGTH_LONG).show();
        }
        else{
            actividadET.setText(actividad.getNombreActividad());
            motivoET.setText(solicitud.getMotivoSolicitud());
            areaET.setText(area.getNombrearea());
            FI.setText(DS.getFechaInicio());
            FF.setText(DS.getFechaFinal());
            CT.setText(String.valueOf(DS.getCobroTotal()));
            estado.setText(solicitud.getEstadoSolicitud());
        }
    }
}
