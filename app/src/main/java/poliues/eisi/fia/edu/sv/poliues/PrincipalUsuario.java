package poliues.eisi.fia.edu.sv.poliues;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> remotes/origin/rodrigo
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
<<<<<<< HEAD
        getMenuInflater().inflate(R.menu.principal_usuario, menu);
=======
        getMenuInflater().inflate(R.menu.opcionessolicitante,menu);
>>>>>>> remotes/origin/rodrigo
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
<<<<<<< HEAD
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

=======
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
            case R.id.actPrincipalUsuario:
                intent = new Intent(this,PrincipalUsuario.class);
                startActivity(intent);
                break;
        }


>>>>>>> remotes/origin/rodrigo
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
    ////////////////////////////////////////////////////////////////////////
<<<<<<< HEAD
        if (id == R.id.solicitudUsuarioCrear) {
            // Codigo INTENT crear Solicitud Usuario
        } else if (id == R.id.solicitudUsuarioVer) {
            //COdigo INTENT para redirigir a ver solicitudes
=======
        Intent intent;
        if (id == R.id.solicitudUsuarioCrear) {
            intent = new Intent(this,SolicitudInsertarActivity.class);
            startActivity(intent);
        } else if (id == R.id.solicitudUsuarioVer) {
            intent = new Intent(this,SolicitudConsultarActivity.class);
            startActivity(intent);
>>>>>>> remotes/origin/rodrigo
        } else if (id == R.id.cerrarSesionUsuario) {
                //Codigo para cerrar sesion y redirigir al login
        }
    //////////////////////////////////////////////////////////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
