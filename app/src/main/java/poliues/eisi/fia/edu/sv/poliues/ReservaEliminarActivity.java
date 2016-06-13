package poliues.eisi.fia.edu.sv.poliues;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReservaEliminarActivity extends  AppCompatActivity {

    private static int realIdD =0;
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
            creardialogo();
        }

    }

    public void mensajes(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


    }

    public void creardialogo(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿Seguro que Quiere Eliminar esta Reserva ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                String url = null;

                realIdD=Integer.valueOf(editIdReserva.getText().toString());
                
                int r=0;
                url=conn.getURLLocal()+"/ws_reserva_eliminar.php"+ "?idreserva=" + realIdD;
                r=ControladorServicio.eliminarReservaPHP(url, ReservaEliminarActivity.this);

                if(r==1){
                    mensajes("Registro Eliminado con exito Reserva N°: "+realIdD);
                }else {
                    mensajes("No se  encontro la reserva N°: " + realIdD);
                }


                //aceptar();
                controlhelper.abrir();

                //Creamos un Objeto reserva
                Reserva reserva = new Reserva();
                reserva.setIdreserva(realIdD);


                //delete the item selected
                String toastinfo = controlhelper.eliminar(reserva);
                controlhelper.cerrar();
                System.out.println(toastinfo);


                //refresh the listview
                //refreshListView();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //cancelar();
                //refreshListView();
            }
        });
        dialogo1.show();
    }
}
