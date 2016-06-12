package poliues.eisi.fia.edu.sv.poliues;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReservaEliminarActivity extends  AppCompatActivity {
    EditText editIdReserva;
    ControlBDPoliUES controlhelper;
    Conexion conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_eliminar);
        controlhelper=new ControlBDPoliUES(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        conn=new Conexion();
        editIdReserva=(EditText)findViewById(R.id.editidReserva);
        editIdReserva.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (TextUtils.isEmpty(editIdReserva.getText().toString())) {
                    editIdReserva.setError("El campo idreserva esta Vacio");

                } else {
                    editIdReserva.setError(null);
                }
            }
        });
    }

    public void eliminarReserva(View v){
        if (TextUtils.isEmpty(editIdReserva.getText().toString())){
            mensajes("El campo idReserva esta vacio");
        }else{
            String url = null;



            url=conn.getURLLocal()+"/ws_reserva_eliminar.php"+ "?idreserva=" + editIdReserva.getText().toString();
            ControladorServicio.eliminarReservaPHP(url, this);
            mensajes("Registro Eliminado con exito Reserva N°: "+editIdReserva.getText().toString());

        }
    }

    public void mensajes(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


    }
}
