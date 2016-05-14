package poliues.eisi.fia.edu.sv.poliues;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SolicitanteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lista;
    ControlBDPoliUES db;
    List<String> item = null;
    List<Solicitante> objSolicitante = null;
    Bundle admi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        admi = getIntent().getExtras();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SolicitanteActivity.this,SolicitanteInsertarActivity.class);
                intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
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

        //////////////////////////////////////////////////////////////////////////////
        lista = (ListView) findViewById(R.id.listView_listaSolicitante);

        try {
            showSolicitante();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No existen Solicitantes", Toast.LENGTH_SHORT).show();
        }
        //////////////////////////////////////////////////////////////////////////////
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
        getMenuInflater().inflate(R.menu.solicitante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_solicitante_agregar) {
            Intent intent = new Intent(SolicitanteActivity.this,SolicitanteInsertarActivity.class);
            intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
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
            Intent inte = new Intent(this, AdministradorActivity.class);
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);
        } else if (id == R.id.solicitante) {
            /*Intent inte = new Intent(this, SolicitanteActivity.class);
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(inte);*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showSolicitante(){

        db = new ControlBDPoliUES(this);
        db.leer();
        Cursor c = db.consultarSolicitante();

        if(!(c == null)){ //Si esta vacio

            item = new ArrayList<String>();
            objSolicitante = new ArrayList<Solicitante>();

            if(c.moveToFirst()){
                //Recorre todos los registros
                do {

                    Solicitante solicitante = new Solicitante();
                    solicitante.setIdSolicitante(c.getInt(0));
                    solicitante.setNombre(c.getString(1));
                    solicitante.setPassword(c.getString(2));
                    solicitante.setCorreo(c.getString(3));

                    objSolicitante.add(solicitante);

                    //Agregar Registro a la lista
                    item.add(solicitante.getNombre().toString() + "       "+ solicitante.getPassword().toString()+ "      "+solicitante.getCorreo().toString());


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

                    final Solicitante solicitante;
                    solicitante = objSolicitante.get(position);
                    final Intent inte = new Intent();
                    //Agregar Extras
                    inte.putExtra("EnvioSolicitanteID", solicitante.getIdSolicitante());
                    inte.putExtra("EnvioSolicitanteNOMBRE", solicitante.getNombre());
                    inte.putExtra("EnvioSolicitantePASS", solicitante.getPassword());
                    inte.putExtra("EnvioSolicitanteCORREO", solicitante.getCorreo());

                    PopupMenu pop = new PopupMenu(getApplicationContext(), view);

                    pop.inflate(R.menu.menu_popup);

                    pop.show();

                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            int id = item.getItemId();

                            if (id == R.id.ConsultarA) {
                                inte.setClass(SolicitanteActivity.this, SolicitanteConsultarActivity.class);

                                inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                                startActivity(inte);
                            } else if (id == R.id.EditarA) {
                                inte.setClass(SolicitanteActivity.this, SolicitanteEditarActivity.class);
                                inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                                startActivity(inte);
                            } else if (id == R.id.BorrarA) {


                                ///////////////////////////////////
                                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(SolicitanteActivity.this);
                                dialogo1.setTitle("BORRAR");
                                dialogo1.setMessage("¿ Desea Eliminar este Administrador ?");
                                dialogo1.setCancelable(false);
                                dialogo1.setPositiveButton("BORRAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String regEliminadas;

                                        db.abrir();
                                        regEliminadas = db.eliminarSolicitante(solicitante);
                                        db.cerrar();

                                        showSolicitante();
                                        Toast.makeText(SolicitanteActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();

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
}
