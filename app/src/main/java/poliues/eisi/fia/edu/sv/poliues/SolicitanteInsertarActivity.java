package poliues.eisi.fia.edu.sv.poliues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dario on 08/05/2016.
 */
public class SolicitanteInsertarActivity extends AppCompatActivity {
    EditText nombre, pass, correo;
    Button agregar, limpiar;
    ControlBDPoliUES helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        System.out.println("PORQUE NO LO HACE PAPUAUAUAUUAUAUAS PORQUEEEEEE DIME PORQUE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitante_insertar);

        helper = new ControlBDPoliUES(this);

        agregar = (Button) findViewById(R.id.agregar_solicitante);
        limpiar = (Button) findViewById(R.id.limpiar_solicitante);

        nombre = (EditText) findViewById(R.id.editText_nombreS);
        pass = (EditText) findViewById(R.id.editText_passwordS);
        correo = (EditText) findViewById(R.id.editText_correoS);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarSolicitante();
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

    }



    private void agregarSolicitante(){
        String nombreS, passS, correoS;
        String regIngresados;

        nombreS = nombre.getText().toString();
        passS = pass.getText().toString();
        correoS = correo.getText().toString();

        Solicitante solicitante = new Solicitante();
        //Falta id
        solicitante.setNombre(nombreS);
        solicitante.setPassword(passS);
        solicitante.setCorreo(correoS);

        helper.abrir();
        regIngresados = helper.insertarSolicitante(solicitante);
        helper.cerrar();

        Toast.makeText(this, regIngresados, Toast.LENGTH_SHORT).show();
        //Ir a otra activity
        Intent intent = new Intent(SolicitanteInsertarActivity.this,SolicitanteActivity.class);
        startActivity(intent);

    }

    private void limpiar(){
        nombre.setText("");
        pass.setText("");
        correo.setText("");
    }
}
