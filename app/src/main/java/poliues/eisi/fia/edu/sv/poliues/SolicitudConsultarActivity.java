package poliues.eisi.fia.edu.sv.poliues;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SolicitudConsultarActivity extends ListActivity {
    String[] solicitudes = {"solicitud1","solicitud2","solicitud1","solicitud2","solicitud1","solicitud2","solicitud1","solicitud2","solicitud1","solicitud2","solicitud1","solicitud2","solicitud1","solicitud2"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,solicitudes));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, solicitudes);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);


    }

}
