package poliues.eisi.fia.edu.sv.poliues;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PolideportivoActivity extends ListActivity {
    String[] menu={"Deporte","Area","Pagina principal","Llenado de Base"};
    String[] activities={"DeporteMenuActivity","AreaMenuActivity","principal"};
    ControlBDPoliUES BDhelper;
    Bundle admi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polideportivo);
        admi = getIntent().getExtras();

        BDhelper = new ControlBDPoliUES(this);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        ListView listView = getListView();
        listView.setBackgroundColor(Color.parseColor("#FFCECE"));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        //Toast.makeText(this,position,Toast.LENGTH_SHORT).show();
        if(position!=3){
            String nombreValue=activities[position];
            try{
                System.out.println("Numero de casilla: "+position);
                Class<?> clase=Class.forName("poliues.eisi.fia.edu.sv.poliues."+nombreValue);
                Intent inte = new Intent(this,clase);
                inte.putExtra("EnvioAdministradorID",admi.getInt("EnvioAdministradorID"));
                inte.putExtra("EnvioAdministradorNOMBRE",admi.getString("EnvioAdministradorNOMBRE"));
                inte.putExtra("EnvioAdministradorPASS",admi.getString("EnvioAdministradorPASS"));
                inte.putExtra("EnvioAdministradorCORREO",admi.getString("EnvioAdministradorCORREO"));
                inte.putExtra("EnvioAdministradorIDENTIFICADOR",admi.getString("EnvioAdministradorIDENTIFICADOR"));
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            BDhelper.abrir();
            String tost=BDhelper.llenarBDPolideportivo();
            BDhelper.cerrar();
            Toast.makeText(this,tost,Toast.LENGTH_SHORT).show();
        }
    }
}
