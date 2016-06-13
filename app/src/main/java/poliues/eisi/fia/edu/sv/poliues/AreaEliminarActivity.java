package poliues.eisi.fia.edu.sv.poliues;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaEliminarActivity extends AppCompatActivity {

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

    public void eliminarArea(View v){
        String regEliminadas;
        Area area=new Area();
        area.setIdarea(Integer.parseInt(editIdarea.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarArea(area);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void eliminarAreaWeb(View v) {
        System.out.println("entro en el metodo");


        String idArea = editIdarea.getText().toString();
        System.out.println("1 lectura");


        String url = "";

        System.out.println("obtener url");
        url+=conn.getURLLocal()+"/ws_area_delete.php"+ "?idArea=" + idArea;

        System.out.println("antes de invocar el metodo php");
        ControladorServicio.eliminarAreaPHP(url, this);

        System.out.println("despues de invocar el metodo php");

    }
}
