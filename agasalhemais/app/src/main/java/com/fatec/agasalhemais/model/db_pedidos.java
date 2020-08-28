package com.fatec.agasalhemais.model;
import java.util.Arrays;
import java.util.List;

public class db_pedidos {
    //ESTA CLASSE É RESPONSAVEL POR RECEBER E ENVIAR OS DADOS PARA O CONSTRUTOR(Pedido)

    public static List<Pedido> fakePedidos(){
        return Arrays.asList(
              Pedido.construtorPedido.construtor()
                      .setUsuario_pedido("Todos pedidos listados!")
                      .setItem_pedido("volte novamente mais tarde")
                      .setItem_variacao("ou entre em contato conosco :)")
                      .setItem_data(";")
                      .setItem_id("0")
                .build()
                );
    }
}
