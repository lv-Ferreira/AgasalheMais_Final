package com.fatec.agasalhemais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ac_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);


        View btn_criarConta = findViewById(R.id.btn_criarConta);

        btn_criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastro = new Intent(ac_login.this, ac_cadastro.class);
                startActivity(telaCadastro);
                finish();
            }
        });

        View btnEntrar = findViewById(R.id.btn_entrarConta);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaLogin = new Intent(ac_login.this, ac_login_entrada.class);
                startActivity(telaLogin);
                finish();
            }
        });



    }


}