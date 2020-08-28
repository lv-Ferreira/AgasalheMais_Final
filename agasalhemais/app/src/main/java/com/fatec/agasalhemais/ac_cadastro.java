package com.fatec.agasalhemais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ac_cadastro extends AppCompatActivity {

    EditText editLogin;

    String url = "http://192.168.15.17:80/dbGetUser.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cadastro);

        requestQueue = Volley.newRequestQueue(this);
        View btnAvancar = findViewById(R.id.btn_avancar);

        editLogin = findViewById(R.id.edit_nomeUsuario);

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editLogin.getText().length()<=2){
                    editLogin.requestFocus();
                    editLogin.setError("Minimo 2 catecteres");
                }else{
                    validarUsuario();
                }
            }
        });

    }


    private void validarUsuario() {
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
                            boolean isCadastrado = jsonObject.getBoolean("cadastrado");

                            if(isCadastrado){
                                editLogin.requestFocus();
                                editLogin.setError("Usuário já cadastrado!");
                                View btnAvancar = findViewById(R.id.btn_avancar);
                                btnAvancar.setEnabled(true);

                            }else{
                                Toast.makeText(getApplicationContext(), "aguarde", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        //Redireciona o usuário para a janela de Login;
                                        Intent novaTela = new Intent(ac_cadastro.this, ac_cadastro_email.class);
                                        //Envia a String de Login para a Activity seguinte
                                        novaTela.putExtra("login", editLogin.getText().toString());
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
                        editLogin.setEnabled(true);

                    }
                })  {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("login",editLogin.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void alertarUsuario(){
        Toast.makeText(getApplicationContext(), "Caiu na desconexão", Toast.LENGTH_SHORT).show();
    }

    private void alertarUsuario1(){
        Toast.makeText(getApplicationContext(), "Conectou ao server", Toast.LENGTH_SHORT).show();
    }


}
