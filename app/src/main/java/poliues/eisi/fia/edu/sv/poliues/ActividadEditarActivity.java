package poliues.eisi.fia.edu.sv.poliues;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActividadEditarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText nombre, desc;
    ControlBDPoliUES helper;
    Bundle admi=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        admi = getIntent().getExtras();


        final int ExtraID = getIntent().getExtras().getInt("EnvioActividadID");
        String ExtraNOMBRE = getIntent().getExtras().getString("EnvioActividadNOMBRE");
        String ExtraDESC = getIntent().getExtras().getString("EnvioActividadDESCRIPCION");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActividadEditarActivity.this, ActividadActivity.class);
                intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                startActivity(intent);
            }
        });
        FloatingActionButton fabAceptar = (FloatingActionButton) findViewById(R.id.fabAceptar);
        fabAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarActividad(view, ExtraID);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        helper = new ControlBDPoliUES(this);
        //EdiText
        nombre = (EditText) findViewById(R.id.editText_nombreEditAc);
        desc = (EditText) findViewById(R.id.editText_descEditActi);


        //Metodo que recibe EXTRAS y llena campos de content
        llenarCampos(ExtraNOMBRE, ExtraDESC);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.administrador) {
            finish();
            Intent inte = new Intent(this, AdministradorActivity.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        } else if (id == R.id.solicitante) {
            finish();
            Intent inte = new Intent(this, SolicitanteActivity.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        }else if (id == R.id.actividad) {
            finish();
            Intent inte = new Intent(this, ActividadActivity.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);

        }
        else if (id == R.id.cerrarSesionAdmin) {
            finish();
            Intent inte = new Intent(this, LoginActivity.class);
            startActivity(inte);

        }
        else if (id == R.id.solicitud) {
            finish();
            Intent inte = new Intent(this, SolicitudConsultarActivity.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        }
        else if(id == R.id.tarifa){
            finish();
            Intent inte = new Intent(this,TarifaMenuActivity.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        }
        else if (id == R.id.nav_listar_reserva) {
            // Handle the camera action
            try{
                Class<?> clase=Class.forName("poliues.eisi.fia.edu.sv.poliues.ListarReservaActivity");
                finish();
                Intent inte = new Intent(this,clase);
                inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
                inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        } else if(id == R.id.areasPoli){
            finish();
            Intent inte = new Intent(this, PolideportivoActivity.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        }
        else if (id == R.id.Principal){
            finish();
            Intent inte = new Intent(this, principal.class);
            inte.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void actualizarActividad(View view, int ExtraID){
        String nombreAc, descAc;
        String estado;
        boolean valido=false;

        nombreAc = nombre.getText().toString();
        descAc = desc.getText().toString();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Validacion de Campos

        if(!(TextUtils.isEmpty(nombreAc) || TextUtils.isEmpty(descAc))){
                valido = true;
        }else{
            Snackbar.make(view, "Debe llenar todos los campos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (valido){
            Actividad actividad = new Actividad();

            actividad.setIdActividad(ExtraID);
            actividad.setNombreActividad(nombreAc);
            actividad.setDescripcionActividad(descAc);

            helper.abrir();
            estado = helper.actualizarActividad(actividad);
            helper.cerrar();

            Snackbar.make(view, estado, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            //Ir a otra activity

            Intent intent = new Intent(this,ActividadActivity.class);
            intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(intent);

        }
    }

    public void llenarCampos(String ExtraNOMBRE, String ExtraDESC){

        nombre.setText(ExtraNOMBRE);
        desc.setText(ExtraDESC);

    }
}
