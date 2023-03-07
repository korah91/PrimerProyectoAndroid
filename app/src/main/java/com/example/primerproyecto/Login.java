package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText et_email, et_password;
    Button btn_login;
    TextView tv_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_signup = findViewById(R.id.tv_signup);



        // Boton para iniciar sesion
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, MainActivity.class);

                String email = et_email.getText().toString();
                String password = et_password.getText().toString();



                // Paso el email y el password
                i.putExtra("email", email);
                i.putExtra("password", password);
                startActivity(i);
                // Termino esta actividad
                finish();
            }
        });

    }
}