package com.fatec.agasalhemais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ac_cadastro_senha extends AppCompatActivity {

    String recLogin;
    String recEmail;

    EditText editSenha;

    String url = "http://192.168.15.17:80/dbCadUser.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cadastro_senha);

        requestQueue = Volley.newRequestQueue(this);

        //Recebe a informação de Login da Activity Anterior
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recLogin = extras.getString("login");
            recEmail = extras.getString("email");
        }



        editSenha = findViewById(R.id.edit_senha);
        View btnAvancar = findViewById(R.id.btn_avancar);
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSenha.getText().length()<=6){
                    editSenha.requestFocus();
                    editSenha.setError("Minimo 6 caracteres!");
                }else{
                    //Toast.makeText(getApplicationContext(), "CADASTRADO", Toast.LENGTH_SHORT).show();
                    cadastrarUsuario();
                }
            }
        });

    }

    private void cadastrarUsuario(){
        View btnAvancar = findViewById(R.id.btn_avancar);
        btnAvancar.setEnabled(false);
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("logLogin", response);

                        Toast.makeText(getApplicationContext(), "CADASTRADO COM SUCESSO!", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                //Redireciona o usuário para a janela de Login;
                                Intent novaTela = new Intent(ac_cadastro_senha.this, ac_login.class);
                                //Envia a String de Login para a Activity seguinte
                                startActivity(novaTela);
                                finish();
                            }
                        }, 3000); // Millisecond 3000 = 1 sec



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        View btnAvancar = findViewById(R.id.btn_avancar);
                        btnAvancar.setEnabled(true);


                    }
                })  {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("Login",recLogin.toString());
                params.put("Email",recEmail);
                params.put("Senha",editSenha.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }




}