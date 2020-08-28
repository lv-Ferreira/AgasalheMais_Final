package com.fatec.agasalhemais;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    //url servidor/codigoPHP
    String urldb = "http://192.168.15.17:80/dbCheckCon.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    //Armazenar o texto "Status"
    //TextView txt_statusCon = (TextView) findViewById(R.id.txt_statusCon);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestQueue = Volley.newRequestQueue(this);
        checarBanco();
    }


    private void checarBanco(){
        stringRequest = new StringRequest(Request.Method.POST, urldb,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //txt_statusCon.setText("Conexão efetuada com sucesso!");
                        Toast.makeText(getApplicationContext(), "Conexão com o Banco de Dados. Funcionou", Toast.LENGTH_SHORT).show();

                        //Executa um timer de 3 Segundos
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                //Redireciona o usuário para a janela de Login;
                                Intent novaTela = new Intent(MainActivity.this, ac_login.class);
                                startActivity(novaTela);
                                finish();
                            }
                        }, 3000); // Millisecond 1000 = 1 sec


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Con. Falhou, Tentando novamente em 5 segundos", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                checarBanco();
                            }
                        }, 5000); // Millisecond 1000 = 1 sec


                    }
                });
        requestQueue.add(stringRequest);
    }


}