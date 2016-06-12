package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeporteActualizarActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText editIddeporte;
    EditText editNombredeporte;
    EditText editDescripciondeporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deporte_actualizar);

        helper = new ControlBDPoliUES(this);
        editIddeporte = (EditText) findViewById(R.id.editIddeporte);
        editNombredeporte = (EditText) findViewById(R.id.editNombredeporte);
        editDescripciondeporte = (EditText) findViewById(R.id.editDescripciondeporte);
    }

    public void actualizarDeporte(View v) {
        Deporte deporte = new Deporte();
        deporte.setIddeporte(Integer.parseInt(editIddeporte.getText().toString()));
        deporte.setNombredeporte(editNombredeporte.getText().toString());
        deporte.setDescripciondeporte(editDescripciondeporte.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(deporte);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoA(View v) {
        editIddeporte.setText("");
        editNombredeporte.setText("");
        editDescripciondeporte.setText("");

    }
}
