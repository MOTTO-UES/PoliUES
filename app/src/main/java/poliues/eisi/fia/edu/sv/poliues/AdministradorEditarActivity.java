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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdministradorEditarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText nombre, pass, correo;
    ControlBDPoliUES helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final int ExtraID = getIntent().getExtras().getInt("EnvioAdministradorID");
        String ExtraNOMBRE = getIntent().getExtras().getString("EnvioAdministradorNOMBRE");
        String ExtraPASS = getIntent().getExtras().getString("EnvioAdministradorPASS");
        String ExtraCORREO = getIntent().getExtras().getString("EnvioAdministradorCORREO");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministradorEditarActivity.this, AdministradorActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fabAceptar = (FloatingActionButton) findViewById(R.id.fabAceptar);
        fabAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAdministrador(view, ExtraID);
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
        nombre = (EditText) findViewById(R.id.editText_nombreEditA);
        pass = (EditText) findViewById(R.id.editText_passwordEditA);
        correo = (EditText) findViewById(R.id.editText_correoEditA);

        //Metodo que recibe EXTRAS y llena campos de content
        llenarCampos(ExtraNOMBRE, ExtraPASS, ExtraCORREO);

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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.administrador) {
            Intent inte = new Intent(this, AdministradorActivity.class);
            startActivity(inte);
        } else if (id == R.id.solicitante) {
            Intent inte = new Intent(this, SolicitanteActivity.class);
            startActivity(inte);
        }else if (id == R.id.solicitud) {
            Intent inte = new Intent(this, SolicitudConsultarActivity.class);
            startActivity(inte);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void actualizarAdministrador(View view, int ExtraID){
        String nombreA, passA, correoA;
        String estado;
        boolean valido=false;

        nombreA = nombre.getText().toString();
        passA = pass.getText().toString();
        correoA = correo.getText().toString();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Validacion de Campos

        if(!(TextUtils.isEmpty(nombreA) || TextUtils.isEmpty(passA) || TextUtils.isEmpty(correoA))){
            if(isEmailValid(correoA)){
                valido = true;
            }else{
                Snackbar.make(view, "El correo no tiene el formato correcto: xxx_xxx@xxx.xxx", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }else{
            Snackbar.make(view, "Debe llenar todos los campos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (valido){
            Administrador administrador = new Administrador();
            //Falta id
            administrador.setIdAdministrador(ExtraID);
            administrador.setNombreAdmin(nombreA);
            administrador.setPasswordAdmin(passA);
            administrador.setCorreoAdmin(correoA);

            helper.abrir();
            estado = helper.actualizarAdministrador(administrador);
            helper.cerrar();

            Snackbar.make(view, estado, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            //Ir a otra activity

            Intent intent = new Intent(AdministradorEditarActivity.this,AdministradorActivity.class);
            startActivity(intent);

        }
    }

        public static boolean isEmailValid(String email) {
            boolean isValid = false;

            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            CharSequence inputStr = email;

            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isValid = true;
            }
            return isValid;
        }

    public void llenarCampos(String ExtraNOMBRE, String ExtraPASS, String ExtraCORREO){

        nombre.setText(ExtraNOMBRE);
        pass.setText(ExtraPASS);
        correo.setText(ExtraCORREO);

    }





}
