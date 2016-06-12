package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaConsultarActivity extends AppCompatActivity {
    ControlBDPoliUES helper;
    EditText editIdarea;
    EditText editMaximopersonas;
    EditText editNombreArea;
    EditText editDescripcionarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new ControlBDPoliUES(this);
        setContentView(R.layout.activity_area_consultar);
        editIdarea = (EditText) findViewById(R.id.editidArea);
        editMaximopersonas = (EditText) findViewById(R.id.editmaximopersonas);
        editNombreArea = (EditText) findViewById(R.id.editnombrearea);
        editDescripcionarea=(EditText) findViewById(R.id.editDescripcionarea);
    }

    public void consultarArea(View v) {
        helper.abrir();
        Area area = helper.consultarAreaJ(editIdarea.getText().toString());
        helper.cerrar();
        if(area == null)
            Toast.makeText(this, "Area con id " + editIdarea.getText().toString() +
                    " no encontrada", Toast.LENGTH_LONG).show();
        else{
            editMaximopersonas.setText(String.valueOf(area.getMaximopersonas()));
            editNombreArea.setText(area.getNombrearea());
            editDescripcionarea.setText(area.getDescripcionarea());

        }
    }

    public void limpiarAC(View v){
        editIdarea.setText("");
        editMaximopersonas.setText("");
        editNombreArea.setText("");
        editDescripcionarea.setText("");
    }


}
