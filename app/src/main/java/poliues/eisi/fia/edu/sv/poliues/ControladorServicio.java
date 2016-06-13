package poliues.eisi.fia.edu.sv.poliues;

/**
 * Created by Rodrigo Daniel on 11/06/2016.
 */

import android.content.ContentResolver;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ControladorServicio {

    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";

        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);


        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG).show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;

    }

    public static String obtenerRespuestaPost(String url, JSONObject obj, Context ctx) {
        String respuesta = " ";
        try {
            HttpParams parametros = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(parametros, 3000);
            HttpConnectionParams.setSoTimeout(parametros, 5000);

            HttpClient cliente = new DefaultHttpClient(parametros);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("content-type", "application/json");

            StringEntity nuevaEntidad = new StringEntity(obj.toString());
            httpPost.setEntity(nuevaEntidad);
            Log.v("Peticion", url);
            Log.v("POST", httpPost.toString());
            HttpResponse httpRespuesta = cliente.execute(httpPost);
            StatusLine estado = httpRespuesta.getStatusLine();

            int codigoEstado = estado.getStatusCode();

            if (codigoEstado == 200) {
                respuesta = Integer.toString(codigoEstado);
                Log.v("respuesta", respuesta);
            } else {
                Log.v("respuesta", Integer.toString(codigoEstado));
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }

        return respuesta;
    }

    public static void insertarReservaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");


            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado en Reserva", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro duplicado",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void insertarDetalleReservaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");


            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado en DetalleReserva", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro duplicado",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void insertarHorarioPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");


            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado en Horario", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro duplicado",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


   /* public static void eliminarReservaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");


            if (respuesta == 1)
                Toast.makeText(ctx, "Registro Eliminado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro duplicado",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


    public static int eliminarReservaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta=0;
        try {
            JSONObject resultado = new JSONObject(json);
            respuesta = resultado.getInt("resultado");


            if (respuesta == 1)
                Toast.makeText(ctx, "Registro Eliminado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro no exite",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public static int obtenerIdReservaJSON(String json, Context ctx) {
        try {
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0)
                return Integer.valueOf(objs.getJSONObject(0).getString("id"));
            else {
                Toast.makeText(ctx, "Error idreserva no existe", Toast.LENGTH_LONG)
                        .show();
                return 0;
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, "Error con la respuesta JSON",
                    Toast.LENGTH_LONG).show();
            return 0;
        }
    }

    public static int actualizarReservaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta=0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta+= resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro actualizado de Reserva", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "no se modifico",
                        Toast.LENGTH_LONG).show();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }

    public static int actualizarDetalleReservaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta=0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta+= resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro actualizado Detalle Reserva", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "no se modifico",
                        Toast.LENGTH_LONG).show();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }

    public static int actualizarHorarioPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta=0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta+= resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro actualizado Horario", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "no se modifico",
                        Toast.LENGTH_LONG).show();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }

    public static int insertarAreaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta = 0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta += resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro duplicado",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }



    public static int actualizarAreaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta=0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta+= resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro actualizado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "no se modifico",
                        Toast.LENGTH_LONG).show();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }


    public static void insertarTarifaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");

            System.out.println(respuesta);

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro WEB ingresado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro WEB duplicado", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public static void eliminarTarifaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");

            System.out.println(respuesta);

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro WEB eliminado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro WEB no existe",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static int insertarDeportePHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta = 0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta = resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "Error registro duplicado",
                        Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }


    public static int actualizarTarifaPHP(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        int respuesta=0;

        try {
            JSONObject resultado = new JSONObject(json);
            respuesta+= resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro actualizado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(ctx, "no se modifico", Toast.LENGTH_LONG).show();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return respuesta;
    }


}






