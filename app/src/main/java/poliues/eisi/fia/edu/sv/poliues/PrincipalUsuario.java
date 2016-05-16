package poliues.eisi.fia.edu.sv.poliues;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PrincipalUsuario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Solicitante soli = null;
        Bundle admi=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        admi = getIntent().getExtras();

        //////////////////////////////////////////////
        ////// soli  contiene Solicitante Logueado

        soli = solicitanteLogueado();

        //final Solicitante soli = solicitanteLogueado();

        //////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "BIENVENIDO "+String.valueOf(soli.getNombre()), Snackbar.LENGTH_LONG)
                   //     .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        getMenuInflater().inflate(R.menu.opcionessolicitante,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.consultarSolicitud:
                intent = new Intent(this,SolicitudConsultarActivity.class);
                System.out.println(soli.getIdSolicitante());
                System.out.println(soli.getNombre());
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
    ////////////////////////////////////////////////////////////////////////
        Intent intent;
        if (id == R.id.solicitudUsuarioCrear) {
            intent = new Intent(this,SolicitudInsertarActivity.class);
            intent.putExtra("IDUSUARIO",soli.getIdSolicitante());
            startActivity(intent);
        } else if (id == R.id.solicitudUsuarioVer) {
            intent = new Intent(this,SolicitudConsultarActivity.class);
            intent.putExtra("IDUSUARIO",soli.getIdSolicitante());
            startActivity(intent);
        } else if (id == R.id.cerrarSesionUsuario) {
            finish();
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
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

        //////////////////////////////////////////////////////////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Solicitante solicitanteLogueado(){

        Solicitante solicitante = new Solicitante();

        int ExtraID = getIntent().getExtras().getInt("EnvioSolicitanteID");
        String ExtraNOMBRE = getIntent().getExtras().getString("EnvioSolicitanteNOMBRE");
        String ExtraPASS = getIntent().getExtras().getString("EnvioSolicitantePASS");
        String ExtraCORREO = getIntent().getExtras().getString("EnvioSolicitanteCORREO");

        solicitante.setIdSolicitante(ExtraID);
        solicitante.setNombre(ExtraNOMBRE);
        solicitante.setPassword(ExtraPASS);
        solicitante.setCorreo(ExtraCORREO);

        System.out.println("PRINCIPAL ID: "+solicitante.getIdSolicitante());
        System.out.println("PRINCIPAL NOMBRE: "+solicitante.getNombre());

        return solicitante;
    }
}
