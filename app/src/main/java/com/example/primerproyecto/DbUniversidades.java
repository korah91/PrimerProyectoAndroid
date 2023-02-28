package com.example.primerproyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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


    public void reiniciarBaseDatos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE);
            Toast.makeText(context, "Se ha reiniciado la base de datos", Toast.LENGTH_SHORT).show();

            this.insertarUniversidad("UPV/EHU", 3, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.T1dgJjAqe-WOTiPtIzApWAHaHa%26pid%3DApi&f=1&ipt=a3a28a395244771c1dc829e0ea0df73143f47b2608c64bb5806b82a0d255f990&ipo=images");
            this.insertarUniversidad("Universidad Autónoma de Madrid", 5, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.9e96CDDUQkKpVJ1LSg9wPwHaDw%26pid%3DApi&f=1&ipt=46d5ecb7cd0719c850185991fd068b4a1953272f66d9ff83519db71244ee9266&ipo=images");
            this.insertarUniversidad("Universidad de Deusto", 0, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.explicit.bing.net%2Fth%3Fid%3DOIP.wqYXq_WpGpaz3WsBYnhxGQHaDo%26pid%3DApi&f=1&ipt=84b7af6598e0546a81298581f9d0d6094968faab4bbfaf0a6b91173f9684991e&ipo=images");
            this.insertarUniversidad("Universidad de Granada", 4, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.explicit.bing.net%2Fth%3Fid%3DOIP.PFsZDOyllpij0VCsN7qUDAHaHa%26pid%3DApi&f=1&ipt=4442466916175b76fdbda2d49527cb9f631c2fc425cc8e36b29aff1a1dafc535&ipo=images");
            this.insertarUniversidad("Universidad Juan Carlos", 1, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.explicit.bing.net%2Fth%3Fid%3DOIP.NfJAF0yT3MvwIFai8G-NKQHaEH%26pid%3DApi&f=1&ipt=e08b3ff7f3a361270d1a278ef3bb10c76cbeec63516c55e5cef3c551ab4728df&ipo=images");
        } catch(Exception ex) {
            ex.toString();
            Toast.makeText(context, "No se ha podido reiniciar la base de datos", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

}
