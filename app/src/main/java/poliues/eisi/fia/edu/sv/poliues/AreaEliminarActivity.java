package poliues.eisi.fia.edu.sv.poliues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaEliminarActivity extends AppCompatActivity {

    EditText editIdarea;
    ControlBDPoliUES controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_eliminar);
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
}
