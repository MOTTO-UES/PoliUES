package poliues.eisi.fia.edu.sv.poliues;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeporteMenuActivity extends ListActivity {
    String[] menu = {"Insertar Deporte", "Eliminar Deporte", "Consultar Deporte", "Actualizar Deporte","Pagina principal"};
    String[] activities = {"DeporteInsertarActivity", "DeporteEliminarActivity", "DeporteConsultarActivity", "DeporteActualizarActivity","principal"};
    Bundle admi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deporte_menu);
        admi = getIntent().getExtras();

        ListView listView = getListView();
        listView.setBackgroundColor(Color.parseColor("#FFCECE"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.parseColor("#FFCECE"));
        try {
            Class<?> clase = Class.forName("poliues.eisi.fia.edu.sv.poliues."+nombreValue);
            Intent inte = new Intent(this, clase);
            inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
            inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
            inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
            inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
            inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
