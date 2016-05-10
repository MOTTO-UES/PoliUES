package poliues.eisi.fia.edu.sv.poliues;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SolicitudConsultarActivity extends Activity {

    private ListView list;
    String[] solicitudes = {"solicitud1", "solicitud2", "solicitud1", "solicitud2", "solicitud1", "solicitud2", "solicitud1", "solicitud2", "solicitud1", "solicitud2", "solicitud1", "solicitud2", "solicitud1", "solicitud2"};
    AlertDialog alertDialog;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_consultar);

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Que Accion desea Realizar");
        alertDialog.setButton("Consultar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // aquí puedes añadir funciones
            }
        });
        alertDialog.setButton2("Actualizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llamarActualizar();
                            }
        });
        alertDialog.setButton3("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // aquí puedes añadir funciones
            }
        });


        list = (ListView) findViewById(R.id.listado);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, solicitudes);
        list.setAdapter(adaptador);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
                alertDialog.show();

            }

        });

    }


    public void llamarActualizar(){
        Intent o = new Intent(this,SolicitudActualizarActivity.class);
        startActivity(o);
    }

    public void llmarEliminar(){
        Intent o = new Intent(this,SolicitudEliminarActivity.class);
        startActivity(o);
    }

}
