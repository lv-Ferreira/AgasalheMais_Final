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

public class ac_cadastro_email extends AppCompatActivity {
    String login;
    EditText editEmail;

    String url = "http://192.168.15.17:80/dbGetEmail.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cadastro_email);

        //Carrega a "fila" e tambem a biblioteca Volley
        requestQueue = Volley.newRequestQueue(this);

        //Recebe a informação de Login da Activity Anterior
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             login = extras.getString("login");
        }

        editEmail = findViewById(R.id.edit_email);

        View btnAvancar = findViewById(R.id.btn_avancar);
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editEmail.getText().length()<=5){
                    editEmail.requestFocus();
                    editEmail.setError("E-mail inserido não é válido!");
                }else{
                    validarEmail();
                }
            }
        });



    }


    private void validarEmail(){
        View btnAvancar = findViewById(R.id.btn_avancar);
        btnAvancar.setEnabled(false);
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("logLogin", response);
                        // alertarUsuario1();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isCadastrado = jsonObject.getBoolean("email");

                            if(isCadastrado){
                                editEmail.requestFocus();
                                editEmail.setError("Email já cadastrado!");
                                View btnAvancar = findViewById(R.id.btn_avancar);
                                btnAvancar.setEnabled(true);

                            }else{
                                Toast.makeText(getApplicationContext(), "aguarde", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        //Redireciona o usuário para a janela de Login;
                                        Intent novaTela = new Intent(ac_cadastro_email.this, ac_cadastro_senha.class);
                                        //Envia a String de Login para a Activity seguinte
                                        novaTela.putExtra("login", login.toString());
                                        novaTela.putExtra("email", editEmail.getText().toString());
                                        startActivity(novaTela);
                                        finish();
                                    }
                                }, 3000); // Millisecond 3000 = 1 sec



                            }

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Não conseguiu trazer as informações!", Toast.LENGTH_SHORT).show();
                            View btnAvancar = findViewById(R.id.btn_avancar);
                            btnAvancar.setEnabled(true);
                        }


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
                params.put("Email",editEmail.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }




}