package poliues.eisi.fia.edu.sv.poliues;

import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TarifaEliminarActivity extends AppCompatActivity {
    EditText editIdTarifa;
    ControlBDPoliUES helper;
    Conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_eliminar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        conn=new Conexion();

        helper = new ControlBDPoliUES(this);

        editIdTarifa = (EditText) findViewById(R.id.editIdTarifa);
    }


    public void eliminarTarifa(View v){

        int idTarifa = 0;
        int Usado=0;
        String mensaje=null;
        idTarifa = Integer.valueOf( editIdTarifa.getText().toString());

        helper.abrir();

        Cursor tarifaSolicitud = helper.consultarSolicitud();

        if (tarifaSolicitud.moveToFirst()){
            do {
                if (tarifaSolicitud.getInt(2) == idTarifa){
                    Usado+=1;
                }

            }while (tarifaSolicitud.moveToNext());
        }

        if (Usado==0){
             mensaje = helper.eliminarTarifa(idTarifa);
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Hay solicitudes que usan esta tarifa no se puede eliminar",Toast.LENGTH_LONG).show();
        }


    }

    public void eliminarTarifaWeb(View v) {
        System.out.println("entro en el metodo");


        String idTarifa = editIdTarifa.getText().toString();
        System.out.println("1 lectura");


        String url = "";

        System.out.println("obtener url");
        url+=conn.getURLLocal()+"/ws_tarifa_delete.php"+ "?idTarifa=" + idTarifa;

        System.out.println("antes de invocar el metodo php");
        ControladorServicio.eliminarTarifaPHP(url, this);

        System.out.println("despues de invocar el metodo php");

    }
}
