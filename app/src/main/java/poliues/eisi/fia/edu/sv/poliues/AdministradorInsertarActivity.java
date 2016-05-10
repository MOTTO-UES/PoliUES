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
public class AdministradorInsertarActivity extends AppCompatActivity{

    EditText nombre, pass, correo;
    Button agregar;
    ControlBDPoliUES helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_insertar);

        helper = new ControlBDPoliUES(this);

        agregar = (Button) findViewById(R.id.button_agregarA);
        nombre = (EditText) findViewById(R.id.editText_nombreA);
        pass = (EditText) findViewById(R.id.editText_passwordA);
        correo = (EditText) findViewById(R.id.editText_correoA);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAdministrador();
            }
        });

    }



    private void agregarAdministrador(){
        String nombreA, passA, correoA;
        String regIngresados;

        nombreA = nombre.getText().toString();
        passA = pass.getText().toString();
        correoA = correo.getText().toString();

        Administrador administrador = new Administrador();
        //Falta id
        administrador.setNombreAdmin(nombreA);
        administrador.setPasswordAdmin(passA);
        administrador.setCorreoAdmin(correoA);

        helper.abrir();
        regIngresados = helper.insertarAdministrador(administrador);
        //helper.cerrar();

        Toast.makeText(this,regIngresados,Toast.LENGTH_SHORT).show();
        //Ir a otra activity
        Intent intent = new Intent(AdministradorInsertarActivity.this,AdministradorActivity.class);
        startActivity(intent);

    }

    private void limpiar(){
        nombre.setText("");
        pass.setText("");
        correo.setText("");
    }
}
