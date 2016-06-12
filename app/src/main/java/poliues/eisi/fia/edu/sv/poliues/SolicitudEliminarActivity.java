package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudEliminarActivity extends AppCompatActivity {
    EditText editIddeporte;
    ControlBDPoliUES controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_eliminar);
        controlhelper=new ControlBDPoliUES(this);
        editIddeporte=(EditText)findViewById(R.id.editIddeporte);
    }


    public void eliminarDeporte(View v){
        String regEliminadas;
        Deporte deporte=new Deporte();
        deporte.setIddeporte(Integer.parseInt(editIddeporte.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarDeporte(deporte);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
