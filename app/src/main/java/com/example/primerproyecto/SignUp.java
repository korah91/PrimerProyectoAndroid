package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText et_email, et_password;
    Button btn_signup;
    TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Obtenemos el BD Helper
        DbHelper dbHelper = new DbHelper(this);
        // Pedimos acceso a la BD
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Obtenemos el objeto BD para los usuarios
        DbUsuarios dbUsuarios = new DbUsuarios(this);


        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signup = findViewById(R.id.btn_signup);
        tv_login = findViewById(R.id.tv_login);


        // Cuando se pulsa el boton para iniciar sesion
        // Primero se comprueba que no esten vacios los campos
        // Luego se comprueba el login con la BD
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, MainActivity.class);

                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                // Si el email esta vacio o no es un email valido
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SignUp.this, getString(R.string.debesIntroducirEmail), Toast.LENGTH_SHORT).show();
                }
                // Si la contrasena esta vacia
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, getString(R.string.debesIntroducirContraseña), Toast.LENGTH_SHORT).show();
                }

                // Si se han introducido email y contrasena
                else {
                    // Se comprueba que el email no exista ya
                    boolean yaExiste = dbUsuarios.existeEmail(email);

                    // Si el email no esta registrado ya se registra
                    if (!yaExiste) {

                        dbUsuarios.insertarUsuario(email, password);
                        // Paso el email
                        i.putExtra("email", email);
                        startActivity(i);
                        // Termino esta actividad
                        finish();
                    }
                    // Si el email ya existe se muestra un mensaje
                    else {
                        Toast.makeText(SignUp.this, getString(R.string.yaExisteEmail), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void onClickLogin(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }
}