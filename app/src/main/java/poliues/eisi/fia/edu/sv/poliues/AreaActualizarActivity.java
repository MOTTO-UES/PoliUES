package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaActualizarActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText editIdarea;
    EditText editMaximopersonas;
    EditText editNombreArea;
    EditText editDescripcionarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_actualizar);
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
}
