package poliues.eisi.fia.edu.sv.poliues;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.Gravity;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AdministradorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



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

        /////////////////////////////////////////////////////////////////////////////////////
        /*
        // Datos para la tabla
        String cabeceras[] = { "Año", "Ciudad", "Oro", "Plata", "Bronce" };
        String datos[][] = { { "1900", "París", "1", "", "" },
                { "1976", "Montreal", "", "2", "" },
                { "1992", "Barcelona", "13", "7", "2" },
                { "2012", "Londres", "3", "10", "4" } };
        // TableLayout (diseño principal de la actividad)
        TableLayout tabla = new TableLayout(this);
        tabla.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        tabla.setColumnStretchable(1, true);

        // Cabecera de la tabla
        TableRow cabecera = new TableRow(this);
        cabecera.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        tabla.addView(cabecera);

        // Textos de la cabecera
        for (int i = 0; i < 5; i++)
        {
            TextView columna = new TextView(this);
            columna.setLayoutParams(new TableRow.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            columna.setText(cabeceras[i]);
            columna.setTextColor(Color.parseColor("#005500"));
            columna.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            columna.setGravity(Gravity.CENTER_HORIZONTAL);
            columna.setPadding(5, 5, 5, 5);
            cabecera.addView(columna);
        }
        */
/*

        TableLayout tabla = (TableLayout) findViewById(R.id.tablaAdministrador);
        TableRow fila = (TableRow) findViewById(R.id.fila1);



        // Provide this parameter so that the whole text can be seen with no cutoff
        comment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,1.0f));
        fila.addView(comment);

        tabla.addView(fila);
*/
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
        if (id == R.id.action_settings) {
            return true;
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
            //Intent inte = new Intent(this, SolicitanteActivity.class);
            //startActivity(inte);
        }/* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
