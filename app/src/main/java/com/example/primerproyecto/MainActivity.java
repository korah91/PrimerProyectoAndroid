package com.example.primerproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Presidents APP";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    Button btn_anadir, btn_reset;
    List<Universidad> listaUniversidades;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos el BD Helper
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        // Le indicamos que se va a escribir sobre la BD
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            Toast.makeText(MainActivity.this, "Se ha creado la BD", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Ha habido un error", Toast.LENGTH_SHORT).show();
        }

        // Obtenemos el objeto DB para universidades
        DbUniversidades dbUniversidades = new DbUniversidades(this);

        // Obtengo la lista de universidades de la BD; cargo BD en objetos
        listaUniversidades = dbUniversidades.mostrarUniversidades();

        // Log que imprime todas las universidades
        Log.d(TAG, "onCreate: "+ listaUniversidades.toString());
        Toast.makeText(this, "Universidades count: "+ listaUniversidades.size(), Toast.LENGTH_SHORT).show();

        // Boton para anadir una nueva universidad
        btn_anadir = findViewById(R.id.btn_anadir);
        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnadirOtro.class);
                startActivity(intent);
                finish();
            }
        });

        // Boton para resetear la base de datos y anadir universidades por defecto
        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbUniversidades.reiniciarBaseDatos();

                // Se han borrado los items, asi que cargo una lista vacia en el Adapter del RecyclerView
                listaUniversidades = dbUniversidades.mostrarUniversidades();
                mAdapter = new Adapter(listaUniversidades, MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }
        });



        // Obtengo el recyclerview
        recyclerView = findViewById(R.id.idRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Uso el Adapter
        mAdapter = new Adapter(listaUniversidades, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
    }




    // Funcion para el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Obtengo el layout sort_menu.xml
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    // Funcion para el menu que trata el clickEvent
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Trato el click a cada item del menu
        switch (item.getItemId()){
            case R.id.menu_AZ:
                // ordenar de A a Z
                // Ordeno la lista de universidades utilizando un comparador diferente para cada opcion
                Collections.sort(listaUniversidades, Universidad.UniversidadNameAZComparator);
                Toast.makeText(MainActivity.this, "Ordenado de A a Z", Toast.LENGTH_SHORT).show();

                // Le aviso al adaptador que los datos de las vistas se han cambiado para que se actualice.
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_ZA:
                // ordenar de Z a A
                Collections.sort(listaUniversidades, Universidad.UniversidadNameZAComparator);
                Toast.makeText(MainActivity.this, "Ordenado de Z a A", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_dateASC:
                // ordenar por Date asc
                Collections.sort(listaUniversidades, Universidad.UniversidadValoracionASCComparator);
                Toast.makeText(MainActivity.this, "Ordenado de Valoracion ASC", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_dateDESC:
                // ordenar por Date desc
                Collections.sort(listaUniversidades, Universidad.UniversidadValoracionDESCComparator);
                Toast.makeText(MainActivity.this, "Ordenado de Valoracion DESC", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}