package poliues.eisi.fia.edu.sv.poliues;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SolicitudConsultarActivity extends AppCompatActivity {

    private ListView list;
    String[] solicitudes;
    AlertDialog alertDialog;
    AlertDialog alertDialogE;
    ControlBDPoliUES helper;
    Cursor cursor;
    List<String> item = null;
    String datoAbuscar;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_consultar);


        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Que Accion desea Realizar");
        alertDialog.setButton("Consultar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llamarConsultar();
            }
        });
        alertDialog.setButton2("Actualizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llamarActualizar();
                            }
        });
        alertDialog.setButton3("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llamarEliminar();
            }
        });


        alertDialogE = new AlertDialog.Builder(this).create();
        alertDialogE.setTitle("Seguro que quiere eliminar");
        alertDialogE.setButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    eliminarSolicitud();
            }
        });
        alertDialogE.setButton2("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        list = (ListView) findViewById(R.id.listado);

        try {
            llenarSolicitudes();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "error de llenado", Toast.LENGTH_SHORT).show();
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
                datoAbuscar = (String)(list.getItemAtPosition(position));

                System.out.println(datoAbuscar);
                alertDialog.show();


            }

        });

    }


    public void llamarActualizar(){
        Intent o = new Intent(this,SolicitudActualizarActivity.class);
        startActivity(o);
    }

    public void llamarEliminar(){
        alertDialogE.show();

    }

    public void llamarConsultar(){
        Intent o = new Intent(this, verSolicitudActivity.class);
        o.putExtra("motivo",datoAbuscar);
        startActivity(o);
    }

    public void eliminarSolicitud(){
        helper = new ControlBDPoliUES(this);
        helper.leer();
        cursor = helper.consultarSolicitud();


    }


    public void llenarSolicitudes(){
        helper = new ControlBDPoliUES(this);
        helper.leer();
        cursor = helper.consultarSolicitud();
        //helper.cerrar();

        item = new ArrayList<String>();

        if(cursor.moveToFirst()){

            do {

                Solicitud solicitud = new Solicitud();

                solicitud.setIdSolicitud(cursor.getInt(0));

                solicitud.setMotivoSolicitud(cursor.getString(5));

                item.add(solicitud.getMotivoSolicitud());


            }while(cursor.moveToNext());
        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        list.setAdapter(adaptador);
    }

}
