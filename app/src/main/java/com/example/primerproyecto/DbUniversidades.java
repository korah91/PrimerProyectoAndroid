package com.example.primerproyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbUniversidades extends DbHelper{

    Context context;

    public DbUniversidades(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Inserta una nueva universidad en la BD
    public long insertarUniversidad(String pNombre, int pValoracion, String pUrl){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Almacenamos los valores en un ContentValues
            ContentValues values = new ContentValues();
            values.put("nombre", pNombre);
            values.put("valoracion", pValoracion);
            values.put("url", pUrl);

            // Finalmente realizamos la query
            id = db.insert(TABLE, null, values);
        } catch (Exception exception){
            // Si falla imprime la excepcion
            exception.toString();
        }
        return id;
    }

    public ArrayList<Universidad> mostrarUniversidades(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Universidad> listaUniversidades = new ArrayList<Universidad>();
        Universidad universidad = new Universidad();
        Cursor cursorUniversidad = null;

        // Voy a ir guardando en listaUniversidades todas las universidades de la DB
        cursorUniversidad = db.rawQuery("SELECT * FROM " + TABLE, null);
        if(cursorUniversidad.moveToFirst()){
            do{
                universidad = new Universidad();
                universidad.setId(cursorUniversidad.getInt(0));
                universidad.setNombre(cursorUniversidad.getString(1));
                universidad.setValoracion(cursorUniversidad.getInt(2));
                universidad.setUrl(cursorUniversidad.getString(3));

                listaUniversidades.add(universidad);
            }
            while (cursorUniversidad.moveToNext());
        }
        cursorUniversidad.close();
        return listaUniversidades;
    }

    public boolean editarUniversidad(int id, String nombre, int valoracion, String url){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE + " SET nombre = '" + nombre + "', valoracion =" + valoracion + ", url = '" + url + "' WHERE id='" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        }
        // finally se ejecuta siempre
        finally {
            db.close();
        }

        return correcto;
    }

}
