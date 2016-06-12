package poliues.eisi.fia.edu.sv.poliues;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("NewApi")
public class AreaActualizarActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_area_actualizar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        conn=new Conexion();


        helper = new ControlBDPoliUES(this);
        editIdarea = (EditText) findViewById(R.id.editidArea);
        editMaximopersonas = (EditText) findViewById(R.id.editmaximopersonas);
        editNombreArea = (EditText) findViewById(R.id.editnombrearea);
        editDescripcionarea = (EditText) findViewById(R.id.editDescripcionarea);
    }

    public void actualizarArea(View v) {
        Area area = new Area();
        area.setIdarea(Integer.parseInt(editIdarea.getText().toString()));
        area.setMaximopersonas(Integer.parseInt(editMaximopersonas.getText().toString()));
        area.setNombrearea(editNombreArea.getText().toString());
        area.setDescripcionarea(editDescripcionarea.getText().toString());
        helper.abrir();
        String estado = helper.actualizarArea(area);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarAA(View v) {
        editIdarea.setText("");
        editMaximopersonas.setText("");
        editNombreArea.setText("");
        editDescripcionarea.setText("");

    }


    public void actualizarAreaWeb(View v){

        String id = editIdarea.getText().toString();
        String maxPersonas = editMaximopersonas.getText().toString();
        String nombre = editNombreArea.getText().toString();
        String descripcion = editDescripcionarea.getText().toString();


        String url = "";

        url+=conn.getURLLocal()+"/ws_area_update.php"+ "?idArea=" + id + "&maximoPersonas=" + maxPersonas + "&nombArea=" + nombre + "&descripcionArea=" + descripcion;

        int respuesta = ControladorServicio.actualizarAreaPHP(url, this);

        if (respuesta == 1)
            Toast.makeText(this, "Registro modificado", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "No se pudo modificar", Toast.LENGTH_LONG).show();

    }


}
