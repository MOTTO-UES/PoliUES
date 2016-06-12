package poliues.eisi.fia.edu.sv.poliues;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

@SuppressLint("NewApi")
public class AreaInsertarActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText editIdarea;
    EditText editMaximopersonas;
    EditText editNombreArea;
    EditText editDescripcionarea;

    Conexion conn;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_insertar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        conn=new Conexion();
        helper = new ControlBDPoliUES(this);
        editIdarea= (EditText) findViewById(R.id.editidArea);
        editMaximopersonas = (EditText) findViewById(R.id.editmaximopersonas);
        editNombreArea= (EditText) findViewById(R.id.editnombrearea);
        editDescripcionarea = (EditText) findViewById(R.id.editDescripcionarea);
    }

    public void insertarArea(View v) {
        Integer idArea=Integer.valueOf(editIdarea.getText().toString());
        Integer maximopersonas=Integer.valueOf(editMaximopersonas.getText().toString());
        String nombrearea=editNombreArea.getText().toString();
        String descripcionarea=editDescripcionarea.getText().toString();
        String regInsertados;

        Area area=new Area();
        area.setIdarea(idArea);
        area.setMaximopersonas(maximopersonas);
        area.setNombrearea(nombrearea);
        area.setDescripcionarea(descripcionarea);
        helper.abrir();
        regInsertados=helper.insertarArea(area);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdarea.setText("");
        editMaximopersonas.setText("");
        editNombreArea.setText("");
        editDescripcionarea.setText("");

    }



    public void insertarAreaWeb(View v) {

        String idArea=editIdarea.getText().toString();
        String maximopersonas=editMaximopersonas.getText().toString();
        String nombrearea=editNombreArea.getText().toString();
        String descripcionarea=editDescripcionarea.getText().toString();


        String url = "";

        url+=conn.getURLLocal()+"/ws_area_insert.php"+ "?idArea=" + idArea + "&maximoPersonas=" + maximopersonas + "&nombArea=" + nombrearea + "&descripcionArea=" + descripcionarea;


          int respuesta = ControladorServicio.insertarAreaPHP(url, this);


       /* if (respuesta == 1)
            Toast.makeText(this, "Registro ingresado", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Error registro duplicado", Toast.LENGTH_LONG).show();*/

    }


}
