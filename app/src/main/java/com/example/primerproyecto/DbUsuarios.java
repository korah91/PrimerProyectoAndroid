package com.example.primerproyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Inserta un nuevo usuario en la DB
    public long insertarUsuario(String pEmail, String pPassword){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Almacenamos los valores en un ContentValues
            ContentValues values = new ContentValues();
            values.put("email", pEmail);
            values.put("password", pPassword);

            // Finalmente realizamos la query
            id = db.insert(T_USUARIOS, null, values);

        } catch (Exception exception){
            // Si falla imprime la excepcion
            exception.toString();
        }
        return id;
    }

    public
}
