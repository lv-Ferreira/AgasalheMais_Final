package com.fatec.agasalhemais;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fatec.agasalhemais.model.Pedido;
import com.fatec.agasalhemais.model.db_pedidos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import org.json.JSONException;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ac_principal extends AppCompatActivity {

    String url = "http://192.168.15.17:80/dbGetRequests.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    String pedido_Usuario;
    String pedido_Produto;
    String pedido_Variacao;
    String pedido_Data;
    String pedido_id;
    String conf_id;

    int numRegistros;
    RecyclerView rv2;

    private PedidoAdapter pedidosAdapter;

    String[] id_utilizadas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestQueue = Volley.newRequestQueue(this);
        carregarPedidos();

        pedidosAdapter = new  PedidoAdapter(new ArrayList<>(db_pedidos.fakePedidos()));

        final RecyclerView rv = findViewById(R.id.recycler_view_main);
        rv2 = findViewById(R.id.recycler_view_main);

        rv.setAdapter(pedidosAdapter);
        rv.scrollToPosition(0);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarPedidos();
                carregarPedidos();
            }
        });








    }

    private void carregarPedidos(){

        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        Log.v("logPedidos", response);



                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            for(int i=0;i<jsonObject.length();i++) {
                                String B = Integer.toString(i);
                                JSONObject arr = jsonObject.getJSONObject(B);

                                    pedido_Usuario = arr.getString("usuario");
                                    pedido_Produto = arr.getString("produto");
                                    pedido_Variacao = arr.getString("variacao");
                                    pedido_Data = arr.getString("data");
                                    pedido_id = arr.getString("id");
                                    atualizarPedidos();

                            }


                        }catch (Exception e) {
                            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })  {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                //params.put("login",editLogin.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }




    private void atualizarPedidos(){
        rv2.scrollToPosition(0);
        pedidosAdapter.getPedidos().add(0, Pedido.construtorPedido.construtor()
                .setUsuario_pedido(pedido_Usuario)
                .setItem_pedido(pedido_Produto)
                .setItem_variacao(pedido_Variacao)
                .setItem_data(pedido_Data)
                .setItem_id(pedido_id)
                .build()
        );

        pedidosAdapter.notifyItemInserted(0);
    }


}