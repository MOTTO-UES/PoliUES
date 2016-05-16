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
import android.widget.Toast;

public class principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        String identificador=null;
        Administrador admin = null;
        ControlBDPoliUES dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbhelper = new ControlBDPoliUES(this);


        //////////////////////////////////////////////
        ////// admin  contiene Administrador Logueado

        admin= administradorLogueado();

        //final Administrador admin = administradorLogueado();

        //////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "BIENVENIDO "+String.valueOf(admin.getNombreAdmin()), Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
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
        getMenuInflater().inflate(R.menu.principal, menu);
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
        else{
            if(id == R.id.consultarSolicitud){
                Intent inte = new Intent(this,SolicitudConsultarActivity.class);
                inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
                inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
                inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
                inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
                inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
                inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
                startActivity(inte);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.administrador) {
            Intent inte = new Intent(principal.this, AdministradorActivity.class);
            inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
            inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
            inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
            startActivity(inte);

        } else if (id == R.id.solicitante) {
            Intent inte = new Intent(this, SolicitanteActivity.class);
            //inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
            inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
            inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
            startActivity(inte);

        } else if (id == R.id.solicitud) {
            Intent inte = new Intent(this, SolicitudConsultarActivity.class);
            inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
            inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
            inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
            startActivity(inte);

        } else if (id == R.id.actividad) {
            Intent inte = new Intent(this, ActividadActivity.class);
            inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
            inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
            inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
            startActivity(inte);
        }else if(id == R.id.tarifa){
            Intent inte = new Intent(this,TarifaMenuActivity.class);
            inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
            inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
            inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
            inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
            startActivity(inte);
        }
        else if (id == R.id.cerrarSesionAdmin) {
            finish();
            Intent inte = new Intent(this, LoginActivity.class);
            startActivity(inte);

        }
        else {
            if (id == R.id.nav_listar_reserva) {
                // Handle the camera action
                try{
                    Class<?> clase=Class.forName("poliues.eisi.fia.edu.sv.poliues.ListarReservaActivity");
                    Intent inte = new Intent(this,clase);
                    inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
                    inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
                    inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
                    inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
                    inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
                    inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }



            } else if (id == R.id.nav_almacenar) {
                dbhelper.abrir();
                String tost = dbhelper.llenarBDPoli();
                Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
                dbhelper.cerrar();

            }
            else if(id == R.id.areasPoli){
                Intent inte = new Intent(this, PolideportivoActivity.class);
                inte.putExtra("IDUSUARIO", admin.getIdAdministrador());
                inte.putExtra("EnvioAdministradorID",admin.getIdAdministrador());
                inte.putExtra("EnvioAdministradorNOMBRE",admin.getNombreAdmin());
                inte.putExtra("EnvioAdministradorPASS",admin.getPasswordAdmin());
                inte.putExtra("EnvioAdministradorCORREO",admin.getCorreoAdmin());
                inte.putExtra("EnvioAdministradorIDENTIFICADOR",identificador);
                startActivity(inte);
            }
            else if(id == R.id.actiAlmacenar){
                dbhelper.abrir();
                String tost = dbhelper.llenarActividades();
                Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
                dbhelper.cerrar();
            }
            else if (id == R.id.areaAlmacenar){
                dbhelper.abrir();
                String tost = dbhelper.llenarBDPolideportivo();
                Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
                dbhelper.cerrar();

            }
            else if (id == R.id.tarifaAlmacenar){
                dbhelper.abrir();
                String tost = dbhelper.llenarTarifa();
                Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
                dbhelper.cerrar();

            }


        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Administrador administradorLogueado(){

        Administrador administrador = new Administrador();

        int ExtraID = getIntent().getExtras().getInt("EnvioAdministradorID");
        String ExtraNOMBRE = getIntent().getExtras().getString("EnvioAdministradorNOMBRE");
        String ExtraPASS = getIntent().getExtras().getString("EnvioAdministradorPASS");
        String ExtraCORREO = getIntent().getExtras().getString("EnvioAdministradorCORREO");
        identificador = getIntent().getExtras().getString("EnvioAdministradorIDENTIFICADOR");

        System.out.println("ES ADMIN?: "+identificador);

        administrador.setIdAdministrador(ExtraID);
        administrador.setNombreAdmin(ExtraNOMBRE);
        administrador.setPasswordAdmin(ExtraPASS);
        administrador.setCorreoAdmin(ExtraCORREO);

        return administrador;
    }
}
