package com.example.primerproyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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

}
