package com.fatec.agasalhemais;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatec.agasalhemais.model.Pedido;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>  {
    //ESTA CLASSE É RESPONSAVEL POR CRIAR E ATRIBUIR OS PEDIDOS NA LISTA: pedidos
    private final List<Pedido> pedidos;

    PedidoAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //retorna os pedidos para o ac_principal
    public List<Pedido> getPedidos(){
        return pedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.ac_pedido_item, parent, false);
        return new PedidoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, final int position) {
        Pedido pedido = pedidos.get(position);
        holder.bind(pedido);


        //CLICK NO PEDIDO!!!------------------------------------------------------------------------
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Click:"+pedidos.get(position).getItem_id());
                Intent intent = new Intent(view.getContext(), ac_item_doacao.class).putExtra("id", pedidos.get(position).getItem_id().toString()); view.getContext().startActivity(intent);
                //Intent intent = new Intent(view.getContext(), ac_item_doacao.class); view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    class PedidoViewHolder extends RecyclerView.ViewHolder{
        //Referente as Strings criadas no XML ac_pedido_item
        TextView textUsuario;
        TextView textPedido;
        TextView textVariacao;
        TextView textIcone;
        TextView textData;

         PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            //Busca a variavel no XML ac_pedido_item
            textUsuario = itemView.findViewById(R.id.txt_user);
            textPedido = itemView.findViewById(R.id.txt_subject);
            textVariacao = itemView.findViewById(R.id.txt_preview);
            textIcone = itemView.findViewById(R.id.txt_icon);
            textData =itemView.findViewById(R.id.txt_date);

        }

         void bind(Pedido pedido) {
            textUsuario.setText(pedido.getUsuario_pedido());
            textPedido.setText(pedido.getItem_pedido());
            textVariacao.setText(pedido.getItem_variacao());
            //Inutilizado, o ideal é pegar a 1ª letra do usuario :(
            //textIcone.setText(pedido.getItem_icone());
            textIcone.setText(String.valueOf(pedido.getUsuario_pedido().charAt(0)));
            textData.setText(pedido.getItem_data());


        }
    }
}
