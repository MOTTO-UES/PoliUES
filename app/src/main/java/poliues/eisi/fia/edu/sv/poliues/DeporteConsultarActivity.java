package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeporteConsultarActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText editIddeporte;
    EditText editNombredeporte;
    EditText editDescripciondeporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deporte_consultar);

        helper = new ControlBDPoliUES(this);
        editIddeporte = (EditText) findViewById(R.id.editIddeporte);
        editNombredeporte = (EditText) findViewById(R.id.editNombredeporte);
        editDescripciondeporte = (EditText) findViewById(R.id.editDescripciondeporte);
    }

    public void consultarDeporte(View v) {
        helper.abrir();
        Deporte deporte = helper.consultarDeporte(editIddeporte.getText().toString());
        helper.cerrar();
        if(deporte == null)
            Toast.makeText(this, "Deporte con id " + editIddeporte.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombredeporte.setText(deporte.getNombredeporte());
            editDescripciondeporte.setText(deporte.getDescripciondeporte());


        }
    }


    public void limpiarTexto(View v){
        editIddeporte.setText("");
        editNombredeporte.setText("");
        editDescripciondeporte.setText("");

    }
}
