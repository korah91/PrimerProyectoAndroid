package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AnadirOtro extends AppCompatActivity {
    Button btn_ok, btn_cancel;
    List<President> presidentList;
    EditText et_presidentDate, et_presidentName, et_pictureUrl;
    TextView tv_presId;
    int id;

    MyApp myapp = (MyApp) this.getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_otro);

        // Consigo la lista
        presidentList = myapp.getPresidentList();

        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
        et_presidentDate = findViewById(R.id.et_presidentDate);
        et_presidentName = findViewById(R.id.et_presidentName);
        et_pictureUrl = findViewById(R.id.et_pictureUrl);
        tv_presId = findViewById(R.id.tv_presidentId);

        Intent intent = getIntent();
        // Si el intent no da un numero, devuelve -1
        // Si es -1, se presume que se esta creando un nuevo item
        id = intent.getIntExtra("id", -1);
        President president = null;

        if (id >= 0){
            // se edita el item
            for(President p: presidentList){
                if (p.getId() == id){
                    // Si coincide el id se obtiene el item
                    president = p;
                }
            }
            // Se obtienen los datos del item
            et_presidentName.setText(president.getName());
            et_presidentDate.setText(String.valueOf(president.getDate()));
            et_pictureUrl.setText(president.getUrl());
            tv_presId.setText(String.valueOf(id));

        }
        else{
            // se crea un item

        }



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id >= 0){
                    // se actualiza el item
                    President updatedPresident = new President(id, et_presidentName.getText().toString(), Integer.parseInt(et_presidentDate.getText().toString()), et_pictureUrl.getText().toString());
                    presidentList.set(id, updatedPresident);
                }
                else {
                    // se crea el item

                    // Consigo el id
                    int nextId = myapp.getNextId();
                    // Los demas atributos ya los tengo
                    President newPresident = new President(nextId, et_presidentName.getText().toString(), Integer.parseInt(et_presidentDate.getText().toString()), et_pictureUrl.getText().toString());

                    // Lo añado a la lista
                    presidentList.add(newPresident);
                    myapp.setNextId(nextId + 1);
                }


                Intent intent = new Intent(AnadirOtro.this, MainActivity.class);
                startActivity(intent);
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