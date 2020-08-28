package com.fatec.agasalhemais;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

public class ac_item_doacao extends AppCompatActivity {


    String url = "http://192.168.15.17:80/dbGetRequest.php";
    String inserturl = "http://192.168.15.17:80/dbInsertRequest.php";
    String deleteurl = "http://192.168.15.17:80/dbDeleteRequest.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    String pedido_Usuario;
    String pedido_Produto;
    String pedido_Variacao;
    String pedido_Data;
    String pedido_id;
    String pedido_descricao;

    TextView textProduto;
    TextView textVariacao;
    TextView textUsuario;
    TextView textDescricao;
    TextView textData;
    TextView textTermos;


    String id_pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_item_doacao);

        textProduto = (TextView)findViewById(R.id.txt_item);
        textVariacao = (TextView)findViewById(R.id.txt_variacao);
        textUsuario = (TextView)findViewById(R.id.txt_solicitado);
        textDescricao = (TextView)findViewById(R.id.txt_descricao);

        textTermos = (TextView)findViewById(R.id.txt_termosDoacao);



        requestQueue = Volley.newRequestQueue(this);
        //Recebe a informação de Login da Activity Anterior
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_pedido = extras.getString("id");
            carregaPedido();
        }

        View btnDoar = findViewById(R.id.btn_doar);

        btnDoar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserePedido();
            }
        });



    }

    private void carregaPedido(){
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        Log.v("logPedido", response);



                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            pedido_Usuario = jsonObject.getString("usuario");
                            pedido_Produto = jsonObject.getString("produto");
                            pedido_Variacao = jsonObject.getString("variacao");
                            pedido_Data = jsonObject.getString("data");
                            pedido_id = jsonObject.getString("id");
                            pedido_descricao = jsonObject.getString("descricao");
                            atPedido();


                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), pedido_Usuario, Toast.LENGTH_SHORT).show();

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
                params.put("ID",id_pedido);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }





    private void inserePedido(){
        stringRequest = new StringRequest(Request.Method.POST, inserturl,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        Log.v("logPedido", response);



                        try {
                            //PRECISA VALIDAR SE O PEDIDO FOI INSERIDO!!!
                            Toast.makeText(getApplicationContext(), "INSERIDO COM SUCESSO!", Toast.LENGTH_SHORT).show();
                            deletePedido();

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), pedido_Usuario, Toast.LENGTH_SHORT).show();

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
                params.put("Usuario",pedido_Usuario);
                params.put("Produto",pedido_Produto);
                params.put("Variacao",pedido_Variacao);
                params.put("Data_pedido",pedido_Data);
                params.put("Id_pedido",pedido_id);
                params.put("Doador","Luiz");
                //params.put("Data_doacao","18/08");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void deletePedido(){
        stringRequest = new StringRequest(Request.Method.POST, deleteurl,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        Log.v("logPedido", response);


                        try {
                            //PRECISA VALIDAR SE O PEDIDO FOI APAGADO!!!

                            Toast.makeText(getApplicationContext(), "AGUARDE!", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    //Redireciona o usuário para a janela de Login;
                                    Intent novaTela = new Intent(ac_item_doacao.this, ac_doacao_efetuada.class);
                                    //Envia a String de Login para a Activity seguinte
                                    novaTela.putExtra("ticket", "0");
                                    startActivity(novaTela);
                                    finish();
                                }
                            }, 3000); // Millisecond 3000 = 1 sec

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), pedido_Usuario, Toast.LENGTH_SHORT).show();

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
                params.put("Id",pedido_id);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }





    private void atPedido(){
        textProduto.setText("Pedido: "+pedido_Produto);
        textVariacao.setText("Tamanho: "+pedido_Variacao);
        textUsuario.setText("Solicitado por: "+pedido_Usuario);
        textDescricao.setText("Descrição: "+pedido_descricao);
        textTermos.setText("Ao clicar em doar, o pedido de: "+pedido_Produto+" solicitado por: "+pedido_Usuario+" será retirado do aplicativo. Portanto você assume um compromisso em ir a um ponto de coleta e efetuar a doação. Você tem 7 dias, caso não compareça a sua conta pode ser excluida por violar esse Termo de Doação. Caso você NÃO possa efetuar a doação, pedimos que entre em contato com o Fundo Social de Itapetininga para cancelar a doação, pois assim o pedido será estornado para o aplicativo.");
    }

}