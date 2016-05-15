package poliues.eisi.fia.edu.sv.poliues;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dario on 08/05/2016.
 */
public class AdministradorInsertarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    EditText nombre, pass, correo;
    ControlBDPoliUES helper;
    Bundle admi;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_insertar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        admi = getIntent().getExtras();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(AdministradorInsertarActivity.this, AdministradorActivity.class);
                inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                startActivity(inte);
            }
        });
        FloatingActionButton fabNuevoAdmin = (FloatingActionButton) findViewById(R.id.fabNuevoAdmin);
        fabNuevoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAdministrador(view);
            }
        });
        FloatingActionButton fabLimpiarAdmin = (FloatingActionButton) findViewById(R.id.fabLimpiarAdmin);
        fabLimpiarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
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
        nombre = (EditText) findViewById(R.id.editText_nombreA);
        pass = (EditText) findViewById(R.id.editText_passwordA);
        correo = (EditText) findViewById(R.id.editText_correoA);


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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.administrador) {
            Intent inte = new Intent(this, AdministradorActivity.class);
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        } else if (id == R.id.solicitante) {
            Intent inte = new Intent(this, SolicitanteActivity.class);
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        }else if (id == R.id.solicitud) {
            Intent inte = new Intent(this, SolicitudConsultarActivity.class);
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        }else if (id == R.id.cerrarSesionAdmin) {
            finish();
            Intent inte = new Intent(this, LoginActivity.class);
            startActivity(inte);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void agregarAdministrador(View view){
        String nombreA, passA, correoA;
        String regIngresados;
        boolean valido=false;

        nombreA = nombre.getText().toString();
        passA = pass.getText().toString();
        correoA = correo.getText().toString();


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

        if (valido){
            Administrador administrador = new Administrador();
            //Falta id
            administrador.setNombreAdmin(nombreA);
            administrador.setPasswordAdmin(passA);
            administrador.setCorreoAdmin(correoA);

            helper.abrir();
            regIngresados = helper.insertarAdministrador(administrador);
            helper.cerrar();

            //Toast.makeText(this,regIngresados,Toast.LENGTH_SHORT).show();
            Snackbar.make(view, "Administrador"+regIngresados+" Ingresado con Exito", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            //Ir a otra activity
            Intent intent = new Intent(AdministradorInsertarActivity.this,AdministradorActivity.class);
            intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(intent);

        }

    }

    private void limpiar(){
        nombre.setText("");
        pass.setText("");
        correo.setText("");
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

}
