package poliues.eisi.fia.edu.sv.poliues;

import android.app.ActionBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



import java.util.ArrayList;

public class ListarReservaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //global variables
    private static int realIdD =0;

    private static ControlBDPoliUES dbhelper;

    private static ListView reservaList;
    Bundle admi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_reserva);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        admi = getIntent().getExtras();


        //instance variables
        reservaList = (ListView) findViewById(R.id.reservaList);
        dbhelper = new ControlBDPoliUES(this);
        //movieC = new MovieController();

        //click in item of list view
        reservaList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        //obtain an Id to selected
                        int realId = obtainSelectedId(position);

                        //change the activity, and send parameters, true if is update
                        //and false if isn't
                        Intent i = new Intent(ListarReservaActivity.this, ReservaInsertarActivity.class);
                        i.putExtra("isEdit", true);
                        i.putExtra("realId", realId);
                        i.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                        i.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                        i.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                        i.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                        i.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                        startActivity(i);
                    }
                }
        );

        //if the user press long in a item selected, the system delete this row
        reservaList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {


                //obtain an Id to selected
                realIdD = obtainSelectedId(pos);
                creardialogo();

                /*dbhelper.abrir();

                //Creamos un Objeto reserva
                Reserva reserva = new Reserva();
                reserva.setIdreserva(realId);


                //delete the item selected
                String toastinfo = dbhelper.eliminar(reserva);
                dbhelper.cerrar();
                mensajes(toastinfo);


                //refresh the listview
                refreshListView();*/
                return true;

            }
        });


    }


    /**
     * This method refresh list view
     */
    private void refreshListView() {
        ArrayList<String> reservaResults = new ArrayList<String>();
        dbhelper.abrir();


        //obtain the cursor of get all
        Cursor getReserva = dbhelper.todaslasreservas();

        if (getReserva != null) {
            //Move cursor to first row
            if (getReserva.moveToFirst()) {
                do {
                    //Get version from Cursor
                    String firstName = getReserva.getString(getReserva.getColumnIndex("motivo"));
                    //Add the version to Arraylist 'results'
                    reservaResults.add(firstName);
                } while (getReserva.moveToNext()); //Move to next row
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, reservaResults);
        reservaList.setAdapter(adapter);
        dbhelper.cerrar();
    }


    /**
     * Obtain the id of selected item
     * if position is equals to listViewPosition
     * obtain the respective Id
     *
     * @param position the int position of list view
     * @return id of real id
     */
    private int obtainSelectedId(int position) {
        int toStop = 0;
        int returnId = 0;
        dbhelper.abrir();
        Cursor getReservas = dbhelper.todaslasreservas();
        if (getReservas != null) {
            //Move cursor to first row
            if (getReservas.moveToFirst()) {
                do {
                    if (position == toStop) {
                        //Get version from Cursor
                        returnId = getReservas.getInt(getReservas.getColumnIndex("idreserva"));
                    }
                    toStop++;
                } while (getReservas.moveToNext()); //Move to next row
            }
        }
        dbhelper.cerrar();
        return returnId;
    }
    public void mensajes(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_reserva, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.gotocreatereserva) {
            Intent intent = new Intent(this, ReservaInsertarActivity.class);
            intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(intent);
            return true;
        } else if (id == R.id.gotoqueryreserva) {
            Intent intent = new Intent(this, ReservaConsultarActivity.class);
            intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return false;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

    }
    public void insertarReserva(View v){
        Intent intent = new Intent(this,ReservaInsertarActivity.class);
        intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
        intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
        intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
        intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
        intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
        startActivity(intent);
    }
    public void consultarReserva(View v){
        Intent intent = new Intent(this,ReservaConsultarActivity.class);
        intent.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
        intent.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
        intent.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
        intent.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
        intent.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
        startActivity(intent);
    }

    public void creardialogo(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿Seguro que Quiere Eliminar esta Reserva ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //aceptar();
                dbhelper.abrir();

                //Creamos un Objeto reserva
                Reserva reserva = new Reserva();
                reserva.setIdreserva(realIdD);


                //delete the item selected
                String toastinfo = dbhelper.eliminar(reserva);
                dbhelper.cerrar();
                mensajes(toastinfo);


                //refresh the listview
                refreshListView();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //cancelar();
                refreshListView();
            }
        });
        dialogo1.show();
    }
}

