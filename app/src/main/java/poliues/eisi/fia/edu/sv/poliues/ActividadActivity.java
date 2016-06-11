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

public class ActividadActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        Bundle admi= null;


    ListView lista;
    ControlBDPoliUES db;
    List<String> item = null;
    List<Actividad> objActividad = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        admi = getIntent().getExtras();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActividadActivity.this,ActividadInsertarActivity.class);
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

        lista = (ListView) findViewById(R.id.listView_listaActividad);

        try {
            showActividad();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No existen Actividades", Toast.LENGTH_SHORT).show();
        }
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
        getMenuInflater().inflate(R.menu.actividad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_actividad_agregar) {
            Intent intent = new Intent(this,ActividadInsertarActivity.class);
            intent.putExtra("IDUSUARIO", admi.getInt("EnvioAdministradorIDENTIFICADOR"));
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

    private void showActividad(){

        db = new ControlBDPoliUES(this);
        db.leer();
        Cursor c = db.consultarActividad();


        if(!(c == null)){ //Si esta vacio
            item = new ArrayList<String>();
            objActividad = new ArrayList<Actividad>();


            if(c.moveToFirst()){
                //Recorre todos los registros
                do {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(c.getInt(0));
                    actividad.setNombreActividad(c.getString(1));
                    actividad.setDescripcionActividad(c.getString(2));

                    objActividad.add(actividad);

                    //Agregar Registro a la lista
<<<<<<< HEAD
                    item.add(actividad.getIdActividad() + "   "+  actividad.getNombreActividad().toString() + "       "+ actividad.getDescripcionActividad().toString());
=======
                    item.add(actividad.getIdActividad() + "   "+  actividad.getNombreActividad().toString());
>>>>>>> master

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

                    final Actividad acti;
                    acti = objActividad.get(position);
                    final Intent inte = new Intent();
                    //Agregar Extras
                    inte.putExtra("EnvioActividadID", acti.getIdActividad());
                    inte.putExtra("EnvioActividadNOMBRE", acti.getNombreActividad());
                    inte.putExtra("EnvioActividadDESCRIPCION", acti.getDescripcionActividad());

                    inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                    inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                    inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                    inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                    inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));


                    PopupMenu pop = new PopupMenu(getApplicationContext(), view);

                    pop.inflate(R.menu.menu_popup);

                    pop.show();

                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            int id = item.getItemId();

                            if (id == R.id.ConsultarA) {
                                inte.setClass(ActividadActivity.this, ActividadConsultarActivity.class);
                                startActivity(inte);
                            } else if (id == R.id.EditarA) {
                                inte.setClass(ActividadActivity.this, ActividadEditarActivity.class);
                                startActivity(inte);
                            } else if (id == R.id.BorrarA) {


                                ///////////////////////////////////
                                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ActividadActivity.this);
                                dialogo1.setTitle("BORRAR");
                                dialogo1.setMessage("¿ Desea Eliminar esta Actividad ?");
                                dialogo1.setCancelable(false);
                                dialogo1.setPositiveButton("BORRAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String regEliminadas;

                                        db.abrir();
                                        regEliminadas = db.eliminarActividad(acti);
                                        db.cerrar();

                                        showActividad();
                                        Toast.makeText(ActividadActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();

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
