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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //////////////////////////////////////////////
        ////// soli  contiene Solicitante Logueado
        soli = solicitanteLogueado();
        //////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "BIENVENIDO "+String.valueOf(soli.getNombre()), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
