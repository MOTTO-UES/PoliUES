package poliues.eisi.fia.edu.sv.poliues;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TarifaMenuActivity extends ListActivity {

    String[] menu={"Insertar Registro","Consultar Registro", "Actualizar Registro","Eliminar Registo"};
    String[]
            activities={"TarifaInsertarActivity","TarifaConsultarActivity", "TarifaActualizarActivity","TarifaEliminarActivity"};
    Bundle admi=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        //listView.setBackgroundColor(Color.rgb(0, 0, 255));
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);

        admi = getIntent().getExtras();
    }


    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));
        try{
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
    }
}