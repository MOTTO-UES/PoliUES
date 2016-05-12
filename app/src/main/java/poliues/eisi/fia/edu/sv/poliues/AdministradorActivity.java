package poliues.eisi.fia.edu.sv.poliues;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.AdapterView;
=======
>>>>>>> remotes/origin/rodrigo
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
<<<<<<< HEAD
import android.widget.PopupMenu;
=======
>>>>>>> remotes/origin/rodrigo
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD
=======

import java.util.ArrayList;
import java.util.List;
>>>>>>> remotes/origin/rodrigo

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class AdministradorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

        ListView lista;
        ControlBDPoliUES db;
        List<String> item = null;
        List<Administrador> objAdministrador = null;
=======
        ListView lista;
        ControlBDPoliUES db;
        List<String> item = null;
>>>>>>> remotes/origin/rodrigo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministradorActivity.this,AdministradorInsertarActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lista = (ListView) findViewById(R.id.listView_listaAdmin);

        try {
            showAdministrador();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "error papu", Toast.LENGTH_SHORT).show();
        }

        /////////////////////////////////////////////////////////////////////////////////////
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
        getMenuInflater().inflate(R.menu.administrador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_administrador_agregar) {
            Intent intent = new Intent(AdministradorActivity.this,AdministradorInsertarActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.administrador) {
            // Handle the camera action
        } else if (id == R.id.solicitante) {
            Intent inte = new Intent(this, SolicitanteActivity.class);
            startActivity(inte);
        }/* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAdministrador(){
<<<<<<< HEAD

        db = new ControlBDPoliUES(this);
        db.leer();
        Cursor c = db.consultarAdministrador();


        if(!(c == null)){ //Si esta vacio
            item = new ArrayList<String>();
            objAdministrador = new ArrayList<Administrador>();


            if(c.moveToFirst()){
                //Recorre todos los registros
                do {

                    Administrador administrador = new Administrador();
                    administrador.setIdAdministrador(c.getInt(0));
                    administrador.setNombreAdmin(c.getString(1));
                    administrador.setPasswordAdmin(c.getString(2));
                    administrador.setCorreoAdmin(c.getString(3));

                    objAdministrador.add(administrador);

                    //Agregar Registro a la lista
                    item.add(administrador.getIdAdministrador() + "   "+  administrador.getNombreAdmin().toString() + "       "+ administrador.getPasswordAdmin().toString()+ "      "+administrador.getCorreoAdmin().toString());

                }while(c.moveToNext());
            }
            //Crear un adaptador
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, item);

            lista.setAdapter(adaptador);

            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    ////////////////////////////////////////////////////////////////
                    //PopUp

                    final Administrador admin;
                    admin = objAdministrador.get(position);
                    final Intent inte = new Intent();
                    //Agregar Extras
                    inte.putExtra("EnvioAdministradorID", admin.getIdAdministrador());
                    inte.putExtra("EnvioAdministradorNOMBRE", admin.getNombreAdmin());
                    inte.putExtra("EnvioAdministradorPASS", admin.getPasswordAdmin());
                    inte.putExtra("EnvioAdministradorCORREO", admin.getCorreoAdmin());

                    PopupMenu pop = new PopupMenu(getApplicationContext(), view);

                    pop.inflate(R.menu.menu_popup);

                    pop.show();

                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            int id = item.getItemId();

                            if (id == R.id.ConsultarA) {
                                inte.setClass(AdministradorActivity.this, AdministradorConsultarActivity.class);
                                startActivity(inte);
                            } else if (id == R.id.EditarA) {
                                inte.setClass(AdministradorActivity.this, AdministradorEditarActivity.class);
                                startActivity(inte);
                            } else if (id == R.id.BorrarA) {


                                ///////////////////////////////////
                                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(AdministradorActivity.this);
                                dialogo1.setTitle("BORRAR");
                                dialogo1.setMessage("¿ Desea Eliminar este Administrador ?");
                                dialogo1.setCancelable(false);
                                dialogo1.setPositiveButton("BORRAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String regEliminadas;

                                        db.abrir();
                                        regEliminadas = db.eliminarAdministrador(admin);
                                        db.cerrar();

                                        showAdministrador();
                                        Toast.makeText(AdministradorActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();

                                    }
                                });
                                dialogo1.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        dialogo1.cancel();
                                    }
                                });
                                dialogo1.show();
                                ///////////////////////////////////


                            }

                            return false;
                        }
                    });

                    return false;
                    ////////////////////////////////////////////////////////////////
                }
            });
        }





    }


    public void cancelar() {
        finish();
=======
        ///test
        Toast.makeText(this, "antes de", Toast.LENGTH_SHORT).show();
        //fin test
        ///////////////////////////////////////////////////////////////////////////////
        db = new ControlBDPoliUES(this);
        db.leer();
        Cursor c = db.consultarAdministrador();
        item = new ArrayList<String>();
        ///test
        //Toast.makeText(this, "bien 1", Toast.LENGTH_SHORT).show();
        //Log.d("My tag", "Antes de cursor");
        //fin test
        //String nombre ="", password="", correo="";
        if(c.moveToFirst()){
            //Recorre todos los registros
            do {

                Administrador administrador = new Administrador();
                administrador.setIdAdministrador(c.getInt(0));
                administrador.setNombreAdmin(c.getString(1));
                administrador.setPasswordAdmin(c.getString(2));
                administrador.setCorreoAdmin(c.getString(3));

                //nombre = c.getString(1);
                //password = c.getString(2);
                //correo = c.getString(3);

                //Agregar Registro a la lista
                item.add(administrador.getNombreAdmin().toString() + "       "+ administrador.getPasswordAdmin().toString()+ "      "+administrador.getCorreoAdmin().toString());
                //item.add(nombre+ " "+ password+ " "+ correo );

            }while(c.moveToNext());
        }
        //Crear un adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, item);

        lista.setAdapter(adaptador);
>>>>>>> remotes/origin/rodrigo
    }


}
