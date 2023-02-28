package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AnadirOtro extends AppCompatActivity {
    Button btn_ok, btn_cancel;
    List<Universidad> listaUniversidades;
    RatingBar rt_valoracionUniversidad;
    EditText et_nombreUniversidad, et_urlUniversidad;
    TextView tv_idUniversidad;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_otro);

        // Obtenemos el objeto DB para universidades
        DbUniversidades dbUniversidades = new DbUniversidades(this);

        // Obtengo la lista de universidades de la BD; cargo BD en objetos
        listaUniversidades = dbUniversidades.mostrarUniversidades();

        // Botones para guardar y cancelar
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
        rt_valoracionUniversidad = findViewById(R.id.rt_valoracionUniversidad);
        et_nombreUniversidad = findViewById(R.id.et_nombreUniversidad);
        et_urlUniversidad = findViewById(R.id.et_urlUniversidad);
        tv_idUniversidad = findViewById(R.id.tv_idUniversidad);

        Intent intent = getIntent();
        // Si el intent no da un numero, devuelve -1
        // Si es -1, se presume que se esta creando un nuevo item
        id = intent.getIntExtra("id", -1);
        Universidad universidad = null;

        // Se ha llegado a esta actividad para editar un item
        if (id >= 0){

            for(Universidad p: listaUniversidades){
                if (p.getId() == id){
                    // Si coincide el id se obtiene el item
                    universidad = p;
                }
            }
            // Se obtienen los datos del item
            et_nombreUniversidad.setText(universidad.getNombre());
            rt_valoracionUniversidad.setRating(universidad.getValoracion());
            et_urlUniversidad.setText(universidad.getUrl());
            tv_idUniversidad.setText(String.valueOf(id));

        }
        // Se ha llegado a esta actividad para crear un nuevo item
        else{
            tv_idUniversidad.setText("No creado");

        }



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // El boton solo funciona si se ha escrito el nombre
                if(et_nombreUniversidad.getText().toString().trim().length() > 0){
                    String nombre = et_nombreUniversidad.getText().toString();
                    int valoracion = Math.round(rt_valoracionUniversidad.getRating());
                    String url = et_urlUniversidad.getText().toString();

                    if (id >= 0){
                        // se actualiza el item
                        Universidad updatedUniversidad = new Universidad();

                        //updatedUniversidad.setId(id);                 EL ID SE AUTOINCREMENTA, ASI QUE NO LO CAMBIO
                        updatedUniversidad.setNombre(nombre);
                        updatedUniversidad.setValoracion(valoracion);
                        updatedUniversidad.setUrl(url);

                        // Actualizo la universidad
                        dbUniversidades.editarUniversidad(id, nombre, valoracion, url);
                    }
                    else {
                        // se crea el item

                        Universidad newUniversidad = new Universidad();

                        newUniversidad.setNombre(nombre);
                        newUniversidad.setValoracion(valoracion);
                        newUniversidad.setUrl(url);


                        // Lo añado a la lista
                        dbUniversidades.insertarUniversidad(nombre, valoracion, url);
                    }


                    Intent intent = new Intent(AnadirOtro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                // Si falta el nombre o la valoracion, se muestra un Toast
                else{
                    Toast.makeText(AnadirOtro.this, "¡Debes introducir nombre y valoracion!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnadirOtro.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}