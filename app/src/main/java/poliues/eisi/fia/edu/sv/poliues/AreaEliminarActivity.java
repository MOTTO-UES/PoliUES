package poliues.eisi.fia.edu.sv.poliues;


import android.app.Activity;

import android.os.StrictMode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaEliminarActivity extends Activity {

    EditText editIdarea;
    ControlBDPoliUES controlhelper;
    Conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_eliminar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        conn=new Conexion();
        controlhelper=new ControlBDPoliUES(this);
        editIdarea=(EditText)findViewById(R.id.editidArea);
    }

    public void eliminarReserva(View v){
        String regEliminadas;
        Area area=new Area();
        area.setIdarea(Integer.parseInt(editIdarea.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarArea(area);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void eliminarAreaWeb(View v) {


        String idArea = editIdarea.getText().toString();


        String url = "";


        url+=conn.getURLLocal()+"/ws_area_delete.php"+ "?idArea=" + idArea;

        ControladorServicio.eliminarAreaPHP(url, this);


    }
}
