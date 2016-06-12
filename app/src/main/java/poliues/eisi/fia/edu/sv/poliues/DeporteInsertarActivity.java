package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeporteInsertarActivity extends AppCompatActivity {

    ControlBDPoliUES helper;
    EditText editIddeporte;
    EditText editNombredeporte;
    EditText editDescripciondeporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deporte_insertar);
        helper = new ControlBDPoliUES(this);
        editIddeporte = (EditText) findViewById(R.id.editIddeporte);
        editNombredeporte = (EditText) findViewById(R.id.editNombredeporte);
        editDescripciondeporte = (EditText) findViewById(R.id.editDescripciondeporte);
    }

    public void insertarDeporte(View v) {
        Integer idDeporte=Integer.valueOf(editIddeporte.getText().toString());
        String nombreDeporte=editNombredeporte.getText().toString();
        String descripcionDeporte=editDescripciondeporte.getText().toString();
        String regInsertados;
        Deporte deporte=new Deporte();
        deporte.setIddeporte(idDeporte);
        deporte.setNombredeporte(nombreDeporte);
        deporte.setDescripciondeporte(descripcionDeporte);
        helper.abrir();
        regInsertados=helper.insertar(deporte);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIddeporte.setText("");
        editNombredeporte.setText("");
        editDescripciondeporte.setText("");

    }
}
