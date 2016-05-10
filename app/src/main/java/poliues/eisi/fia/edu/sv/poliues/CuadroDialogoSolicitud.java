package poliues.eisi.fia.edu.sv.poliues;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Rodrigo Daniel on 10/05/2016.
 */
public class CuadroDialogoSolicitud extends DialogFragment {
    AlertDialog.Builder builder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        System.out.println("CREAR DIALOGO");

        final String[] items = {"Español", "Inglés", "Francés"};

        builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Selección")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.i("Dialogos", "Opción elegida: " + items[item]);
                    }
                });

        return builder.create();
    }


    public void invocar(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        show(fragmentManager," ");

    }


}
