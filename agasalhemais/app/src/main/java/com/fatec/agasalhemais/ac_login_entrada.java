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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ac_login_entrada extends AppCompatActivity {

    EditText editEmail;
    EditText editSenha;

    String url = "http://192.168.15.17:80/dbLoginCheck.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login_entrada);

        View btnEntrar = findViewById(R.id.btn_entrar);
        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);

        //Carrega a "fila" e tambem a biblioteca Volley
        requestQueue = Volley.newRequestQueue(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editEmail.getText().length()<=6){
                    editEmail.requestFocus();
                    editEmail.setError("Email inválido");
                }else if(editSenha.getText().length()<=6){
                    editSenha.requestFocus();
                    editSenha.setError("Minimo 6 caracteres");
                }else{
                    validaEntrada();;
                }


            }
        });
    }

    private void validaEntrada(){
        View btnEntrar = findViewById(R.id.btn_entrar);
        btnEntrar.setEnabled(false);
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("logLogin", response);
                        // alertarUsuario1();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isLogin = jsonObject.getBoolean("login");

                            if(isLogin!=true){
                                editEmail.requestFocus();
                                editEmail.setError("E-mail ou login inválidos!");
                                View btnEntrar = findViewById(R.id.btn_entrar);
                                btnEntrar.setEnabled(true);

                            }else{
                                Toast.makeText(getApplicationContext(), "Ok, efetuando login", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        //Redireciona o usuário para a janela de Login;
                                        Intent novaTela = new Intent(ac_login_entrada.this, ac_principal.class);
                                        startActivity(novaTela);
                                        finish();
                                    }
                                }, 3000); // Millisecond 3000 = 1 sec



                            }

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Não conseguiu trazer as informações!", Toast.LENGTH_SHORT).show();
                            View btnEntrar = findViewById(R.id.btn_entrar);
                            btnEntrar.setEnabled(true);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        View btnEntrar = findViewById(R.id.btn_entrar);
                        btnEntrar.setEnabled(true);


                    }
                })  {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("email",editEmail.getText().toString());
                params.put("senha",editSenha.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }




}