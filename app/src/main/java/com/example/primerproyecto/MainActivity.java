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

    MyApp myapp = (MyApp) this.getApplication();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    Button btn_anadir;
    List<President> presidentList;
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

        // Obtengo la lista
        presidentList = myapp.getPresidentList();

        Log.d(TAG, "onCreate: "+ presidentList.toString());
        Toast.makeText(this, "Presidents count: "+ presidentList.size(), Toast.LENGTH_SHORT).show();

        btn_anadir = findViewById(R.id.btn_anadir);
        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnadirOtro.class);
                startActivity(intent);
            }
        });

        // Obtengo el recyclerview
        recyclerView = findViewById(R.id.idRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Uso el Adapter
        mAdapter = new Adapter(presidentList, MainActivity.this);
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
                // Ordeno la lista de presidentes utilizando un comparador diferente para cada opcion
                Collections.sort(presidentList, President.PresidentNameAZComparator);
                Toast.makeText(MainActivity.this, "Ordenado de A a Z", Toast.LENGTH_SHORT).show();

                // Le aviso al adaptador que los datos de las vistas se han cambiado para que se actualice.
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_ZA:
                // ordenar de Z a A
                Collections.sort(presidentList, President.PresidentNameZAComparator);
                Toast.makeText(MainActivity.this, "Ordenado de Z a A", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_dateASC:
                // ordenar por Date asc
                Collections.sort(presidentList, President.PresidentDateASCComparator);
                Toast.makeText(MainActivity.this, "Ordenado de Date ASC", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_dateDESC:
                // ordenar por Date desc
                Collections.sort(presidentList, President.PresidentDateDESCComparator);
                Toast.makeText(MainActivity.this, "Ordenado de Date DESC", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}